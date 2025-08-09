package org.example.controller;

import org.example.entity.Announcement;
import org.example.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable Long id) {
        return announcementService.getAnnouncementById(id);
    }

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @PostMapping
    public int addAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);
    }

    @PutMapping
    public int updateAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.updateAnnouncement(announcement);
    }

    @DeleteMapping("/{id}")
    public int deleteAnnouncement(@PathVariable Long id) {
        return announcementService.deleteAnnouncementById(id);
    }
}
