package teamg.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import teamg.spring.boot.exception.UserNotFoundException;
import teamg.spring.boot.model.User;
import teamg.spring.boot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        Iterable<User> iterable
                = userRepository.findAll();
        List<User> result
                = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public User getUserById(Long id) {return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    "User with id: " + id + " not found!")); }
}
