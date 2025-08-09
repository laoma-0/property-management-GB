package org.example.mapper;

import org.example.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoomMapper {
    Room selectById(Long roomId);
    List<Room> selectAll();
    int insert(Room room);
    int update(Room room);
    int deleteById(Long roomId);
}
