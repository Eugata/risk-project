package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.AddDetailDTO;
import com.kkcloud.risk.dto.PermissionDTO;
import com.kkcloud.risk.model.PermissionDetail;
import java.util.List;

public interface DetailService {
    abstract List<PermissionDetail> getDetails(PermissionDTO permissionDTO);
    abstract void savePermissionDetail(AddDetailDTO addDetailDTO);
}
