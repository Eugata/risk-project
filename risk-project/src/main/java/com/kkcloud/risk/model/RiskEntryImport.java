package com.kkcloud.risk.model;

import lombok.Data;

@Data
public class RiskEntryImport {
    /***
     * 栏目介绍id
     **/
    private int entry_id;
    /***
     * 会员name
     **/
    private String member_name;
    /***
     * 会员account
     **/
    private String member_account;
    /***
     * 存款人name
     **/
    private String depositor_name;
    /***
     * 银行账户number
     **/
    private String bank_account_no;
    /***
     * 参赛作品reason
     **/
    private String entry_reason;
    /***
     * 联系number
     **/
    private String contact_number;
    /***
     * 创建日期
     **/
    private String created_at;
    /***
     * 评论
     **/
    private String remarks;
    /***
     * 风险name
     **/
    private String risk_name;
    /***
     * 风险id
     **/
    private int risk_id;
    /***
     * 操作员
     **/
    private String created_by;

    private String updated_at;
    private String updated_by;


    public RiskEntryImport(String member_name, String member_account, String depositor_name, String bank_account_no, String entry_reason, String contact_number, String created_at, String remarks, int risk_id, String created_by) {
        this.member_name = member_name;
        this.member_account = member_account;
        this.depositor_name = depositor_name;
        this.bank_account_no = bank_account_no;
        this.entry_reason = entry_reason;
        this.contact_number = contact_number;
        this.created_at = created_at;
        this.remarks = remarks;
        this.risk_id = risk_id;
        this.created_by = created_by;
    }
}
