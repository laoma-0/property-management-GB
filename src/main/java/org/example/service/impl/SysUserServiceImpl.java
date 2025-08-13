package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.SysUser;
import org.example.mapper.SysUserMapper;
import org.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserById(Long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public List<SysUser> getAllUsers() {
        return sysUserMapper.selectAll();
    }

    @Override
    public PageInfo<SysUser> getUsersByPage(String username, String realName, String phone, Integer userType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> users = sysUserMapper.selectByCondition(username, realName, phone, userType);
        return new PageInfo<>(users);
    }

    @Override
    public int addUser(SysUser user) {
        return sysUserMapper.insert(user);
    }

    @Override
    public int updateUser(SysUser user) {
        return sysUserMapper.update(user);
    }

    @Override
    public int deleteUserById(Long id) {
        return sysUserMapper.deleteById(id);
    }
}