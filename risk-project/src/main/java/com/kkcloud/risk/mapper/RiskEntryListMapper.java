package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.EntryLogDTO;
import com.kkcloud.risk.dto.RiskEntryListDTO;
import com.kkcloud.risk.dto.RiskEntryLogDTO;
import com.kkcloud.risk.model.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.github.pagehelper.Page;

@Mapper
public interface RiskEntryListMapper {
    Page<RiskEntryList> QueryListByFilter(RiskEntryListDTO riskEntryListDto);
    Page<RiskEntryList> QueryListByPageAndSize(RiskEntryListDTO riskEntryListDto);
    Page<RiskEntryExport> downloadRiskEntry(RiskEntryListDTO riskEntryListDto);
    void uploadRiskEntry(RiskEntryImport riskEntryListModels);
    Boolean hasDuplicate(RiskEntryImport object);
    void updateDuplicatedEntry(RiskEntryImport object);
    void insertEntryLogs(EntryLogDTO object);
    RiskEntry getRiskEntry(String member_account);
    Integer getMaxAuditId();
    RiskEntry getOldObjectToBeUpdated(String member_account);
    List<Integer> getRiskIdList();
    int getRiskTotal();
}
