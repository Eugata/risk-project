package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.ViewLogsDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ViewDetailMapper {
    List<ViewLogsDTO> outputViewLog(ViewDetailDTO viewDetailDTO);
}
