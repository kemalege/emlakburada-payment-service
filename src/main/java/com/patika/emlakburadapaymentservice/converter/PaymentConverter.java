package com.patika.emlakburadapaymentservice.converter;

import com.patika.emlakburadapaymentservice.dto.request.PackagePaymentRequest;
import com.patika.emlakburadapaymentservice.model.Payment;

import java.time.LocalDateTime;

public class PaymentConverter {

    public static Payment convert(PackagePaymentRequest request) {
        return Payment.builder()
                .userId(request.getPurchasePackageRequest().getUserId())
                .createDate(LocalDateTime.now())
                .amount(request.getAmount())
                .packageId(request.getPurchasePackageRequest().getPackageId())
                .build();
    }
}
