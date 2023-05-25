package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundMicroserviceDownException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateAdapter {
    RestTemplate restTemplate = new RestTemplate();
    @Value("${my.variables.url}")
    private String url;

    public User getUser(Long id) {
        String urlId = url + id;

        // Obtener el token de autorización desde el interceptor
        String authorizationToken = JwtInterceptor.getAuthorizationToken();

        // Configurar el encabezado "Authorization" con el token obtenido
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorizationToken);
        HttpEntity<?> entity = new HttpEntity<>(null, headers);

        ResponseEntity<User> response;
        try {
            response = restTemplate.exchange(urlId, HttpMethod.GET, entity, User.class);
        } catch (HttpClientErrorException ex) {
            if (!ex.getStatusCode().is2xxSuccessful()) {
                throw new UserNotFoundException();
            } else {
                throw new UserNotFoundMicroserviceDownException();
            }
        }
        return response.getBody();
    }
}
