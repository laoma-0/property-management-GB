package org.example.mapper;

import org.example.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DeviceMapper {
    Device selectById(String deviceId);
    List<Device> selectAll();
    int insert(Device device);
    int update(Device device);
    int deleteById(String deviceId);
}
