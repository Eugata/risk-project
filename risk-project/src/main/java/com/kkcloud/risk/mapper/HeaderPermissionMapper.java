package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.model.PermissionHeader;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HeaderPermissionMapper {
    List<PermissionHeader> findById(HeaderDTO headerDTO);
}
