package org.example.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.common.PageResult;
import org.example.common.Result;
import org.example.entity.Announcement;
import org.example.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 公告管理控制器
 * 处理公告的查询、发布、修改、删除等请求
 */
@RestController
@RequestMapping("/announcements")
@Tag(name = "公告管理", description = "公告相关接口（发布、查询、编辑等）")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 根据ID查询公告
     * @param id 公告ID（长整型）
     * @return 包含公告详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询公告", description = "通过公告ID获取公告详情（标题、内容、发布时间等）")
    public Result<Announcement> getAnnouncementById(
            @Parameter(description = "公告ID", required = true)
            @PathVariable Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        return Result.success(announcement);
    }

    /**
     * 分页查询公告
     * @param title 公告标题（可选）
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页记录数
     * @return 包含分页公告列表的PageResult对象
     */
    @GetMapping
    @Operation(summary = "分页查询公告", description = "分页获取公告列表，支持按标题搜索")
    public PageResult<Announcement> getAnnouncementsByPage(
            @Parameter(description = "公告标题")
            @RequestParam(required = false) String title,
            @Parameter(description = "页码（从1开始）")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "页码必须大于0") int pageNum,
            @Parameter(description = "每页记录数")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页记录数必须大于0") int pageSize) {
        
        PageInfo<Announcement> pageInfo = announcementService.getAnnouncementsByPage(title, pageNum, pageSize);
        return PageResult.success(pageInfo.getList(), pageInfo.getTotal(), pageNum, pageSize);
    }

    /**
     * 查询所有公告
     * @return 包含公告列表的Result对象
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有公告", description = "获取系统中所有公告的列表（按发布时间排序）")
    public Result<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return Result.success(announcements);
    }

    /**
     * 新增公告
     * @param announcement 公告实体（包含标题、内容、发布人等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增公告", description = "发布新公告到系统中")
    public Result<Integer> addAnnouncement(
            @Parameter(description = "公告信息", required = true)
            @Valid @RequestBody Announcement announcement) {
        // 设置发布时间为当前时间
        announcement.setPublishTime(new Date());
        int rows = announcementService.addAnnouncement(announcement);
        return Result.success(rows);
    }

    /**
     * 修改公告
     * @param announcement 公告实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改公告", description = "更新已发布的公告信息")
    public Result<Integer> updateAnnouncement(
            @Parameter(description = "更新后的公告信息", required = true)
            @Valid @RequestBody Announcement announcement) {
        int rows = announcementService.updateAnnouncement(announcement);
        return Result.success(rows);
    }

    /**
     * 根据ID删除公告
     * @param id 公告ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除公告", description = "通过公告ID删除指定公告")
    public Result<Integer> deleteAnnouncement(
            @Parameter(description = "公告ID", required = true)
            @PathVariable Long id) {
        int rows = announcementService.deleteAnnouncementById(id);
        return Result.success(rows);
    }
}