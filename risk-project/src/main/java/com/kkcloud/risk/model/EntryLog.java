package com.kkcloud.risk.model;

import lombok.Data;
import java.util.Date;

@Data
public class EntryLog {
    private int log_id;
    private int entry_id;
    private int user_id;
    private String operation;
    private String field_type;
    private String before_value;
    private String updated_value;
    private Date created_date;
    private Date updated_date;
    private int audit_id;
}
