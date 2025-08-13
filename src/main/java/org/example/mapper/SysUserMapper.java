package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.SysUser;
import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser selectById(Long id);
    List<SysUser> selectAll();
    List<SysUser> selectByCondition(@Param("username") String username, @Param("realName") String realName, @Param("phone") String phone, @Param("userType") Integer userType);
    int insert(SysUser user);
    int update(SysUser user);
    int deleteById(Long id);
}