package org.example.controller;

import org.example.entity.Device;
import org.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable String id) {
        return deviceService.getDeviceById(id);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public int addDevice(@RequestBody Device device) {
        return deviceService.addDevice(device);
    }

    @PutMapping
    public int updateDevice(@RequestBody Device device) {
        return deviceService.updateDevice(device);
    }

    @DeleteMapping("/{id}")
    public int deleteDevice(@PathVariable String id) {
        return deviceService.deleteDeviceById(id);
    }
}
