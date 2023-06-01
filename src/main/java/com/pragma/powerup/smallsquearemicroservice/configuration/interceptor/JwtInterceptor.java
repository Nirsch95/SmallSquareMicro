package com.pragma.powerup.smallsquearemicroservice.configuration.interceptor;

import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.exception.InvalidTokenException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.exception.UserIsNotAllowedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String TOKEN_PREFIX = "Bearer ";
    @Setter
    private String authorizationToken;
    @Setter
    private Long userId;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${my.variables.admin}")
    private String admin;

    @Value("${my.variables.owner}")
    private String owner;

    @Value("${my.variables.client}")
    private String client;

    private final Map<String, String> endpointRoles = new HashMap<>();
    @PostConstruct
    private void initializeEndpointRoles() {
        endpointRoles.put("/dish", owner);
        endpointRoles.put("/restaurant/add", owner);
        endpointRoles.put("/restaurant", admin);
        endpointRoles.put("/restaurant/all/", client);
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String userRole = getUserRole(request);
        if (admin.equals(userRole) && isAllowedEndpoint(request.getRequestURI(), userRole)) {
            return true;
        }

        if (owner.equals(userRole) && isAllowedEndpoint(request.getRequestURI(), userRole)) {
            return true;
        }

        if (client.equals(userRole) && isAllowedEndpoint(request.getRequestURI(), userRole)) {
            return true;
        }
        throw new UserIsNotAllowedException();
    }

    private String getUserRole(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException();
        }
        authorizationToken = token.substring(TOKEN_PREFIX.length());
        try {
            Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(authorizationToken).getBody();
            List<String> roles = claims.get("roles", List.class);
            userId = claims.get("id", Long.class);
            return roles.get(0);
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }

    private boolean isAllowedEndpoint(String requestURI, String role) {
        if (endpointRoles.containsKey(requestURI)) {
            String allowedRole = endpointRoles.get(requestURI);
            return allowedRole.equals(role);
        }
        if(requestURI.startsWith("/restaurant/all/") && client.equals(role)){
            return true;
        }
        return requestURI.startsWith("/restaurant/add/") && owner.equals(role);
    }
}
