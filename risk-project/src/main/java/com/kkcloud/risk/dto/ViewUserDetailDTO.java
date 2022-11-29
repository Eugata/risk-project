package com.kkcloud.risk.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewUserDetailDTO {
    private int user_id;
    private String user_email;
    private List<Integer> user_ids;
    private String updated_at;
    private String updated_by;
}
