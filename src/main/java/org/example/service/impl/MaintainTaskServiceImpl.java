package org.example.service.impl;

import org.example.entity.MaintainTask;
import org.example.mapper.MaintainTaskMapper;
import org.example.service.MaintainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintainTaskServiceImpl implements MaintainTaskService {
    @Autowired
    private MaintainTaskMapper maintainTaskMapper;

    @Override
    public MaintainTask getMaintainTaskById(Long id) {
        return maintainTaskMapper.selectById(id);
    }

    @Override
    public List<MaintainTask> getAllMaintainTasks() {
        return maintainTaskMapper.selectAll();
    }

    @Override
    public int addMaintainTask(MaintainTask task) {
        return maintainTaskMapper.insert(task);
    }

    @Override
    public int updateMaintainTask(MaintainTask task) {
        return maintainTaskMapper.update(task);
    }

    @Override
    public int deleteMaintainTaskById(Long id) {
        return maintainTaskMapper.deleteById(id);
    }
}
