package com.kkcloud.risk.controller;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.model.RiskEntryImport;
import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.service.iface.RegisterService;
import com.kkcloud.risk.service.iface.UserService;
import com.kkcloud.risk.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@EnableAutoConfiguration
@RestController
@CrossOrigin(origins = "127.0.0.1")
@RequestMapping("/api/riskmanagement")
public class UserController {

        @Autowired
        private RegisterService registerService;

        @Autowired
        private UserService userService;

        @ResponseBody
        @PostMapping("/user/v1/login")
        public ResponseEntity<ResponseVO<UserLogedInDTO>> authenticateUser(@Valid @RequestBody LoginDTO loginDto) {
            System.out.println(loginDto);
            UserLogedInDTO userLogedInDTO = userService.loginUser(loginDto);

                  if(userLogedInDTO!=null){
                        log.info("Successful login {}", userLogedInDTO);
                      System.out.println(userLogedInDTO);
                      return new ResponseEntity<ResponseVO<UserLogedInDTO>>(ResponseHelper.success(userLogedInDTO), HttpStatus.OK);
                  }
                    else {
                        log.error("User Not Found {}", ResponseHelper.userNotFound());
                      return new ResponseEntity<ResponseVO<UserLogedInDTO>>(ResponseHelper.userNotFound(), HttpStatus.FORBIDDEN);

                  }
        }

        @PostMapping("/user/v1/register")
        public ResponseVO<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){

            String regUser = userService.registerUser(registerDTO);

            if(regUser == "Success"){
                log.info("Success! {}", regUser);
                return ResponseHelper.success();
            }
            else if(regUser  == "Mismatch"){
                log.warn("User already exist! {}", regUser);
                return ResponseHelper.userExist();
            }else {
                log.error("Invalid {}", regUser);
                return ResponseHelper.error();
            }
        }

        @PostMapping("/user/v1/list")
        public Object getAllUsers(@RequestBody UserDTO userDTO){
            Object users =  userService.getAllUsers(userDTO);
            log.info("Users available: {}",users);
            return users;
        }

        @PostMapping("/user/v1/update")
        public ResponseVO<?> updateUser(@RequestBody RegisterDTO registerDTO){
           String update = userService.updateUser(registerDTO);
            log.info("Updatingerrors");
            if(update == "Success"){
                log.info("Successfully update! {}", update);
                return ResponseHelper.success();
            } else if(update  == "Mismatch") {
                log.warn("The user is updated successfully {}", update);
                return ResponseHelper.nullData();
            } else {
                log.error("Invalid {}", update);
                return ResponseHelper.error();
            }
        }



//    @PostMapping("/user/v1/delete")
//    public ResponseVO deleteUser1(@Valid @RequestBody List<String> user_emailList) { try {
//       userService.deleteUser(user_emailList);
//        return ResponseHelper.success(new ArrayList());
//
//        } catch (Exception e) {
//            log.info("Fail");
//            e.printStackTrace();
//            return ResponseHelper.error();
//        }
//    }

//    @PostMapping("/user/v1/delete")
//    public ResponseVO deleteUser(@Valid @RequestBody ViewUserDetailDTO viewUserDetailDTO) {
//        try {
//            userService.deleteUser(viewUserDetailDTO);
//            return ResponseHelper.success(new ArrayList());
//        } catch (Exception e) {
//            log.info("Fail");
//            e.printStackTrace();
//            return ResponseHelper.error();
//        }
//    }

    @PostMapping("/user/v1/batchDelete")
    public ResponseVO batchDeleteUser(@Valid @RequestBody ViewUserDetailDTO viewUserDetailDTO) {
        try {
            userService.batchDeleteUser(viewUserDetailDTO);
            return ResponseHelper.success(new ArrayList());
        } catch (Exception e) {
            log.error("Fail!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    @PostMapping("/user/v1/delete")
    public ResponseVO deleteUser(@Valid @RequestBody ViewUserDetailDTO viewUserDetailDTO){
        try {
            userService.deleteUser(viewUserDetailDTO);
            return ResponseHelper.success(new ArrayList());
        } catch (Exception e) {
            log.error("Fail!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }


}

