package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.DetailsDTO;
import com.kkcloud.risk.dto.DetailsPermissionUpdateDTO;
import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.mapper.PermissionUpdateMapper;
import com.kkcloud.risk.mapper.UserPermissionMapper;
import com.kkcloud.risk.service.iface.UpdateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpdatePermissionServiceImpl implements UpdateService {

    @Autowired
    PermissionUpdateMapper permissionUpdateMapper;
    @Autowired
    UserPermissionMapper userPermissionMapper;

    @Override
    public String updateDetails(List<DetailsPermissionUpdateDTO> detailsPermissionUpdateDTO) {
        String headerName, resultVal = "", userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        int userId, initialUserId = 0;
        for (DetailsPermissionUpdateDTO detailsPermissionUpdateDTOInput : detailsPermissionUpdateDTO) {
            initialUserId = detailsPermissionUpdateDTOInput.getUser_id();
            break;
        }
        /*  Boolean isDeleted;*/
        Boolean isDeleted, isAllowed;
        isAllowed = userPermissionMapper.checkIfAllowed(initialUserId);

        isDeleted = userPermissionMapper.existingLoginById(initialUserId);

        if (isAllowed == true || isDeleted == true) {
            LocalDateTime createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now();
            int i = 0;
            for (DetailsPermissionUpdateDTO detailsPermissionUpdateDTOInput : detailsPermissionUpdateDTO) {
                headerName = detailsPermissionUpdateDTOInput.getHeader_name();
                userId = initialUserId;
                isDeleted = userPermissionMapper.existingLoginById(userId);
                if (isDeleted == true) {

                    for (DetailsDTO detailsDTO : detailsPermissionUpdateDTOInput.getDetails()) {
                        i++;
                        isDeleted = permissionUpdateMapper.validateExistingDetails(userId, headerName, detailsDTO.getDetail_name());

                        if (isDeleted == true) {
                            permissionUpdateMapper.updateDetails(detailsDTO.getDetail_name(), detailsDTO.getIs_allowed(), userId, headerName);
                        } else {
                            permissionUpdateMapper.addPermissionOfUser(userId, headerName, detailsDTO.getDetail_name(),
                                    detailsDTO.getIs_allowed(),
                                    createdAt, updatedAt, userName);
                        }
                    }
                } else {
                    resultVal = "No user found";
                    break;
                }
            }
        } else {
            resultVal = "No permission";
        }

        return resultVal;
    }

    @Override
    public String updateHeaderDetails(HeaderDTO headerDTO) {
        Boolean isDeleted = permissionUpdateMapper.validateHeader(headerDTO);

        if (isDeleted == true) {
            headerDTO.setUpdated_at(LocalDateTime.now());
            permissionUpdateMapper.updateHeaderDetails(headerDTO);
            return "";
        } else {
            return "Same Value!";
        }
    }

//    @Override
//    public String deleteHeaderDetails(HeaderDTO headerDTO){
//        String userName;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails)principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        LocalDateTime updatedAt = LocalDateTime.now();
//        Boolean isDeleted = permissionUpdateMapper.validateDeleteHeader(headerDTO);
//
//        if(isDeleted==true){
//            permissionUpdateMapper.deleteHeaderDetails(headerDTO.getHeader_id(), userName, updatedAt);
//            return "";
//        }
//        else{
//            return "No Existing Header";
//        }
//
//    }
}