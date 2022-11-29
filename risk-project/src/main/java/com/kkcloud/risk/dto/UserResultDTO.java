package com.kkcloud.risk.dto;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class UserResultDTO {
    private int user_count;
    private List user_list;
}
