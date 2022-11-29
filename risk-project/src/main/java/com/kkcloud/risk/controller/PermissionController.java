package com.kkcloud.risk.controller;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.UpdateDetailNameAllMapper;
import com.kkcloud.risk.mapper.DetailPermissionMapper;
import com.kkcloud.risk.mapper.HeaderPermissionMapper;
import com.kkcloud.risk.mapper.PermissionUpdateMapper;
import com.kkcloud.risk.mapper.UserPermissionMapper;
import com.kkcloud.risk.model.PermissionDetail;
import com.kkcloud.risk.model.PermissionHeader;
import com.kkcloud.risk.service.iface.*;
import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "127.0.0.1")
@RequestMapping("/api/riskmanagement")
public class PermissionController {

    public static final String PERMISSIONS_V_1_ADD_DETAIL = "/permissions/v1/add/detail";
    @Autowired
    DetailPermissionMapper detailPermissionMapper;
    @Autowired
    UserPermissionMapper userPermissionMapper;
    @Autowired
    DetailService detailService;
    @Autowired
    UserService userService;
    @Autowired
    HeaderPermissionMapper headerPermissionMapper;
    @Autowired
    HeaderService headerService;
    @Autowired
    PermissionUpdateMapper permissionUpdateMapper;
    @Autowired
    UpdateService updateService;
    @Autowired
    UpdateDetailNameAllService updateDetailNameAllService;


    //Uncomment @PreAuthorize if you want to implement the permission based authorization.
    @PreAuthorize("hasAnyAuthority('录入')")
    @PostMapping("/permissions/v1/getDetailPermission")
    public ResponseVO<List<PermissionDetail>> getUserById(@RequestBody PermissionDTO permissionDTO) {
        List<PermissionDetail> detail = detailService.getDetails(permissionDTO);
        log.info("Successful Operation! {}", permissionDTO);
        return ResponseHelper.success(detail);
        /*return new ResponseVO(200,"Successful Operation!",detail);*/

    }

    @PostMapping("/permissions/v1/getUserPermission")
    public ResponseVO<List<UserDetailsDTO>> getUserId(@RequestBody UserDTO userDTO) {
        try {
            List<UserDetailsDTO> user = userService.getUser(userDTO);
            log.info("Successful Operation! {}", userDTO);
            return ResponseHelper.success(user);
            /*  return new ResponseVO(200,"Successful Operation!",user);*/
        } catch (Exception e) {
            /* e.printStackTrace();*/
            log.error("Unsuccessful Operation! {} {}", e.getMessage(), e);
            return ResponseHelper.error();
            /*return new ResponseVO(500,"Successful Operation!",null);*/
        }
    }

    @PostMapping("/permissions/v1/getHeaderPermission")
    public ResponseVO<List<PermissionHeader>> getById(@Validated @RequestBody HeaderDTO headerDTO) {
        List<PermissionHeader> permit = headerService.getHeader(headerDTO);
        log.info("Successful Operation! {}", ResponseHelper.success(permit));
        return ResponseHelper.success(permit);
        /*return new ResponseVO(200,"Successful Operation!",permit);*/
    }

    @PostMapping("/permissions/v1/addHeader")

    public ResponseVO getHeader(@RequestBody HeaderDTO headerDTO) {
        headerService.savePermissionHead(headerDTO);
        log.info("Successful Operation! {}", ResponseHelper.success(headerDTO));
        return ResponseHelper.success(headerDTO);
        /*return new ResponseVO(200,"Successful Operation!",new ArrayList());*/
    }

    @PostMapping("/permissions/v1/addDetail")
    public ResponseVO getDetail(@RequestBody AddDetailDTO addDetailDTO) {
        detailService.savePermissionDetail(addDetailDTO);
        log.info("Successful Operation! {}", ResponseHelper.success(addDetailDTO));
        return ResponseHelper.success(addDetailDTO);
        /*return new ResponseVO(200,"Successful Operation!",new ArrayList());*/
    }

    @PostMapping("/permissions/v1/updatePermission")
    public ResponseVO<?> updateBatchDetail(@RequestBody List<DetailsPermissionUpdateDTO> detailsPermissionUpdateDTO) {
        String result = updateService.updateDetails(detailsPermissionUpdateDTO);
/*        if(result=="No user found"){
            result = "";
            log.error("No record found!", result);
            return  ResponseHelper.userNotFound();
            *//*return new ResponseVO(200, "No record found", result );*//*
        }else{
            log.info("Successful Operation!", result);
            return ResponseHelper.success(result);
            *//*return new ResponseVO(200, "Successful Operation", result );*//*
        }*/
        if (result == "No user found") {
            log.error("No record found! {}", result);
            return ResponseHelper.userNotFound();
            /*return new ResponseVO(200, "No record found", result );*/
        }
        if (result == "No permission") {
            log.error("No permission {}", result);
            return ResponseHelper.forbidden();
            /*return new ResponseVO(200, "No record found", result );*/
        } else {
            log.info("Successful Operation! {}", result);
            return ResponseHelper.success(result);
            /*return new ResponseVO(200, "Successful Operation", result );*/
        }
    }

    @PostMapping("/permissions/v1/updateHeader")
    public ResponseVO updateHeader(@RequestBody HeaderDTO headerDTO) {
        updateService.updateHeaderDetails(headerDTO);
        log.info("Successful Operation! {}", ResponseHelper.success());
        return ResponseHelper.success();
        /*return new ResponseVO(200, "Successful Operation!", new ArrayList());*/
    }

    @PostMapping("/permissions/v1/updateDetail")
    public ResponseVO updateDetailsAll(@RequestBody UpdateDetailNameAllDTO updateDetailNameAlldto) {
        return new ResponseVO(200, updateDetailNameAllService.updateDetailNames(updateDetailNameAlldto), new ArrayList());

    }
// removed for now delete column removed
//    @PostMapping("/permissions/v1/deleteDetail")
//    public ResponseVO deleteDetailsAll(@RequestBody UpdateDetailNameAllDTO updateDetailNameAlldto){
//        return new ResponseVO(200, updateDetailNameAllService.deleteDetailNames(updateDetailNameAlldto), new ArrayList());
//
//    }

//    @PostMapping("/permissions/v1/deleteHeader")
//    public ResponseVO deleteHeaderDetails(@RequestBody HeaderDTO headerDTO){
//        String updateHeaderResponse = updateService.deleteHeaderDetails(headerDTO);
//        if(updateHeaderResponse=="No Existing Header"){
//            updateHeaderResponse = "";
//            return new ResponseVO(200, "No record found", updateHeaderResponse );
//        }else{
//            return new ResponseVO(200, "Successful Operation", updateHeaderResponse );
//        }
//
//    }
}

