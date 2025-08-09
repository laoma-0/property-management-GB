package org.example.mapper;

import org.example.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser selectById(Long userId);
    List<SysUser> selectAll();
    int insert(SysUser user);
    int update(SysUser user);
    int deleteById(Long userId);
}
