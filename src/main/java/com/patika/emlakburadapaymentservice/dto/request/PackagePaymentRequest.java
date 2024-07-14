package com.patika.emlakburadapaymentservice.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackagePaymentRequest {

    PurchasePackageRequest purchasePackageRequest;
    BigDecimal amount;

}
