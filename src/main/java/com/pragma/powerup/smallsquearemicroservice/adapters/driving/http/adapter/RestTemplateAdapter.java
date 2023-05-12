package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundMicroserviceDownException;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
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
        ResponseEntity<User> response;

        try {
            response = restTemplate.exchange(urlId, HttpMethod.GET, null, User.class);
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
