package com.patika.emlakburadapaymentservice.service;

import com.patika.emlakburadapaymentservice.dto.request.PackagePaymentRequest;
import com.patika.emlakburadapaymentservice.dto.response.PaymentResponse;
import com.patika.emlakburadapaymentservice.producer.dto.enums.PaymentStatus;
import com.patika.emlakburadapaymentservice.producer.NotificationProducer;
import com.patika.emlakburadapaymentservice.producer.dto.NotificationDto;
import com.patika.emlakburadapaymentservice.producer.dto.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentService {

    private final NotificationProducer notificationProducer;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentResponse pay(PackagePaymentRequest request) {

        log.info("payment succeedd: {}", request.getAmount());

        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.PUSH_NOTIFICATION, request.getPurchasePackageRequest().getUserId(), request.getPurchasePackageRequest().getPackageId()));

        return new PaymentResponse(PaymentStatus.SUCCESSFUL);
    }

    private NotificationDto prepareNotificationDto(NotificationType type, Long userId, Long packageId) {
        return NotificationDto.builder()
                .notificationType(type)
                .userId(userId)
                .packageId(packageId)
                .build();
    }
}
