package org.example.mapper;

import org.example.entity.DeviceMetrics;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DeviceMetricsMapper {
    DeviceMetrics selectById(Long metricId);
    List<DeviceMetrics> selectAll();
    int insert(DeviceMetrics metrics);
    int update(DeviceMetrics metrics);
    int deleteById(Long metricId);
}
