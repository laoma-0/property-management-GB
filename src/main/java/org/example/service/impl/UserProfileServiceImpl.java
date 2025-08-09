package org.example.service.impl;

import org.example.entity.UserProfile;
import org.example.mapper.UserProfileMapper;
import org.example.mapper.SysUserMapper;
import org.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserProfile getUserProfileById(Long id) {
        return userProfileMapper.selectById(id);
    }

    @Override
    public List<UserProfile> getAllUserProfiles() {
        return userProfileMapper.selectAll();
    }

    @Override
    public int addUserProfile(UserProfile profile) {
        // 校验userId是否存在
        if (profile.getUserId() == null || sysUserMapper.selectById(profile.getUserId()) == null) {
            throw new IllegalArgumentException("userId不存在，无法插入user_profile，请先添加用户或检查userId参数");
        }
        return userProfileMapper.insert(profile);
    }

    @Override
    public int updateUserProfile(UserProfile profile) {
        return userProfileMapper.update(profile);
    }

    @Override
    public int deleteUserProfileById(Long id) {
        return userProfileMapper.deleteById(id);
    }
}
