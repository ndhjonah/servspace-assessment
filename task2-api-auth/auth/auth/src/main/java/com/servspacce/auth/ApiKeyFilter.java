package com.servspacce.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyFilter(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String keyValue = authHeader.substring(7);

            ApiKey foundKey = apiKeyRepository.findByKeyValue(keyValue);

            if (foundKey != null && foundKey.isActive()) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        response.setStatus(403);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\"error\": \"Forbidden\", " +
                        "\"message\": \"Invalid or missing API key.\"}"
        );
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/api/keys/create") || path.equals("/");
    }
}