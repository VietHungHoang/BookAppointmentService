package com.vhh.appointment_booking_service.messaging.publisher;
// Lớp này sẽ sử dụng RabbitTemplate để gửi message đến exchange.

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.vhh.appointment_booking_service.config.RabbitMQConfig;
import com.vhh.appointment_booking_service.dto.NotificationDTO;

@Component
@RequiredArgsConstructor // Inject RabbitTemplate
@Slf4j
public class AppointmentEventPublisher {

    private final AmqpTemplate rabbitTemplate;

    public void sendUser(NotificationDTO notificationDTO) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            notificationDTO
        );
    }
}