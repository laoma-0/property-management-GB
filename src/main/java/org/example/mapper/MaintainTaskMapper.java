package org.example.mapper;

import org.example.entity.MaintainTask;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MaintainTaskMapper {
    MaintainTask selectById(Long taskId);
    List<MaintainTask> selectAll();
    int insert(MaintainTask task);
    int update(MaintainTask task);
    int deleteById(Long taskId);
}
