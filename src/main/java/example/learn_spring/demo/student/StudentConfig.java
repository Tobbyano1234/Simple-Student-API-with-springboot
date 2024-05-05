package example.learn_spring.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
//import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

//import static java.util.Calendar.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (
            StudentRepository repository
    ){
        return args -> {
            Student mary = new Student(
                    "Mary",
                    "mary@example.com",
                    LocalDate.of(2009, JANUARY, 10)
            );

            Student yemi = new Student(
                    "Yemi",
                    "yemi@example.com",
                    LocalDate.of(2020, JANUARY, 10)
                    );

            repository.saveAll(List.of(mary, yemi));
        };
    }
}
