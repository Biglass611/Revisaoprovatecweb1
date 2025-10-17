package com.example.Lucas.config;

import com.example.Lucas.entity.Funcionario;
import com.example.Lucas.repository.FuncionarioRepository;
import com.example.Lucas.config.UserDetailsImpl;
import com.example.Lucas.service.JwtTokenService; // Ajuste o package se o seu JwtTokenService estiver em outro lugar
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (checkIfEndpointRequiresAuthentication(request)) {
            String token = recoverToken(request);

            if (token != null && !token.isEmpty()) {
                String subject = jwtTokenService.getSubjectFromToken(token);

                Optional<Funcionario> optionalFuncionario = funcionarioRepository.findByMatricula(subject);
                if (optionalFuncionario.isPresent()) {
                    Funcionario funcionario = optionalFuncionario.get();

                    UserDetailsImpl userDetails = new UserDetailsImpl(funcionario);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, // o objeto UserDetails completo
                            null,
                            userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new RuntimeException("Funcionário não encontrado para o token informado.");
                }
            } else {
                throw new RuntimeException("Token ausente ou inválido.");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove "Bearer "
        }
        return null;
    }

    private boolean checkIfEndpointRequiresAuthentication(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        // ajustado para funcionamento do Swagger e endpoints públicos
        return Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .noneMatch(publicEndpoint ->
                        requestURI.startsWith(publicEndpoint.replace("/**", ""))
                );
    }
}
