package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.model.Header;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HeaderMapper {
    List<Header> findById(HeaderDTO headerDTO);

    void saveHeader(HeaderDTO headerDTO);
}
