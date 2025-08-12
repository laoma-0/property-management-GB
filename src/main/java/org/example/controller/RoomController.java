package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.Room;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 房间管理控制器
 * 处理小区房间信息的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/rooms")
@Tag(name = "房间管理", description = "小区房间信息相关接口（CRUD操作）")
public class RoomController {
    @Autowired
    private RoomService roomService;

    /**
     * 根据ID查询房间
     * @param id 房间ID（长整型）
     * @return 包含房间详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询房间", description = "通过房间ID获取房间信息（楼栋号、户型、业主等）")
    public Result<Room> getRoomById(
            @Parameter(description = "房间ID", required = true)
            @PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return Result.success(room);
    }

    /**
     * 查询所有房间
     * @return 包含房间列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有房间", description = "获取系统中所有小区房间的列表信息")
    public Result<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return Result.success(rooms);
    }

    /**
     * 新增房间
     * @param room 房间实体（包含楼栋ID、房号、面积等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增房间", description = "添加新的房间信息到系统")
    public Result<Integer> addRoom(
            @Parameter(description = "房间信息", required = true)
            @RequestBody Room room) {
        int rows = roomService.addRoom(room);
        return Result.success(rows);
    }

    /**
     * 修改房间
     * @param room 房间实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改房间", description = "更新房间信息（如业主变更、状态更新等）")
    public Result<Integer> updateRoom(
            @Parameter(description = "更新后的房间信息", required = true)
            @RequestBody Room room) {
        int rows = roomService.updateRoom(room);
        return Result.success(rows);
    }

    /**
     * 根据ID删除房间
     * @param id 房间ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除房间", description = "通过房间ID删除指定房间信息")
    public Result<Integer> deleteRoom(
            @Parameter(description = "房间ID", required = true)
            @PathVariable Long id) {
        int rows = roomService.deleteRoomById(id);
        return Result.success(rows);
    }
}