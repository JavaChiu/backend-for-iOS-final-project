package mobi.uchicago.finalproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobi.uchicago.finalproject.exception.ResourceNotFoundException;
import mobi.uchicago.finalproject.model.User;
import mobi.uchicago.finalproject.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PostMapping("/user")
    public User createuser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }
    
    @GetMapping("/user/{id}")
    public User getuserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
    
    @PutMapping("/users/{id}")
    public User updateuser(@PathVariable(value = "id") Long userId,
                                            @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        user.setDescription(userDetails.getDescription());
        user.setAddress(userDetails.getAddress());

        User updateduser = userRepository.save(user);
        return updateduser;
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
