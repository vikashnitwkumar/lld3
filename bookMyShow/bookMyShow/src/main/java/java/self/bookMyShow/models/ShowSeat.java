package java.self.bookMyShow.models;

import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;

public class ShowSeat extends Serializers.Base {
    private Seat seat;
}


//- To ensure Proper contracts are there. Spring Data JPA is the interface for all SQL
// DB’s.
//- Basically why JPA is there so Other ORM’s don’t change the Syntax as per their Choice.
//        - Hibernate has to follow JPA contracts.
//        - Spring Data JPA → is an interface which contains methods which are
//        implemented by Hibernate.

// How will the ORM know which table to create?