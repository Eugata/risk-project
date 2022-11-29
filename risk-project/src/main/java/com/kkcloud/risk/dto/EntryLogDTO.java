package com.kkcloud.risk.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@Accessors(chain = true)
public class EntryLogDTO {
    private int log_id;
    private int entry_id;
    private int user_id;
    private String operation;
    private String field_type;
    private String before_value;
    private String updated_value;
    private String created_date;
    private String updated_date;
    private int audit_id;
}
