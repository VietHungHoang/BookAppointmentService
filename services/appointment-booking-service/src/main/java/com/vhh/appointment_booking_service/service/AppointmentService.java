package com.vhh.appointment_booking_service.service;


import com.vhh.appointment_booking_service.dto.request.AppointmentCreationRequest;
import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
import com.vhh.appointment_booking_service.exception.BadRequestException;
import com.vhh.appointment_booking_service.exception.OperationFailedException;
import com.vhh.appointment_booking_service.exception.ResourceNotFoundException;
import com.vhh.appointment_booking_service.exception.SlotUnavailableException;

public interface AppointmentService {

    /**
     * Xử lý logic tạo lịch hẹn mới.
     * Bao gồm gọi Scheduling Service để kiểm tra và đặt chỗ, sau đó publish event.
     *
     * @param request Dữ liệu yêu cầu tạo lịch hẹn.
     * @return Mono chứa AppointmentResponse của lịch hẹn đã tạo.
     * @throws SlotUnavailableException nếu slot không có sẵn.
     * @throws BadRequestException nếu dữ liệu không hợp lệ.
     * @throws OperationFailedException nếu có lỗi khác (ví dụ: gọi service lỗi, publish message lỗi).
     */
    AppointmentResponse createAppointment(AppointmentCreationRequest request);

    /**
     * Xử lý logic cập nhật lịch hẹn.
     * Bao gồm gọi Scheduling Service để kiểm tra slot mới và cập nhật, sau đó publish event.
     *
     * @param appointmentId ID của lịch hẹn cần cập nhật.
     * @param request Dữ liệu yêu cầu cập nhật.
     * @return Mono chứa AppointmentResponse của lịch hẹn đã cập nhật.
     * @throws ResourceNotFoundException nếu không tìm thấy lịch hẹn.
     * @throws SlotUnavailableException nếu slot mới không có sẵn.
     * @throws BadRequestException nếu dữ liệu không hợp lệ.
     * @throws OperationFailedException nếu có lỗi khác.
     */
    AppointmentResponse updateAppointment(Long appointmentId, AppointmentCreationRequest request);

    /**
     * Xử lý logic hủy lịch hẹn.
     * Bao gồm gọi Scheduling Service để hủy, sau đó publish event.
     *
     * @param appointmentId ID của lịch hẹn cần hủy.
     * @return Mono<Void> hoàn thành khi hủy thành công.
     * @throws ResourceNotFoundException nếu không tìm thấy lịch hẹn.
     * @throws OperationFailedException nếu có lỗi khác.
     */
    void cancelAppointment(Long appointmentId);
}