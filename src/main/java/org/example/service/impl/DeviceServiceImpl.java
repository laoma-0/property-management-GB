package org.example.service.impl;

import org.example.entity.Device;
import org.example.mapper.DeviceMapper;
import org.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Device getDeviceById(String id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceMapper.selectAll();
    }

    @Override
    public int addDevice(Device device) {
        return deviceMapper.insert(device);
    }

    @Override
    public int updateDevice(Device device) {
        return deviceMapper.update(device);
    }

    @Override
    public int deleteDeviceById(String id) {
        return deviceMapper.deleteById(id);
    }
}
