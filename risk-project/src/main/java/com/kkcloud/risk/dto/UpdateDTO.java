package com.kkcloud.risk.dto;

import lombok.Data;

@Data
public class UpdateDTO {

    private String user_email;
    private String detail_name;
    private Boolean is_allowed;
}
