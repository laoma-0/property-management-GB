package org.example.service.impl;

import org.example.entity.Room;
import org.example.mapper.RoomMapper;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Room getRoomById(Long id) {
        return roomMapper.selectById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomMapper.selectAll();
    }

    @Override
    public int addRoom(Room room) {
        return roomMapper.insert(room);
    }

    @Override
    public int updateRoom(Room room) {
        return roomMapper.update(room);
    }

    @Override
    public int deleteRoomById(Long id) {
        return roomMapper.deleteById(id);
    }
}
