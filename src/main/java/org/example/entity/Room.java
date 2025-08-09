package org.example.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Room {
    private Long roomId;
    private Integer buildingId;
    private String roomNumber;
    private Long ownerId;
    private BigDecimal area;
}
