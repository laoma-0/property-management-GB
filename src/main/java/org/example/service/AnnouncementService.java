package org.example.service;

import org.example.entity.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement getAnnouncementById(Long id);
    List<Announcement> getAllAnnouncements();
    int addAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncementById(Long id);
}
