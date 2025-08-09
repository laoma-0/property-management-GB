package org.example.service.impl;

import org.example.entity.UserProfile;
import org.example.mapper.UserProfileMapper;
import org.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileMapper userProfileMapper;

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
