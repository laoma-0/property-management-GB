package org.example.controller;

import org.example.entity.MaintainTask;
import org.example.service.MaintainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maintain-tasks")
public class MaintainTaskController {
    @Autowired
    private MaintainTaskService maintainTaskService;

    @GetMapping("/{id}")
    public MaintainTask getMaintainTaskById(@PathVariable Long id) {
        return maintainTaskService.getMaintainTaskById(id);
    }

    @GetMapping
    public List<MaintainTask> getAllMaintainTasks() {
        return maintainTaskService.getAllMaintainTasks();
    }

    @PostMapping
    public int addMaintainTask(@RequestBody MaintainTask task) {
        return maintainTaskService.addMaintainTask(task);
    }

    @PutMapping
    public int updateMaintainTask(@RequestBody MaintainTask task) {
        return maintainTaskService.updateMaintainTask(task);
    }

    @DeleteMapping("/{id}")
    public int deleteMaintainTask(@PathVariable Long id) {
        return maintainTaskService.deleteMaintainTaskById(id);
    }
}
