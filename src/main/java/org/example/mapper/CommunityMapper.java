package org.example.mapper;

import org.example.entity.Community;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CommunityMapper {
    Community selectById(Integer id);
    List<Community> selectAll();
    int insert(Community community);
    int update(Community community);
    int deleteById(Integer id);
}
