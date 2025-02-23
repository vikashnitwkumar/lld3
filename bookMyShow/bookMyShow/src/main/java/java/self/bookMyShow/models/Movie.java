package java.self.bookMyShow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

@Getter
@Setter
public class Movie extends BaseModel {
    private String title;
    private String director;
    private String genre;
    private List<String> actors;
    private List<Language> languages;
    private Date releaseDate;
    private double length;
}