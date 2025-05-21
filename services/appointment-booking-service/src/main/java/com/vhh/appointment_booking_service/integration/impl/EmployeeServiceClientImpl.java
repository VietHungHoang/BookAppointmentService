package com.vhh.appointment_booking_service.integration.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vhh.appointment_booking_service.dto.EmployeeDTO;
import com.vhh.appointment_booking_service.integration.EmployeeServiceClient;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceClientImpl implements EmployeeServiceClient {

    private final RestTemplate restTemplate;
    @Value("${app.services.employee.url}")
    private String employeeServiceBaseUrl;

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeInfoById(Long employeeId) {
        String url = UriComponentsBuilder.fromUriString(employeeServiceBaseUrl)
                .path("/{employeeId}")
                .buildAndExpand(Map.of("employeeId", employeeId))
                .toUriString();

        try {
                ResponseEntity<EmployeeDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<EmployeeDTO>() {}
                );
                return response;
        } catch (Exception ex) {
            throw ex; 
        } 
}
}