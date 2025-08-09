package org.example.service;

import org.example.entity.MaintainTask;
import java.util.List;

public interface MaintainTaskService {
    MaintainTask getMaintainTaskById(Long id);
    List<MaintainTask> getAllMaintainTasks();
    int addMaintainTask(MaintainTask task);
    int updateMaintainTask(MaintainTask task);
    int deleteMaintainTaskById(Long id);
}
