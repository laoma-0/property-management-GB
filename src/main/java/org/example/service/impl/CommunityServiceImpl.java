package org.example.service.impl;

import org.example.entity.Community;
import org.example.mapper.CommunityMapper;
import org.example.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public Community getCommunityById(Integer id) {
        return communityMapper.selectById(id);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityMapper.selectAll();
    }

    @Override
    public int addCommunity(Community community) {
        return communityMapper.insert(community);
    }

    @Override
    public int updateCommunity(Community community) {
        return communityMapper.update(community);
    }

    @Override
    public int deleteCommunityById(Integer id) {
        return communityMapper.deleteById(id);
    }
}
