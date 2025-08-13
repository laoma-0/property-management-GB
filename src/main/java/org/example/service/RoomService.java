package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.Room;
import java.util.List;

public interface RoomService {
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    PageInfo<Room> getRoomsByPage(Integer buildingId, String roomNumber, int pageNum, int pageSize);
    int addRoom(Room room);
    int updateRoom(Room room);
    int deleteRoomById(Long id);
}