package com.kkcloud.risk.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetailsPermissionUpdateDTO {
    private int user_id;
    private String header_name;
    private List<DetailsDTO> details;
}
