package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Announcement {
    private Long announceId;
    private String title;
    private String content;
    private Long publisherId;
    private Date publishTime;
    private Boolean isTop;
}
