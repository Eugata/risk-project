package com.kkcloud.risk.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateDetailNameAllDTO {
    public UpdateDetailNameAllDTO(){}
    private String detail_name;
    private String new_detail_name;
    //private String user_id;
    private String header_id;
    private LocalDateTime updated_at;
    private String username;

}
