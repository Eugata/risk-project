package com.kkcloud.risk.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDetailsDTO {
    public UserDetailsDTO(){}

    private String header_name;
    private List<DetailsDTO> details;
}
