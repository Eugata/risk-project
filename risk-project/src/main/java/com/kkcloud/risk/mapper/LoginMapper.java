package com.kkcloud.risk.mapper;

import com.github.pagehelper.Page;
import com.kkcloud.risk.dto.LoginDTO;
import com.kkcloud.risk.dto.ViewUserDetailDTO;
import com.kkcloud.risk.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface LoginMapper {

    List<LoginDTO>loginUser1(LoginDTO loginDTO);

    Optional<User>findByUsername(String userEmail);

    Page<User> findAllUsers();

    void addUser(User user);

    Boolean existByUserName(String username);

    void updateUser(User user);

    void deleteUser(ViewUserDetailDTO viewUserDetailDTO);

    //-----
    boolean checkIfIDExist(int user_id);
    boolean checkByEmail(String user_email);

    boolean checkIfDeleted(String user_email);

    int getUserTotal();
}
