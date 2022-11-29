package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.RegisterDTO;
import com.kkcloud.risk.vo.ResponseVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    ResponseVO addNewUser1(RegisterDTO registerDTO);
}
