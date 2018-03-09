package mobi.uchicago.finalproject.service;

import java.util.List;

import mobi.uchicago.finalproject.model.User;

public interface UserService {
    List<User> getAllUsers();
    
    User getUserById(Integer id);
    
    User createUser(User user);
 
    User updateUser(Integer id, User userDetails);
 
    void deleteUser(Integer id);
}
