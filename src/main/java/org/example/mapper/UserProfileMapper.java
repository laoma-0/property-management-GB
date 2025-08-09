package org.example.mapper;

import org.example.entity.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserProfileMapper {
    UserProfile selectById(Long profileId);
    List<UserProfile> selectAll();
    int insert(UserProfile profile);
    int update(UserProfile profile);
    int deleteById(Long profileId);
}
