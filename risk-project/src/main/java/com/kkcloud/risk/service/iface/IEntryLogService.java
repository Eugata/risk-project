package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.EntryLogDTO;
import com.kkcloud.risk.dto.RiskEntryDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;
import java.util.List;

public interface IEntryLogService {
    void addEntryLog(ViewDetailDTO vDTO, RiskEntryDTO riskEntryDTO, RiskEntryDTO riskEntryFromDB);
    void deletedEntryLog(ViewDetailDTO viewDetailDTO);
    List<EntryLogDTO> getEntryLogTable();
}
