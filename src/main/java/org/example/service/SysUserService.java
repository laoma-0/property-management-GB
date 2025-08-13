package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.SysUser;
import java.util.List;

public interface SysUserService {
    SysUser getUserById(Long id);
    List<SysUser> getAllUsers();
    PageInfo<SysUser> getUsersByPage(String username, String realName, String phone, Integer userType, int pageNum, int pageSize);
    int addUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUserById(Long id);
}