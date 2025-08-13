package org.example.mapper;

import org.example.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AnnouncementMapper {
    Announcement selectById(Long announceId);
    List<Announcement> selectAll();
    List<Announcement> selectByCondition(@Param("title") String title);
    int insert(Announcement announcement);
    int update(Announcement announcement);
    int deleteById(Long announceId);
}