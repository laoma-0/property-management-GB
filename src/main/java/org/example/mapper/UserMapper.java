package org.example.mapper;

import org.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Long id);
    User selectByUsername(String username); // 添加根据用户名查询方法
    List<User> selectAll();
    List<User> selectByCondition(@Param("username") String username, @Param("name") String name, @Param("phone") String phone);
    int insert(User user);
    int update(User user);
    int deleteById(Long id);
}