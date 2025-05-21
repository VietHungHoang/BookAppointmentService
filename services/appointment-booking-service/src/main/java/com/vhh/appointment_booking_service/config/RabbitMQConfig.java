package com.vhh.appointment_booking_service.config;
//Định nghĩa Exchange, các Queues (nếu service này cũng cần listen - hiện tại chưa cần), và các Bindings. Chúng ta sẽ dùng một Topic Exchange để linh hoạt trong việc routing messages.

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // --- Tên cho Exchange và Routing Keys ---
    public static final String EXCHANGE_NAME = "appointment.exchange";
    public static final String ROUTING_KEY = "appointment.confirmed";
    public static final String APPOINTMENT_UPDATED_ROUTING_KEY = "appointment.updated";
    public static final String APPOINTMENT_CANCELLED_CUSTOMER_ROUTING_KEY = "appointment.cancelled.customer";
    public static final String APPOINTMENT_CANCELLED_STAFF_ROUTING_KEY = "appointment.cancelled.staff";

    // --- Định nghĩa Exchange ---
    @Bean
    TopicExchange appointmentExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

     @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(jsonConverter());
        return template;
    }

    // --- Cấu hình Message Converter ---
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // --- Cấu hình RabbitTemplate ---
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
