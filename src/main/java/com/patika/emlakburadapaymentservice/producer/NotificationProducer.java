package com.patika.emlakburadapaymentservice.producer;

import com.patika.emlakburadapaymentservice.config.RabbitConfig;
import com.patika.emlakburadapaymentservice.producer.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationProducer {

    private final AmqpTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;

    public void sendNotification(NotificationDto notificationDto) {
        rabbitTemplate.convertAndSend(rabbitConfig.getExchange(), rabbitConfig.getRoutingKey(), notificationDto);
        log.info("notification sent. exchange:{}", rabbitConfig.getExchange());
    }

}
