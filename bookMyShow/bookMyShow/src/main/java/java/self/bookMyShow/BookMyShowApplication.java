package java.self.bookMyShow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.self.bookMyShow.models.BaseModel;


@SpringBootApplication
public class BookMyShowLld15JuneApplication implements CommandLineRunner {
	@Autowired
	UserController userController;

	//we want everything in the run method to run in spring context, so to do that we did this in this manner.
	// This place is used to preprocess the task or execute some task we need to happen just after the Application starts runnung
	@Override
	public void run(String... args) throws Exception {
		SignUpRequestDTO requestDTO = new SignUpRequestDTO();
		requestDTO.setEmail("puneet@gmail.com");
		requestDTO.setPassword("12345");

		SignUpResponseDTO responseDTO = userController.signUp(requestDTO);
		System.out.println(responseDTO.getMessage());
	}

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowLld15JuneApplication.class, args);


	}

//    Cardinalities
//    Generate the table by running the code
//    -Agenda
//    - [ ]  Book My Ticket Functionality
//    - [ ]  Signup Functionality

}
