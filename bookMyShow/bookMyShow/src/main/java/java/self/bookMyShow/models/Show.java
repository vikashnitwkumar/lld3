package java.self.bookMyShow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Show extends BaseModel {
    private Movie movie;
    private Date startTIme;
    private int duration;
    private Screen Screen;
    private List<ShowSeat> showSeats;
    private List<ShowSeatType> showSeatTypes;
}
