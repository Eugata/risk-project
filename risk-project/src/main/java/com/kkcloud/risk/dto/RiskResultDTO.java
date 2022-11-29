package com.kkcloud.risk.dto;

import com.github.pagehelper.Page;
import lombok.Data;

@Data
public class RiskResultDTO {
    private int risk_count;
    private Page entry_list;
}