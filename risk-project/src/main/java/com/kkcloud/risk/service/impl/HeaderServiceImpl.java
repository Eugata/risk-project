package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.mapper.HeaderMapper;
import com.kkcloud.risk.mapper.HeaderPermissionMapper;
import com.kkcloud.risk.model.Header;
import com.kkcloud.risk.model.PermissionHeader;
import com.kkcloud.risk.service.iface.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeaderServiceImpl implements HeaderService {

    @Autowired
    HeaderPermissionMapper headerPermissionMapper;
    @Autowired
    HeaderMapper headerMapper;

    @Override
    public List<PermissionHeader> getHeader(HeaderDTO headerDTO) {
        return headerPermissionMapper.findById(headerDTO);
    }

    @Override
    public void savePermissionHead(HeaderDTO headerDTO) {
        HeaderDTO headerDTO1 = new HeaderDTO();
        headerDTO1.setHeader_name(headerDTO.getHeader_name());
        headerDTO1.setCreated_at(LocalDateTime.now());
        headerDTO1.setUpdated_at(LocalDateTime.now());
        headerDTO1.setCreated_by(headerDTO.getCreated_by());
        headerDTO1.setUpdated_by(headerDTO.getUpdated_by());
        headerDTO1.setIs_deleted(headerDTO.getIs_deleted());
        headerMapper.saveHeader(headerDTO1);
    }

    @Override
    public List<Header> getAddHeader(HeaderDTO headerDTO) {
        return null;
    }

}
