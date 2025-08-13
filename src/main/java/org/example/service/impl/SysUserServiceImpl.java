package org.example.service.impl;

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