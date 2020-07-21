package teamg.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


//import static javafx.scene.input.KeyCode.DOLLAR;

@SpringBootApplication
@RestController
public class Application {

    private static Logger LOG
            = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("Application started");
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }
}