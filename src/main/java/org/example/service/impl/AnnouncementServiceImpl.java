package org.example.service.impl;

import org.example.entity.Announcement;
import org.example.mapper.AnnouncementMapper;
import org.example.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Announcement getAnnouncementById(Long id) {
        return announcementMapper.selectById(id);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementMapper.selectAll();
    }

    @Override
    public int addAnnouncement(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    @Override
    public int updateAnnouncement(Announcement announcement) {
        return announcementMapper.update(announcement);
    }

    @Override
    public int deleteAnnouncementById(Long id) {
        return announcementMapper.deleteById(id);
    }
}
