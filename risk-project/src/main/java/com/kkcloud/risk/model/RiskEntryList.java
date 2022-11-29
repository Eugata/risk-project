package com.kkcloud.risk.model;

import lombok.Data;

@Data
public class RiskEntryList {

    private int entry_id;
    private String member_name;
    private String member_account;
    private String depositor_name;
    private String bank_account_no;
    private String entry_reason;
    private String contact_number;
    private String created_at;
    private String remarks;
    private String risk_name;
    private String created_by;
    private int risk_id;

}
