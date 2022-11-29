package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.model.PermissionUser;

import javax.validation.Valid;
import java.util.List;

public interface UserService {
    UserLogedInDTO loginUser(LoginDTO loginDTO);
    List<UserDetailsDTO> getUser(UserDTO userDTO);
    String registerUser(RegisterDTO registerDTO);
    Object getAllUsers(UserDTO userDTO);
    List<PermissionUser> getUsersById(UserDTO userDTO);
    //List<UserDetailsDTO> getUsersById(UserDetailsDTO userDetailsDTO);
    String updateUser(RegisterDTO registerDTO);
    String deleteUser(String userEmail);
//    Boolean existsById(int user_id);

    LoginDTO checkIfRiskEntryExists(ViewUserDetailDTO viewDetailDTO);

    List<UserDetailsDTO> getUsersById(UserDetailsDTO userDetailsDTO);
    boolean checkIfDeleted(String user_email);
    String getUserFromTable();
    ViewUserDetailDTO checkAuditIdIfEmpty(int i);

    //---
//    void deleteUser(@Valid ViewUserDetailDTO viewUserDetailDTO);
    void batchDeleteUser(ViewUserDetailDTO viewUserDetailDTO);
    void deleteUser(ViewUserDetailDTO viewUserDetailDTO);

    boolean checkByEmail(String user_email);

}



