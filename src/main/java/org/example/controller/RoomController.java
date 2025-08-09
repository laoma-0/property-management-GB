package org.example.controller;

import org.example.entity.Room;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public int addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping
    public int updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/{id}")
    public int deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoomById(id);
    }
}
