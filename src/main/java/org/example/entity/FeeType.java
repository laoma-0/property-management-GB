package org.example.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class FeeType {
    private Integer feeId;
    private String feeName;
    private BigDecimal unitPrice;
}
