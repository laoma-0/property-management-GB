package org.example.mapper;

import org.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Integer id);
    List<User> selectAll();
    int insert(User user);
    int update(User user);
    int deleteById(Integer id);
}
