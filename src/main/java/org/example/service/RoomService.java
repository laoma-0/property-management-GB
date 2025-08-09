package org.example.service;

import org.example.entity.Room;
import java.util.List;

public interface RoomService {
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    int addRoom(Room room);
    int updateRoom(Room room);
    int deleteRoomById(Long id);
}
