package teamg.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

//import static javafx.scene.input.KeyCode.DOLLAR;

@SpringBootApplication
public class Application {

    private static Logger LOG
            = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("Application started");
        SpringApplication.run(Application.class, args);
    }
}