package org.example.service;

import org.example.entity.Device;
import java.util.List;

public interface DeviceService {
    Device getDeviceById(String id);
    List<Device> getAllDevices();
    int addDevice(Device device);
    int updateDevice(Device device);
    int deleteDeviceById(String id);
}
