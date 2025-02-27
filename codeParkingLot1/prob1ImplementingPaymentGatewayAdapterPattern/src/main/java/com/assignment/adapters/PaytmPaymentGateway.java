package com.assignment.adapters;

import com.assignment.libraries.paytm.PaytmApi;
import com.assignment.libraries.paytm.PaytmPaymentResponse;
import com.assignment.models.Payment;
import com.assignment.models.PaymentStatus;
import com.assignment.services.PaymentService;

public class PaytmPaymentGateway implements PaymentGatewayAdapter{
   PaytmApi paytmApi;

   public PaytmPaymentGateway(){ 
        this.paytmApi = new PaytmApi();
   }
   public PaytmPaymentGateway(PaytmApi paytmApi){
       this.paytmApi = paytmApi;
   }
    @Override
    public Payment makePayment(long billId, double amount) {
       PaytmPaymentResponse paytmPaymentResponse = paytmApi.makePayment(billId, amount);
       return from(paytmPaymentResponse);
    }

    private Payment from(PaytmPaymentResponse paytmPaymentResponse){
       Payment payment = new Payment();
       payment.setPaymentStatus(getPaymentStatusFrom(paytmPaymentResponse.getPaymentStatus()));
       payment.setBillId(Long.parseLong(paytmPaymentResponse.getOrderId()));
       payment.setTxnId(paytmPaymentResponse.getTxnId());
       return payment;
    }

    private PaymentStatus getPaymentStatusFrom(String paytmResponsePaymentStatus){
       switch (paytmResponsePaymentStatus){
           case "SUCCESS":
               return PaymentStatus.SUCCESS;
           default:
               return PaymentStatus.FAILURE;
       }
    }
}
