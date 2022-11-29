package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.EntryLogDTO;
import com.kkcloud.risk.dto.RiskEntryDTO;
import com.kkcloud.risk.dto.ViewDetailDTO;
import com.kkcloud.risk.model.EntryLog;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EntryLogMapper {
    void addEntryLog(ViewDetailDTO vDTO);
    void deletedEntryLog(ViewDetailDTO viewDetailDTO);
    List<EntryLog> getEntryLogTable();
    void addDupUpdateLog(EntryLogDTO entryLogDTO);
    int getCurrentUserId(String user_email);
}
