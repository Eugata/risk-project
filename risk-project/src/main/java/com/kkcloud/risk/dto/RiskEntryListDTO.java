package com.kkcloud.risk.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class RiskEntryListDTO  implements Serializable {

    private int entry_id;
    private String member_name;
    private String member_account;
    private String depositor_name;
    private String bank_account_no;
    private String entry_reason;
    private String contact_number;
    private String created_at;
    private String remarks;
    private int risk_id;
    private String created_by;
    private Date updated_at;
    private String updated_by;
    private Boolean is_deleted;

    private String risk_name;
    private String file_name;
    private int page_no;
    private int page_size;
}
