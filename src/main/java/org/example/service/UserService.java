package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.User;
import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    List<User> getAllUsers();
    List<User> getUsersByCondition(String username, String name, String phone);
    PageInfo<User> getUsersByPage(int pageNum, int pageSize);
    PageInfo<User> getUsersByPageAndCondition(String username, String name, String phone, int pageNum, int pageSize);
    int addUser(User user);
    int updateUser(User user);
    int deleteUserById(Integer id);
}