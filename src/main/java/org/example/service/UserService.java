package org.example.service;

import org.example.entity.User;
import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    List<User> getAllUsers();
    int addUser(User user);
    int updateUser(User user);
    int deleteUserById(Integer id);
}
