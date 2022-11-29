package com.kkcloud.risk.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RiskEntry {

    @Column(name = "entry_id")
    private int entry_id; //null when it is Integer, Connected to entry_id of entry_logs and risk_entry

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

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "risk_id")
    private int risk_id;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @Column(name = "risk_name")
    private String risk_name;

}
