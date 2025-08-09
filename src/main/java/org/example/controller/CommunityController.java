package org.example.controller;

import org.example.entity.Community;
import org.example.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @GetMapping("/{id}")
    public Community getCommunityById(@PathVariable Integer id) {
        return communityService.getCommunityById(id);
    }

    @GetMapping
    public List<Community> getAllCommunities() {
        return communityService.getAllCommunities();
    }

    @PostMapping
    public int addCommunity(@RequestBody Community community) {
        return communityService.addCommunity(community);
    }

    @PutMapping
    public int updateCommunity(@RequestBody Community community) {
        return communityService.updateCommunity(community);
    }

    @DeleteMapping("/{id}")
    public int deleteCommunity(@PathVariable Integer id) {
        return communityService.deleteCommunityById(id);
    }
}
