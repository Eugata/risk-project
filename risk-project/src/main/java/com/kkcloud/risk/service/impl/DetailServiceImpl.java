package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.DetailPermissionMapper;
import com.kkcloud.risk.model.PermissionDetail;
import com.kkcloud.risk.service.iface.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    DetailPermissionMapper detailPermissionMapper;

    @Override
    public List<PermissionDetail> getDetails(PermissionDTO permissionDTO) {
        return detailPermissionMapper.findById(permissionDTO);
    }

    @Override
    public void savePermissionDetail(AddDetailDTO addDetailDTO) {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        AddDetailDTO detailsNewValue = new AddDetailDTO();
        detailsNewValue.setUser_id(addDetailDTO.getUser_id());
        detailsNewValue.setHeader_id(addDetailDTO.getHeader_id());
        detailsNewValue.setDetail_name(addDetailDTO.getDetail_name());
        detailsNewValue.setIs_allowed(addDetailDTO.getIs_allowed());
        detailsNewValue.setCreated_at(LocalDateTime.now());
        detailsNewValue.setUpdated_at(LocalDateTime.now());
        detailsNewValue.setCreated_by(userName);
        detailsNewValue.setUpdated_by(userName);
        detailsNewValue.setIs_deleted(addDetailDTO.getIs_deleted());
        detailPermissionMapper.savePermissionDetail(detailsNewValue);


    }

}
