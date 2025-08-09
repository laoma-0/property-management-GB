package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class UserProfile {
    private Long profileId;
    private Long userId;
    private Integer paymentHabit;
    private String preferredChannel;
    private Date lastPaymentDate;
}
