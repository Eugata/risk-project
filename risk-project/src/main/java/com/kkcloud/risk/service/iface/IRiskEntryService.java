package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.RiskEntryDTO;
import com.kkcloud.risk.dto.RiskEntryLogUpdateDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;

public interface IRiskEntryService {
    void addRiskEntry(RiskEntryDTO riskEntryDTO);
    RiskEntryDTO getRiskEntryDTOById(int entryId);
    RiskEntryDTO getMaxRiskEntryId();
    void overwriteDuplicateRow(RiskEntryDTO riskEntryDTO);
    RiskEntryDTO checkIfRiskEntryExists(ViewDetailDTO viewDetailDTO);
    RiskEntryLogUpdateDTO getRiskEntry(RiskEntryLogUpdateDTO riskEntryLogUpdateDTO);
    void deleteRiskEntry(ViewDetailDTO viewDetailDTO);
    String getUserFromSystem();
    ViewDetailDTO checkDuplicateRiskEntry(String member_account);
    ViewDetailDTO checkAuditIdIfEmpty(int adjustId);
    Boolean checkIfDeleted(int entry_id);

}
