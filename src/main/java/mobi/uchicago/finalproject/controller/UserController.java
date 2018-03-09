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

import mobi.uchicago.finalproject.model.User;
import mobi.uchicago.finalproject.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping("/user")
    public User createuser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }
    
    @GetMapping("/user/{id}")
    public User getuserById(@PathVariable(value = "id") Integer userId) {
        return userService.getUserById(userId);
    }
    
    @PutMapping("/users/{id}")
    public User updateuser(@PathVariable(value = "id") Integer userId,
                                            @Valid @RequestBody User userDetails) {
        return userService.updateUser(userId, userDetails);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable(value = "id") Integer userId) { 
        userService.deleteUser(userId);

        return ResponseEntity.ok().build();
    }
}
