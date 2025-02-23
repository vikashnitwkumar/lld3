package java.self.bookMyShow.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Region extends BaseModel {
    private String name;
    List<Theatre> theatreList;
}
