package com.kkcloud.risk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.LoginMapper;
import com.kkcloud.risk.mapper.UserPermissionMapper;
import com.kkcloud.risk.model.PermissionUser;
import com.kkcloud.risk.model.User;
import com.kkcloud.risk.service.iface.UserService;
import com.kkcloud.risk.vo.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    RiskEntryListServiceImpl riskEntryListService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserLogedInDTO loginUser(LoginDTO loginDto) throws BadCredentialsException{
        try {
            User user = loginMapper.findByUsername(loginDto.getUser_email())
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found with username:" + loginDto.getUser_email()));

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUser_email(), loginDto.getUser_password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserLogedInDTO userLogedInDTO = new UserLogedInDTO();
            userLogedInDTO.setUser_id(user.getId());
            userLogedInDTO.setUser_name(user.getUserName());

            return userLogedInDTO;
        }
        catch(BadCredentialsException e){
//            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    UserPermissionMapper userPermissionMapper;

    @Override
    public List<UserDetailsDTO> getUser(UserDTO userDTO) {
        return userPermissionMapper.findById(userDTO);
    }


    //Register User
    @Override
    public String registerUser(RegisterDTO registerDTO) {

        // add check for username exists in a DB
        if(loginMapper.existByUserName(registerDTO.getUser_email())){
            return "Mismatch";
        }
        // create user object
        User user = new User();
        user.setUserName(registerDTO.getUser_email().split("@")[0]);
        user.setPassword(passwordEncoder.encode(registerDTO.getUser_password()));
        user.setUserEmail(registerDTO.getUser_email());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedBy(getUserLogin());
        loginMapper.addUser(user);

        return "Success";
    }

    @Override
    public Object getAllUsers(UserDTO userDTO) {
        PageHelper.startPage(userDTO.getPage_no(), userDTO.getPage_size());
        Page<User> users = loginMapper.findAllUsers();
        UserResultDTO userResult = new UserResultDTO();
        userResult.setUser_count(loginMapper.getUserTotal());

        List<UserListDTO> userListDTO =   users.stream()
                .map(
                        p -> new UserListDTO(p.getId(),
                        p.getUserName(),
                        p.getUserEmail(),
                        p.getCreatedAt().toString().split("T")[0] + " " + p.getCreatedAt().toString().split("T")[1],
                        p.getUpdatedAt().toString().split("T")[0] + " " + p.getUpdatedAt().toString().split("T")[1],
                        p.getCreatedBy(),
                        p.getUpdatedBy()))

                .collect(Collectors.toList());
        userResult.setUser_list(userListDTO);

        if (users.isEmpty() || users == null || users.size() == 0) {
            return ResponseHelper.nullData();
        } else {
            return ResponseHelper.success(userResult);
        }
    }

    @Override
    public List<PermissionUser> getUsersById(UserDTO userDTO) {
        return userPermissionMapper.findId(userDTO);
    }

    @Override
    public String updateUser(RegisterDTO registerDTO) {

        if(loginMapper.existByUserName(registerDTO.getUser_email())){

            User user = loginMapper.findByUsername(getUserLogin()) .orElseThrow(() ->
                            new UsernameNotFoundException("User not found"));
            log.info("First");
            User userUpdate = new User();
            userUpdate.setUserName(registerDTO.getUser_name());
            userUpdate.setPassword(passwordEncoder.encode(registerDTO.getUser_password()));
            userUpdate.setUserEmail(registerDTO.getUser_email());
            userUpdate.setUpdatedAt(LocalDateTime.now());
            userUpdate.setUpdatedBy(user.getUserName());

            loginMapper.updateUser(userUpdate);
            log.info("asdf"+userUpdate.getCreatedBy());

            return "Success";
        }else {
            log.info("Mism");
            return "Mismatch";
        }

    }

    @Override
    public String deleteUser(String userEmail) {
        return null;
    }


    @Override
    public LoginDTO checkIfRiskEntryExists(ViewUserDetailDTO viewDetailDTO) {
        return null;
    }



//    @Override
//    public void deleteUser(ViewUserDetailDTO viewUserDetailDTO) {
//
//    }

    @Override
    public List<UserDetailsDTO> getUsersById(UserDetailsDTO userDetailsDTO) {
        return null;
    }

    @Override
    public boolean checkIfDeleted(String user_email) {
        return false;
    }

    @Override
    public String getUserFromTable() {
        return null;
    }

    @Override
    public ViewUserDetailDTO checkAuditIdIfEmpty(int i) {
        return null;
    }



//    @Override
//    public Boolean existsById(int user_id){
//     Boolean checkID = userPermissionMapper.existsById(user_id);
//        return checkID;
//    }

    public String getUserLogin(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
       return username;
    }

    //------ * ------

    public boolean checkIfIDExist(int user_id) {
        return loginMapper.checkIfIDExist(user_id);
    }

    public boolean checkByEmail(String user_email) {
        return loginMapper.checkByEmail(user_email);
    }

//    @Override
//    public void deleteUser(ViewUserDetailDTO viewUserDetailDTO) {
//        ViewUserDetailDTO deleteObject = new ViewUserDetailDTO();
//        if(this.checkIfIDExist(viewUserDetailDTO.getUser_id())){
//            deleteObject.setUser_id(viewUserDetailDTO.getUser_id());
//            deleteObject.setUpdated_by(riskEntryListService.getLoggedInUser().getUserName());
//            deleteObject.setUpdated_at(LocalDateTime.now().toString());
//            loginMapper.deleteUser(deleteObject);
//        }
//    }

    @Override
    public void batchDeleteUser(ViewUserDetailDTO viewUserDetailDTO){
        ViewUserDetailDTO deleteObject = new ViewUserDetailDTO();

        for(int user_id: viewUserDetailDTO.getUser_ids()) {
            if(this.checkIfIDExist(user_id)){
                deleteObject.setUser_id(user_id);
                deleteObject.setUpdated_by(riskEntryListService.getLoggedInUser().getUserName());
                deleteObject.setUpdated_at(LocalDateTime.now().toString());
                loginMapper.deleteUser(deleteObject);
            }
        }
    }

    @Override
    public void deleteUser(ViewUserDetailDTO viewUserDetailDTO) {
        ViewUserDetailDTO deleteObject = new ViewUserDetailDTO();
        if(this.checkByEmail(viewUserDetailDTO.getUser_email())){
            deleteObject.setUser_email(viewUserDetailDTO.getUser_email());
            deleteObject.setUpdated_by(riskEntryListService.getLoggedInUser().getUserName());
            deleteObject.setUpdated_at(LocalDateTime.now().toString());
            loginMapper.deleteUser(deleteObject);
        }
    }
}
