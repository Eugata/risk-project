package com.kkcloud.risk.model;

import lombok.Data;

@Data
public class PermissionUser {
    public PermissionUser(){}

    private String header_name;
    private String detail_name;
    private Boolean is_allowed;

}
