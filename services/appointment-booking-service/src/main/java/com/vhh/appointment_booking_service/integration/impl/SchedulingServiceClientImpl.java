package com.vhh.appointment_booking_service.integration.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vhh.appointment_booking_service.dto.request.AppointmentCreationRequest;
import com.vhh.appointment_booking_service.dto.request.SchedulingRequestPayload;
import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
import com.vhh.appointment_booking_service.dto.response.SchedulingResponsePayload;
import com.vhh.appointment_booking_service.integration.SchedulingServiceClient;

import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulingServiceClientImpl implements SchedulingServiceClient {

    private final RestTemplate restTemplate;
    @Value("${app.services.scheduling.url}")
    private String schedulingServiceBaseUrl;

    @Override
    public ResponseEntity<AppointmentResponse> bookSlot(AppointmentCreationRequest payload) {
        String url = UriComponentsBuilder.fromUriString(schedulingServiceBaseUrl)
                .path("/slots/book")
                .build()
                .toUriString();

        try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> entity = new HttpEntity<>(payload, headers);
                ResponseEntity<AppointmentResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<AppointmentResponse>() {}
                );
                return response;
        } catch (Exception ex) {
            throw ex; 
        } 
    }

    @Override
    public SchedulingResponsePayload updateSlot(Long appointmentId, SchedulingRequestPayload payload) {
        String url = UriComponentsBuilder.fromUriString(schedulingServiceBaseUrl)
                .path("/slots/{appointmentId}/update")
                .buildAndExpand(Map.of("appointmentId", appointmentId))
                .toUriString();

        try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Object> entity = new HttpEntity<>(payload, headers);
                ResponseEntity<SchedulingResponsePayload> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    new ParameterizedTypeReference<SchedulingResponsePayload>() {}
                );
                return response.getBody();
        } catch (Exception ex) {
            throw ex; 
        } 
    }

    @Override
    public Mono<Void> cancelSlot(Long appointmentId) {
        // log.debug("Calling Scheduling Service - cancelSlot for ID {}", appointmentId);
        // return webClient.post() // Giả sử API hủy là POST (hoặc DELETE)
        //         .uri("/slots/{appointmentId}/cancel", appointmentId)
        //         .retrieve()
        //          .onStatus(status -> status == HttpStatus.NOT_FOUND,
        //                 clientResponse -> Mono.error(new ResourceNotFoundException("Appointment not found in Scheduling Service for cancellation, ID: " + appointmentId)))
        //          .onStatus(status -> status.isError(),
        //                  clientResponse -> clientResponse.createException()
        //                          .flatMap(ex -> {
        //                              log.error("Error calling Scheduling Service - cancelSlot: Status={}, Body={}", ex.getStatusCode(), ex.getResponseBodyAsString(), ex);
        //                              return Mono.error(ex);
        //                          }))
        //         .bodyToMono(Void.class) // Trả về Mono<Void> khi thành công (thường là 200 OK hoặc 204 No Content từ service kia)
        //         .doOnSuccess(v -> log.info("Scheduling Service response - cancelSlot successful for ID {}", appointmentId));
        return null;
    }

    @Override
    public SchedulingResponsePayload getAppointmentDetails(Long appointmentId) {

        String url = UriComponentsBuilder.fromUriString(schedulingServiceBaseUrl) // Sử dụng fromUriString thay vì fromHttpUrl
                .path("/{appointmentId}")
                .buildAndExpand(Map.of("appointmentId", appointmentId)) // Truyền path variable
                .toUriString();

        try {
            ResponseEntity<SchedulingResponsePayload> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SchedulingResponsePayload>() {}
            );
            return response.getBody();
        } catch (Exception ex) {
            throw ex; 
        } 
}
}