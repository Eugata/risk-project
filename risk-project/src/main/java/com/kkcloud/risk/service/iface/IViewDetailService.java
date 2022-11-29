package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.ViewLogsDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;
import java.util.List;

public interface IViewDetailService {
    List<ViewLogsDTO> outputViewLog(ViewDetailDTO viewDetailDTO);
}
