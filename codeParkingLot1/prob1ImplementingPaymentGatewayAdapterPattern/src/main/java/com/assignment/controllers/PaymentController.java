package com.assignment.controllers;

import com.assignment.dtos.MakePaymentRequestDto;
import com.assignment.dtos.MakePaymentResponseDto;
import com.assignment.dtos.ResponseStatus;
import com.assignment.exceptions.InvalidBillException;
import com.assignment.models.Payment;
import com.assignment.services.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {

        try{
            Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
            return from(payment);
        } catch (InvalidBillException e) {
            MakePaymentResponseDto response = new MakePaymentResponseDto();
            response.setResponseStatus(ResponseStatus.FAILURE);
            // Keep txnId and paymentStatus null as expected by tests
            return response;
        }
    }

    private MakePaymentResponseDto from(Payment payment){
        MakePaymentResponseDto makePaymentResponseDto = new MakePaymentResponseDto();
        makePaymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
        makePaymentResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        makePaymentResponseDto.setTxnId(payment.getTxnId());
        return makePaymentResponseDto;
    }
}