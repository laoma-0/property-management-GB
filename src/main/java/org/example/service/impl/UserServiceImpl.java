package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.common.BusinessException;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(400, "用户ID不能为空且必须大于0");
        }
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getUsersByCondition(String username, String name, String phone) {
        return userMapper.selectByCondition(username, name, phone);
    }

    @Override
    public PageInfo<User> getUsersByPage(int pageNum, int pageSize) {
        // 参数校验
        if (pageNum <= 0) {
            throw new BusinessException(400, "页码必须大于0");
        }
        if (pageSize <= 0) {
            throw new BusinessException(400, "每页记录数必须大于0");
        }
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询数据
        List<User> users = userMapper.selectAll();
        // 封装分页结果
        return new PageInfo<>(users);
    }

    @Override
    public PageInfo<User> getUsersByPageAndCondition(String username, String name, String phone, int pageNum, int pageSize) {
        // 参数校验
        if (pageNum <= 0) {
            throw new BusinessException(400, "页码必须大于0");
        }
        if (pageSize <= 0) {
            throw new BusinessException(400, "每页记录数必须大于0");
        }
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        // 根据条件查询数据
        List<User> users = userMapper.selectByCondition(username, name, phone);
        // 封装分页结果
        return new PageInfo<>(users);
    }

    @Override
    public int addUser(User user) {
        // 参数校验
        if (user == null) {
            throw new BusinessException(400, "用户信息不能为空");
        }
        if (!StringUtils.hasText(user.getUsername())) {
            throw new BusinessException(400, "用户名不能为空");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new BusinessException(400, "密码不能为空");
        }

        // 密码加密
        String encryptedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(encryptedPassword);

        // 设置创建时间和更新时间
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        // 参数校验
        if (user == null) {
            throw new BusinessException(400, "用户信息不能为空");
        }
        if (user.getId() == null || user.getId() <= 0) {
            throw new BusinessException(400, "用户ID不能为空且必须大于0");
        }

        // 如果密码有更新，则加密
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encryptedPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(encryptedPassword);
        }

        return userMapper.update(user);
    }

    @Override
    public int deleteUserById(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(400, "用户ID不能为空且必须大于0");
        }
        return userMapper.deleteById(id);
    }

    @Override
    public User authenticate(String username, String password) {
        // 根据用户名查找用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }

        // 验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (user.getPassword().equals(encryptedPassword)) {
            return user;
        }

        return null;
    }
}