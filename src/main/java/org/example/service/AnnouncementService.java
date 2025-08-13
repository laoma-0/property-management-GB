package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement getAnnouncementById(Long id);
    List<Announcement> getAllAnnouncements();
    PageInfo<Announcement> getAnnouncementsByPage(String title, int pageNum, int pageSize);
    int addAnnouncement(Announcement announcement);
    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncementById(Long id);
}