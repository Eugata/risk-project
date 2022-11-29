package com.kkcloud.risk.dto;

import lombok.Data;

@Data
public class ViewLogsDTO {
    //from entry_logs
    private String operation;
    private String field_type;
    private String before_value;
}
