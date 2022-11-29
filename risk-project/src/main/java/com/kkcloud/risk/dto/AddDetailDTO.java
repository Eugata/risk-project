package com.kkcloud.risk.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AddDetailDTO {
    public AddDetailDTO(){}
    private int user_id;
    private int header_id;
    private String detail_name;
    private Boolean is_allowed;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String created_by;
    private String updated_by;
    private Boolean is_deleted;
}
