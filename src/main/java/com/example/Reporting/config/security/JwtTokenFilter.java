package com.example.Reporting.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {
        String jwtToken = extractToken(request);

        if (isTokenValid(jwtToken)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    jwtToken,
                    null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isTokenValid(String jwtToken) {
        if (StringUtils.isBlank(jwtToken)) {
            return false;
        }

        String[] parts = jwtToken.split("\\.");
        if (parts.length != 3) {
            return false;
        }

        String payload = new String(Base64.getDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        JSONObject payloadJson = new JSONObject(payload);
        if (payloadJson.has("exp")) {
            long expiration = payloadJson.getLong("exp");
            long currentTime = System.currentTimeMillis() / 1_000;

            return expiration > currentTime;
        }
        return false;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return StringUtils.EMPTY;
    }
}
