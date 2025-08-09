package org.example.service;

import org.example.entity.Community;
import java.util.List;

public interface CommunityService {
    Community getCommunityById(Integer id);
    List<Community> getAllCommunities();
    int addCommunity(Community community);
    int updateCommunity(Community community);
    int deleteCommunityById(Integer id);
}
