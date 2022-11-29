package com.kkcloud.risk.dto;

import lombok.Data;

@Data
public class UserDTO {
    public UserDTO(){}

    private int user_id;
    private String detail_name;
    private Boolean is_allowed;

    private int page_no;
    private int page_size;
}