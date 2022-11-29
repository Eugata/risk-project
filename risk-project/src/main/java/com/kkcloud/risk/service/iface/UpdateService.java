package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.DetailsPermissionUpdateDTO;
import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.dto.UserDetailsDTO;

import java.util.List;

public interface UpdateService {

    String updateDetails(List<DetailsPermissionUpdateDTO> detailsPermissionUpdateDTO);
    String updateHeaderDetails(HeaderDTO headerDTO);
//    String deleteHeaderDetails(HeaderDTO headerDTO);
}
