package org.example.controller;

import org.example.entity.UserProfile;
import org.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfile getUserProfileById(@PathVariable Long id) {
        return userProfileService.getUserProfileById(id);
    }

    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @PostMapping
    public int addUserProfile(@RequestBody UserProfile profile) {
        return userProfileService.addUserProfile(profile);
    }

    @PutMapping
    public int updateUserProfile(@RequestBody UserProfile profile) {
        return userProfileService.updateUserProfile(profile);
    }

    @DeleteMapping("/{id}")
    public int deleteUserProfile(@PathVariable Long id) {
        return userProfileService.deleteUserProfileById(id);
    }
}
