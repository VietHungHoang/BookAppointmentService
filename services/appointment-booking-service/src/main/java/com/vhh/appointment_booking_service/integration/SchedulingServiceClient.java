package com.vhh.appointment_booking_service.integration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.vhh.appointment_booking_service.dto.request.AppointmentCreationRequest;
import com.vhh.appointment_booking_service.dto.request.SchedulingRequestPayload;
import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
import com.vhh.appointment_booking_service.dto.response.SchedulingResponsePayload;

import reactor.core.publisher.Mono;


public interface SchedulingServiceClient {

    /**
     * Gọi Scheduling Service để đặt một slot lịch hẹn.
     * Scheduling Service sẽ chịu trách nhiệm xử lý logic khóa (locking).
     *
     * @param payload Dữ liệu cần thiết để đặt lịch (customerId, employeeId, serviceId, startTime, endTime).
     * @return Thông tin chi tiết lịch hẹn đã được tạo bởi Scheduling Service.
     * @throws WebClientResponseException nếu Scheduling Service trả về lỗi (ví dụ: 409 Conflict - Slot đã bị đặt).
     */
    ResponseEntity<AppointmentResponse> bookSlot(AppointmentCreationRequest payload);

    /**
     * Gọi Scheduling Service để cập nhật một lịch hẹn.
     *
     * @param appointmentId ID lịch hẹn cần cập nhật.
     * @param payload Dữ liệu cập nhật.
     * @return Mono chứa thông tin chi tiết lịch hẹn đã được cập nhật.
     * @throws WebClientResponseException nếu Scheduling Service trả về lỗi (404 Not Found, 409 Conflict).
     */
    SchedulingResponsePayload updateSlot(Long appointmentId, SchedulingRequestPayload payload); // Sử dụng lại payload hoặc tạo DTO riêng

    /**
     * Gọi Scheduling Service để hủy một lịch hẹn.
     *
     * @param appointmentId ID lịch hẹn cần hủy.
     * @return Mono<Void> hoàn thành khi hủy thành công phía Scheduling Service.
     * @throws WebClientResponseException nếu Scheduling Service trả về lỗi (404 Not Found).
     */
    Mono<Void> cancelSlot(Long appointmentId);


     /**
     * Gọi Scheduling Service để lấy thông tin chi tiết một lịch hẹn.
     *
     * @param appointmentId ID lịch hẹn cần lấy.
     * @return Mono chứa thông tin chi tiết lịch hẹn.
     * @throws WebClientResponseException nếu Scheduling Service trả về lỗi 404 Not Found.
     */
    SchedulingResponsePayload getAppointmentDetails(Long appointmentId);

}