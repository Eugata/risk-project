package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.RegisterDTO;
import com.kkcloud.risk.mapper.RegisterMapper;
import com.kkcloud.risk.service.iface.RegisterService;
import com.kkcloud.risk.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public ResponseVO addNewUser(RegisterDTO registerDTO) {
        try {
            if (registerDTO.getUser_name() == null || registerDTO.getUser_name().isEmpty() || registerDTO.getUser_name().equals("")) {
                return new ResponseVO(200, "User name is required!", new ArrayList());
            }

            registerMapper.addNewUser1(registerDTO);
            return new ResponseVO(200, "New user created successfully!", new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseVO(200, "Server error", new ArrayList());
        }
    }
}