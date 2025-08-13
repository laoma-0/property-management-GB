package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.SysUser;
import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser selectById(Long id);
    List<SysUser> selectAll();
    int insert(SysUser user);
    int update(SysUser user);
    int deleteById(Long id);
}