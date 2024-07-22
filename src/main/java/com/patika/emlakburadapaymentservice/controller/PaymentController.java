package com.patika.emlakburadapaymentservice.controller;

import com.patika.emlakburadapaymentservice.dto.request.PackagePaymentRequest;
import com.patika.emlakburadapaymentservice.dto.response.GenericResponse;
import com.patika.emlakburadapaymentservice.dto.response.PaymentResponse;
import com.patika.emlakburadapaymentservice.model.Payment;
import com.patika.emlakburadapaymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pay")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public GenericResponse<PaymentResponse> pay(@RequestBody PackagePaymentRequest request){
        PaymentResponse paymentResponse = paymentService.pay(request);
        return GenericResponse.success(paymentResponse);
    }

    @GetMapping("list/user/{id}")
    public GenericResponse<List<Payment>> getUserPaymentsById(@PathVariable Long id) {
        return GenericResponse.success(paymentService.getPaymentsByUserId(id));
    }

}
