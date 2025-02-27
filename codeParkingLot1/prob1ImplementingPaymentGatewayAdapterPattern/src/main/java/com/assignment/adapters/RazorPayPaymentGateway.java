package com.assignment.adapters;

import com.assignment.libraries.razorpay.RazorpayApi;
import com.assignment.libraries.razorpay.RazorpayPaymentResponse;
import com.assignment.models.Payment;
import com.assignment.models.PaymentStatus;

public class RazorPayPaymentGateway implements PaymentGatewayAdapter{
    RazorpayApi razorpayApi;
    public RazorPayPaymentGateway(){
        this.razorpayApi = new RazorpayApi();
    }
    public  RazorPayPaymentGateway(RazorpayApi razorpayApi){
        this.razorpayApi = razorpayApi;
    }
    @Override
    public Payment makePayment(long billId, double amount) {
        RazorpayPaymentResponse razorpayPaymentResponse = razorpayApi.processPayment(billId, amount);
        return from(razorpayPaymentResponse);
    }
    private Payment from(RazorpayPaymentResponse razorpayPaymentResponse){
        Payment payment = new Payment();
        payment.setPaymentStatus(getPaymentStatusFrom(razorpayPaymentResponse.getPaymentStatus()));
        payment.setBillId(Long.parseLong(razorpayPaymentResponse.getOrderId()));
        payment.setTxnId(razorpayPaymentResponse.getTransactionId());
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
