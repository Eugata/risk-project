package com.kkcloud.risk.model;

import lombok.Data;

@Data
public class RiskEntryExport {
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
     * 风险id
     **/
    private int risk_id;
    /***
     * 操作员
     **/
    private String created_by;
}
