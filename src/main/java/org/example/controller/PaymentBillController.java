package org.example.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.common.PageResult;
import org.example.common.Result;
import org.example.entity.PaymentBill;
import org.example.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 缴费账单管理控制器
 * 处理业主缴费账单的查询、创建、修改、删除等请求
 */
@RestController
@RequestMapping("/payment-bills")
@Tag(name = "缴费账单管理", description = "业主物业费账单相关接口（生成、查询、缴费状态等）")
public class PaymentBillController {
    @Autowired
    private PaymentBillService paymentBillService;

    /**
     * 根据ID查询缴费账单
     * @param id 账单ID（长整型）
     * @return 包含账单详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询缴费账单", description = "通过账单ID获取账单详情（费用类型、金额、缴费状态等）")
    public Result<PaymentBill> getPaymentBillById(
            @Parameter(description = "缴费账单ID", required = true)
            @PathVariable Long id) {
        PaymentBill bill = paymentBillService.getPaymentBillById(id);
        return Result.success(bill);
    }

    /**
     * 查询所有缴费账单
     * @return 包含账单列表的Result对象
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有缴费账单", description = "获取系统中所有业主的缴费账单列表")
    public Result<List<PaymentBill>> getAllPaymentBills() {
        List<PaymentBill> bills = paymentBillService.getAllPaymentBills();
        return Result.success(bills);
    }

    /**
     * 分页查询缴费账单
     * @param roomId 房间ID（可选）
     * @param feeId 费用类型ID（可选）
     * @param status 缴费状态（可选）
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页记录数
     * @return 包含分页账单列表的PageResult对象
     */
    @GetMapping
    @Operation(summary = "分页查询缴费账单", description = "分页获取缴费账单列表，支持按房间、费用类型、状态搜索")
    public PageResult<PaymentBill> getPaymentBillsByPage(
            @Parameter(description = "房间ID")
            @RequestParam(required = false) Long roomId,
            @Parameter(description = "费用类型ID")
            @RequestParam(required = false) Integer feeId,
            @Parameter(description = "缴费状态（0-未缴费，1-已缴费）")
            @RequestParam(required = false) Integer status,
            @Parameter(description = "页码（从1开始）")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "页码必须大于0") int pageNum,
            @Parameter(description = "每页记录数")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页记录数必须大于0") int pageSize) {
        
        PageInfo<PaymentBill> pageInfo;
        // 如果有搜索条件，则按条件搜索
        if (roomId != null || feeId != null || status != null) {
            pageInfo = paymentBillService.getPaymentBillsByPageAndCondition(roomId, feeId, status, pageNum, pageSize);
        } else {
            // 否则查询所有账单
            pageInfo = paymentBillService.getPaymentBillsByPage(pageNum, pageSize);
        }
        
        return PageResult.success(pageInfo.getList(), pageInfo.getTotal(), pageNum, pageSize);
    }

    /**
     * 新增缴费账单
     * @param bill 账单实体（包含业主ID、费用类型、金额等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增缴费账单", description = "生成新的业主缴费账单")
    public Result<Integer> addPaymentBill(
            @Parameter(description = "账单信息", required = true)
            @Valid @RequestBody PaymentBill bill) {
        // 设置创建时间
        Date now = new Date();
        bill.setCreateTime(now);
        int rows = paymentBillService.addPaymentBill(bill);
        return Result.success(rows);
    }

    /**
     * 修改缴费账单
     * @param bill 账单实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改缴费账单", description = "更新缴费账单信息")
    public Result<Integer> updatePaymentBill(
            @Parameter(description = "更新后的账单信息", required = true)
            @Valid @RequestBody PaymentBill bill) {
        int rows = paymentBillService.updatePaymentBill(bill);
        return Result.success(rows);
    }

    /**
     * 根据ID删除缴费账单
     * @param id 账单ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除缴费账单", description = "通过账单ID删除指定缴费账单")
    public Result<Integer> deletePaymentBill(
            @Parameter(description = "缴费账单ID", required = true)
            @PathVariable Long id) {
        int rows = paymentBillService.deletePaymentBillById(id);
        return Result.success(rows);
    }
}