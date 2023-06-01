package com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.adapter;

import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.dto.request.UserDto;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundException;
import com.pragma.powerup.smallsquearemicroservice.adapters.driving.http.exceptions.UserNotFoundMicroserviceDownException;
import com.pragma.powerup.smallsquearemicroservice.configuration.interceptor.JwtInterceptor;
import com.pragma.powerup.smallsquearemicroservice.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateAdapter {

    private String authorizationToken;
    private final RestTemplate restTemplate;
    private final String url;

    private final JwtInterceptor jwtInterceptor;

    public RestTemplateAdapter(@Value("${my.variables.url}") String url, JwtInterceptor jwtInterceptor) {
        this.restTemplate = new RestTemplate();
        this.url = url;
        this.jwtInterceptor = jwtInterceptor;
    }

    public User getUser(Long id) {
        String urlId = url + "get/" + id;
        // Obtener el token de autorización desde el interceptor
        authorizationToken = jwtInterceptor.getAuthorizationToken();
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

    public String createEmployee(UserDto userDto){
        String urlEmployee = url + "employee";// Reemplaza con los datos que deseas enviar en el cuerpo de la solicitud
        // Obtener el token de autorización desde el interceptor
        authorizationToken = jwtInterceptor.getAuthorizationToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authorizationToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto, headers);

        ResponseEntity<UserDto> response = restTemplate.exchange(urlEmployee, HttpMethod.POST, requestEntity, UserDto.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            UserDto userCreated = response.getBody();
            assert userCreated != null;
            return userCreated.getDniNumber();
        } else {
            return null;
        }
    }

    public User getUserByDni(String dniUser) {
        String urlId = url + dniUser;
        // Obtener el token de autorización desde el interceptor
        authorizationToken = jwtInterceptor.getAuthorizationToken();
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
