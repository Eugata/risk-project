package com.kkcloud.risk.dto;

import lombok.Data;
import java.util.List;

@Data
public class ViewDetailDTO {
    private int entry_id;
    private String user_email;
    private List<Integer> entry_ids;
    private int audit_id;
    private String operation;
    private String updated_at;
    private String updated_by;
}
