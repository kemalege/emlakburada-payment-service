package com.patika.emlakburadapaymentservice.dto.response;

import com.patika.emlakburadapaymentservice.producer.dto.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private PaymentStatus status;
}
