package org.example.service;

import org.example.entity.SysUser;
import java.util.List;

public interface SysUserService {
    SysUser getUserById(Long id);
    List<SysUser> getAllUsers();
    int addUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUserById(Long id);
}