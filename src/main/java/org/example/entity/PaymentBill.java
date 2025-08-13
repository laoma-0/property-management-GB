package org.example.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentBill {
    private Long billId;
    private Long roomId;
    private Integer feeId;
    private String period; // 缴费周期，如"2023-01"
    private BigDecimal amount; // 缴费金额
    private Integer status; // 缴费状态：0-未缴费，1-已缴费
    private Date dueDate; // 截止日期
    private Date paymentTime; // 缴费时间
    private Date createTime; // 创建时间

    // 关联字段：房间信息
    private Room room;
    // 关联字段：费用类型信息
    private FeeType feeType;
}