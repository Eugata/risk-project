package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.RiskEntryDTO;
import com.kkcloud.risk.dto.RiskEntryDuplicateDTO;
import com.kkcloud.risk.dto.RiskEntryLogDTO;
import com.kkcloud.risk.dto.RiskEntryLogUpdateDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;
import com.kkcloud.risk.model.RiskEntry;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RiskEntryMapper {
    void addRiskEntry(RiskEntry addRiskEntry);
    RiskEntry getById(int entryId);
    RiskEntry getLatestRowId();
    void overwriteDuplicateRow(RiskEntry riskEntry);
    RiskEntry findByUser(int riskEntry);
    void updateRiskEntry(RiskEntryLogUpdateDTO riskEntryLogUpdateDTO);
    void updateEntryLog(RiskEntryLogUpdateDTO riskEntryUpdated);
    void deleteRiskEntry(ViewDetailDTO viewDetailDTO);
    int entryLogsAuditId(int audit_id);
    RiskEntryDTO checkDuplicateRiskEntry(String member_account);
    Boolean checkIfDeleted(int entry_id);
}
