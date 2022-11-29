package com.kkcloud.risk.model;

import lombok.Data;
import javax.persistence.Column;

@Data
public class RiskEntryLog {

    @Column(name = "entry_id")
    private int entry_id;

    @Column(name = "member_name")
    private String member_name;

    @Column(name = "member_account")
    private String member_account;

    @Column(name = "depositor_name")
    private String depositor_name;

    @Column(name = "bank_account_no")
    private String bank_account_no;

    @Column(name = "entry_reason")
    private String entry_reason;

    @Column(name = "contact_number")
    private String contact_number;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "risk_id")
    private int risk_id;

    @Column(name = "risk_name")
    private String risk_name;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @Column(name = "log_id")
    private int log_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "operation")
    private String operation;

    @Column(name = "field_type")
    private String field_type;

    @Column(name = "before_value")
    private String before_value;

    @Column(name = "updated_value")
    private String updated_value;

    @Column(name = "update_date")
    private String updated_date;

    @Column(name = "audit_id")
    private int audit_id;

}
