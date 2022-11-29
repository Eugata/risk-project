package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.AddDetailDTO;
import com.kkcloud.risk.dto.PermissionDTO;
import com.kkcloud.risk.model.PermissionDetail;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DetailPermissionMapper {
    List<PermissionDetail> findById(PermissionDTO permissionDTO);
    void savePermissionDetail(AddDetailDTO addDetailDTO);
}
