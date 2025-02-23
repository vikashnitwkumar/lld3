package java.self.bookMyShow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseModel {
    private  int amount;
    private String refNumber;
    private Date date;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private PaymentGateway paymentGateway;
    private BookingStatus bookingStatus;
}
