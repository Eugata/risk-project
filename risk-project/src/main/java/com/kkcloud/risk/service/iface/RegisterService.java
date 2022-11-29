package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.RegisterDTO;
import com.kkcloud.risk.vo.ResponseVO;

public interface RegisterService {
    ResponseVO addNewUser(RegisterDTO registerDTO);
}