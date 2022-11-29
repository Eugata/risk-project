package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.UserDTO;
import com.kkcloud.risk.dto.UserDetailsDTO;
import com.kkcloud.risk.model.PermissionUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserPermissionMapper {
    List<PermissionUser> findId(UserDTO userDTO);
//    UserDTO userDTO1 = new UserDTO();
//    String email = userDTO1.
    List<UserDetailsDTO> findById(UserDTO userDTO);
    Boolean existsById(int user_id);
    Boolean existingLoginById(int user_id);
    Boolean checkIfAllowed(int user_id);
}
