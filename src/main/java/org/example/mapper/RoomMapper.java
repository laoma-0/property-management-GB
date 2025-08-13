package org.example.mapper;

import org.example.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RoomMapper {
    Room selectById(Long roomId);
    List<Room> selectAll();
    List<Room> selectByCondition(@Param("buildingId") Integer buildingId, @Param("roomNumber") String roomNumber);
    int insert(Room room);
    int update(Room room);
    int deleteById(Long roomId);
}