package com.kkcloud.risk.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RiskEntryLogDTO {

    private int entry_id;//Automatic

    @Length(max= 50, message = "Member Name should not exceed 50 characters! " )
    private String member_name;

    @Length(max= 50, message = "Member Account should not exceed 50 characters! ")
    private String member_account;

    @Length(max= 50, message = "Depositor Name should not exceed 50 characters! ")
    private String depositor_name;

    @Length(max= 50, message = "Bank Account Number should not exceed 50 characters! ")
    private String bank_account_no;

    @Length(max= 100, message = "Entry Reason should not exceed 100 characters! ")
    private String entry_reason;

    @Length(max= 50, message = "Contact Number should not exceed 50 characters! ")
    private String contact_number;

    @Length(max= 100, message = "Remarks should not exceed 100 characters! ")
    private String remarks;

    @NotNull(message = "Risk ID should not be empty, insert numerical value! " )
    @Min(value = 1)
    @Max(value = 4)
    private int risk_id;

    private String risk_name;
    private String updated_at;
    private String updated_by;
    private Boolean is_deleted;
    private int log_id;
    private int user_id;
    private String operation;
    private String field_type;
    private String before_value;
    private String updated_value;
    private String updated_date;
    private int audit_id;
    private String user_email;
    private String user_name;


}
