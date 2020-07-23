package teamg.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamg.spring.boot.model.User;
import teamg.spring.boot.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/api/rest/User.svc")
public class UserController {
    private static Logger LOG
            = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        LOG.info("getUserById: " + id);
        return userService.getUserById(id);
    }

}
