package com.longld.notification_service.service;

import com.longld.notification_service.config.RabbitMqConfig;
import com.longld.notification_service.dto.NotificationDTO;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    @Autowired
    private EmailNotificationService emailNotificationService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveUser(NotificationDTO notificationDTO) {
        System.out.println(notificationDTO);
        emailNotificationService.sendEmail("viethunghoang1508@gmail.com", "Đặt lịch hẹn thành công!", notificationDTO.getSuccessMessage());
    }

}
