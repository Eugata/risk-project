package com.kkcloud.risk.dto;

import lombok.Data;
import java.util.List;

@Data
public class ViewLogsDetailsDTO {
    private int audit_id;
    private String user_name;
    private String updated_date;
    private List<ViewLogsDTO> details;
}
