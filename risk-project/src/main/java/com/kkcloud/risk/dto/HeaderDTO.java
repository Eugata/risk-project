package com.kkcloud.risk.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HeaderDTO {
    public HeaderDTO(){}

    private int header_id;
    private int user_id;
    private String user_name;
    private String header_name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String created_by;
    private String updated_by;
    private Boolean is_deleted;
}
