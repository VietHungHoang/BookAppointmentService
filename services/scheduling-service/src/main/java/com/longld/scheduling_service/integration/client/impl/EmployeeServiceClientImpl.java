package com.longld.scheduling_service.integration.client.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.longld.scheduling_service.dto.request.AvailableEmployeeInfoDTO;
import com.longld.scheduling_service.integration.client.EmployeeServiceClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceClientImpl implements EmployeeServiceClient {
    private final RestTemplate restTemplate;
    @Value("${app.services.employee.url}")
    private String employeeServiceBaseUrl;

    @Override
    public List<AvailableEmployeeInfoDTO> getFreeEmployeeByScheduleAndTime(Long serviceId, LocalDateTime time) {
        String url = UriComponentsBuilder.fromUriString(employeeServiceBaseUrl)
                .path("/{serviceId}/free")
                .queryParam("time", time)
                .buildAndExpand(Map.of("serviceId", serviceId))
                .toUriString();

        try {
            ResponseEntity<List<AvailableEmployeeInfoDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<AvailableEmployeeInfoDTO>>() {}
            );

            if(response.getStatusCode() == HttpStatus.OK) {
                System.out.println(response.getBody());
                return response.getBody();
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Bị null rồi");
        return null;
    }
}