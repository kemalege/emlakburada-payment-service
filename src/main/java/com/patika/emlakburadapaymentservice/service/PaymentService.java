package com.patika.emlakburadapaymentservice.service;

import com.patika.emlakburadapaymentservice.converter.PaymentConverter;
import com.patika.emlakburadapaymentservice.dto.request.PackagePaymentRequest;
import com.patika.emlakburadapaymentservice.dto.response.PaymentResponse;
import com.patika.emlakburadapaymentservice.model.Payment;
import com.patika.emlakburadapaymentservice.model.enums.PaymentStatus;
import com.patika.emlakburadapaymentservice.producer.NotificationProducer;
import com.patika.emlakburadapaymentservice.producer.dto.NotificationDto;
import com.patika.emlakburadapaymentservice.producer.dto.enums.NotificationType;
import com.patika.emlakburadapaymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentService {

    private final NotificationProducer notificationProducer;
    private final PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentResponse pay(PackagePaymentRequest request) {

        log.info("payment succeedd: {}", request.getAmount());

        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.PUSH_NOTIFICATION, request));

        Payment payment = PaymentConverter.convert(request);

        paymentRepository.save(payment);

        return new PaymentResponse(PaymentStatus.SUCCESSFUL);
    }

    public List<Payment> getPaymentsByUserId(Long id) {
        return paymentRepository.findAllByUserId(id);
    }

    private NotificationDto prepareNotificationDto(NotificationType type, PackagePaymentRequest request) {
        return NotificationDto.builder()
                .notificationType(type)
                .userId(request.getPurchasePackageRequest().getUserId())
                .packageId(request.getPurchasePackageRequest().getPackageId())
                .build();
    }

}
