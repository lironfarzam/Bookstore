package hac.ex4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * main class for ex4 application
 */
@SpringBootApplication
public class Ex4Application {

    /**
     * main function
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex4Application.class, args);
    }

}
