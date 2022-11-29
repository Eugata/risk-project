package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.EntryLogDTO;
import com.kkcloud.risk.dto.RiskEntryDTO;
import com.kkcloud.risk.mapper.EntryLogMapper;
import com.kkcloud.risk.dto.ViewDetailDTO;
import com.kkcloud.risk.model.EntryLog;
import com.kkcloud.risk.service.iface.IEntryLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryLogServiceImpl implements IEntryLogService {

    @Autowired
    private EntryLogMapper entryLogMapper;

    @Override
    public void addEntryLog(ViewDetailDTO vDTO, RiskEntryDTO riskEntryDTO, RiskEntryDTO riskEntryFromDB) {
        if(vDTO.getOperation() == "update") {
            EntryLogDTO entryLogDTO = new EntryLogDTO();
            entryLogDTO.setEntry_id(vDTO.getEntry_id());
            entryLogDTO.setUser_id(entryLogMapper.getCurrentUserId(vDTO.getUser_email()));
            entryLogDTO.setOperation("update");
            entryLogDTO.setCreated_date(riskEntryFromDB.getCreated_at());
            entryLogDTO.setUpdated_date(riskEntryDTO.getCreated_at());
            entryLogDTO.setAudit_id(vDTO.getAudit_id());
            if(riskEntryFromDB.getMember_name().compareTo(riskEntryDTO.getMember_name()) != 0) {
                entryLogDTO.setField_type("member_name");
                entryLogDTO.setBefore_value(riskEntryFromDB.getMember_name());
                entryLogDTO.setUpdated_value(riskEntryDTO.getMember_name());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getDepositor_name().compareTo(riskEntryDTO.getDepositor_name()) != 0) {
                entryLogDTO.setField_type("depositor_name");
                entryLogDTO.setBefore_value(riskEntryFromDB.getDepositor_name());
                entryLogDTO.setUpdated_value(riskEntryDTO.getDepositor_name());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getBank_account_no().compareTo(riskEntryDTO.getBank_account_no()) != 0) {
                entryLogDTO.setField_type("bank_account_no");
                entryLogDTO.setBefore_value(riskEntryFromDB.getBank_account_no());
                entryLogDTO.setUpdated_value(riskEntryDTO.getBank_account_no());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getEntry_reason().compareTo(riskEntryDTO.getEntry_reason()) != 0) {
                entryLogDTO.setField_type("entry_reason");
                entryLogDTO.setBefore_value(riskEntryFromDB.getEntry_reason());
                entryLogDTO.setUpdated_value(riskEntryDTO.getEntry_reason());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getRisk_id() != riskEntryDTO.getRisk_id()) {
                entryLogDTO.setField_type("risk_id");
                entryLogDTO.setBefore_value(Integer.toString(riskEntryFromDB.getRisk_id()));
                entryLogDTO.setUpdated_value(Integer.toString(riskEntryDTO.getRisk_id()));
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getContact_number().compareTo(riskEntryDTO.getContact_number()) != 0) {
                entryLogDTO.setField_type("contact_number");
                entryLogDTO.setBefore_value(riskEntryFromDB.getContact_number());
                entryLogDTO.setUpdated_value(riskEntryDTO.getContact_number());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getRemarks().compareTo(riskEntryDTO.getRemarks()) != 0) {
                entryLogDTO.setField_type("remarks");
                entryLogDTO.setBefore_value(riskEntryFromDB.getRemarks());
                entryLogDTO.setUpdated_value(riskEntryDTO.getRemarks());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getCreated_by().compareTo(riskEntryDTO.getCreated_by()) != 0) {
                entryLogDTO.setField_type("created_by");
                entryLogDTO.setBefore_value(riskEntryFromDB.getCreated_by());
                entryLogDTO.setUpdated_value(riskEntryDTO.getCreated_by());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
            if(riskEntryFromDB.getCreated_at().compareTo(riskEntryDTO.getCreated_at()) != 0) {
                entryLogDTO.setField_type("created_at");
                entryLogDTO.setBefore_value(riskEntryFromDB.getCreated_at());
                entryLogDTO.setUpdated_value(riskEntryDTO.getCreated_at());
                entryLogMapper.addDupUpdateLog(entryLogDTO);
            }
        } else {//"add"
            entryLogMapper.addEntryLog(vDTO);
        }
    }

    @Override
    public void deletedEntryLog(ViewDetailDTO viewDetailDTO) {
        entryLogMapper.deletedEntryLog(viewDetailDTO);
    }

    @Override
    public List<EntryLogDTO> getEntryLogTable() {
        List<EntryLogDTO> eLogsDTO = new ArrayList<>();
        EntryLogDTO entryLogDTO = new EntryLogDTO();
        List<EntryLog> eLogs = entryLogMapper.getEntryLogTable();
        for (EntryLog el : eLogs) {
            BeanUtils.copyProperties(el, entryLogDTO);
            eLogsDTO.add(entryLogDTO);
        }
        return eLogsDTO;
    }
}
