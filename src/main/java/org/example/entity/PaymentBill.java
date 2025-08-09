package org.example.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentBill {
    private Long billId;
    private Long roomId;
    private Integer feeId;
    private String period;
    private BigDecimal amount;
    private Integer status;
    private Date dueDate;
    private Date paymentTime;
}
