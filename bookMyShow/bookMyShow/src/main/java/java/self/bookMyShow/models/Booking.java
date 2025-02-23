package java.self.bookMyShow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Booking extends BaseModel{
    private  int amount;
    private List<Payment> payments;
    private User user;
    private Date date;
    private List<Seat> seats;
    private BookingStatus bookingStatus;
}