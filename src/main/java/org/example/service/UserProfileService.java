package org.example.service;

import org.example.entity.UserProfile;
import java.util.List;

public interface UserProfileService {
    UserProfile getUserProfileById(Long id);
    List<UserProfile> getAllUserProfiles();
    int addUserProfile(UserProfile profile);
    int updateUserProfile(UserProfile profile);
    int deleteUserProfileById(Long id);
}
