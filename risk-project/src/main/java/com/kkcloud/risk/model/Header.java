package com.kkcloud.risk.model;

import lombok.Data;

@Data
public class Header {
    public Header(){}

    private int header_id;
    private String header_name;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
}
