package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.EntryLogMapper;
import com.kkcloud.risk.mapper.LoginMapper;
import com.kkcloud.risk.mapper.RiskEntryMapper;
import com.kkcloud.risk.model.EntryLog;
import com.kkcloud.risk.model.RiskEntry;
import com.kkcloud.risk.service.iface.IRiskEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RiskEntryServiceImpl implements IRiskEntryService {

    @Autowired
    private RiskEntryMapper riskEntryMapper;

    @Autowired
    private EntryLogMapper entryLogMapper;

    @Autowired
    LoginMapper loginMapper;

    @Override
    public void addRiskEntry(RiskEntryDTO riskEntryDTO) {
        RiskEntry entry = new RiskEntry();
        BeanUtils.copyProperties(riskEntryDTO, entry);
        riskEntryMapper.addRiskEntry(entry);
    }



    @Override
    public RiskEntryDTO getRiskEntryDTOById(int entryId) {
        RiskEntry entry = riskEntryMapper.getById(entryId);
        RiskEntryDTO dto = new RiskEntryDTO();
        BeanUtils.copyProperties(entry, dto);
        return dto;
    }

    @Override
    public RiskEntryDTO getMaxRiskEntryId() {
        RiskEntryDTO dto = new RiskEntryDTO();
        BeanUtils.copyProperties(riskEntryMapper.getLatestRowId(), dto);
        return dto;
    }



    @Override
    public void overwriteDuplicateRow(RiskEntryDTO riskEntryDTO) {
        RiskEntry riskEntry = new RiskEntry();
        BeanUtils.copyProperties(riskEntryDTO, riskEntry);
        riskEntryMapper.overwriteDuplicateRow(riskEntry);
    }

    @Override
    public RiskEntryDTO checkIfRiskEntryExists(ViewDetailDTO viewDetailDTO) {
        RiskEntryDTO riskEntryDTO = new RiskEntryDTO();
        RiskEntry riskEntry = riskEntryMapper.getById(viewDetailDTO.getEntry_id());
        if(riskEntry != null) {
            BeanUtils.copyProperties(riskEntry, riskEntryDTO);
            return riskEntryDTO;
        }
        return null;
    }

    public RiskEntryLogUpdateDTO getRiskEntry(RiskEntryLogUpdateDTO riskEntryLogUpdateDTO) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        RiskEntry riskEntry = riskEntryMapper.findByUser(riskEntryLogUpdateDTO.getEntry_id());
        RiskEntry riskEntryUpdated = new RiskEntry();
        RiskEntryLogUpdateDTO riskEntryLogDTOUpdated = new RiskEntryLogUpdateDTO();
        RiskEntryLogUpdateDTO riskEntryLogDTO = new RiskEntryLogUpdateDTO();
        riskEntryLogDTO.setUser_email(username);
        riskEntryLogDTOUpdated.setEntry_id(riskEntryLogUpdateDTO.getEntry_id());
        riskEntryLogDTOUpdated.setUser_email(username);
        riskEntryUpdated.setEntry_id(riskEntry.getEntry_id());

        //Audit ID Incrementation
        int audit_id = riskEntryLogDTOUpdated.getEntry_id();//1447
        System.out.println(audit_id);
        audit_id = riskEntryMapper.entryLogsAuditId(audit_id);//2010
        System.out.println(audit_id);
        //audit_id++;//2011
        riskEntryLogDTO.setAudit_id(++audit_id);
        riskEntryLogDTO.setCreated_date(riskEntryLogUpdateDTO.getCreated_date());
        riskEntryLogDTOUpdated.setUpdated_at(riskEntryLogUpdateDTO.getUpdated_at());
        riskEntryLogDTO.setUpdated_at(riskEntryLogUpdateDTO.getUpdated_at());
        System.out.println(audit_id);

        if(!riskEntryLogUpdateDTO.getMember_name().equals(riskEntry.getMember_name())){
            riskEntryLogDTOUpdated.setMember_name(riskEntryLogUpdateDTO.getMember_name());

            riskEntryLogDTO.setBefore_value(riskEntry.getMember_name());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getMember_name());
            riskEntryLogDTO.setField_type("member_name");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());
            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if(!riskEntryLogUpdateDTO.getBank_account_no().equals(riskEntry.getBank_account_no())){
            riskEntryLogDTOUpdated.setBank_account_no(riskEntryLogUpdateDTO.getBank_account_no());

            riskEntryLogDTO.setBefore_value(riskEntry.getBank_account_no());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getBank_account_no());
            riskEntryLogDTO.setField_type("bank_account_no");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if(!riskEntryLogUpdateDTO.getDepositor_name().equals(riskEntry.getDepositor_name())){
            riskEntryLogDTOUpdated.setDepositor_name(riskEntryLogUpdateDTO.getDepositor_name());

            riskEntryLogDTO.setBefore_value(riskEntry.getDepositor_name());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getDepositor_name());
            riskEntryLogDTO.setField_type("depositor_name");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if(!riskEntryLogUpdateDTO.getEntry_reason().equals(riskEntry.getEntry_reason())){
            riskEntryLogDTOUpdated.setEntry_reason(riskEntryLogUpdateDTO.getEntry_reason());

            riskEntryLogDTO.setBefore_value(riskEntry.getEntry_reason());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getEntry_reason());
            riskEntryLogDTO.setField_type("entry_reason");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if(!riskEntryLogUpdateDTO.getContact_number().equals(riskEntry.getContact_number())){
            riskEntryLogDTOUpdated.setContact_number(riskEntryLogUpdateDTO.getContact_number());

            riskEntryLogDTO.setBefore_value(riskEntry.getContact_number());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getContact_number());
            riskEntryLogDTO.setField_type("contact_number");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if (!riskEntryLogUpdateDTO.getRemarks().equals(riskEntry.getRemarks())){
            riskEntryLogDTOUpdated.setRemarks(riskEntryLogUpdateDTO.getRemarks());

            riskEntryLogDTO.setBefore_value(riskEntry.getRemarks());
            riskEntryLogDTO.setUpdated_value(riskEntryLogUpdateDTO.getRemarks());
            riskEntryLogDTO.setField_type("remarks");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        if (riskEntryLogUpdateDTO.getRisk_id() != riskEntry.getRisk_id()){
            riskEntryLogDTOUpdated.setRisk_id(riskEntryLogUpdateDTO.getRisk_id());
            System.out.println(riskEntry.getRisk_id());
            riskEntryLogDTO.setBefore_value(Integer.toString(riskEntry.getRisk_id()));
            riskEntryLogDTO.setUpdated_value(Integer.toString(riskEntryLogDTOUpdated.getRisk_id()));
            riskEntryLogDTO.setField_type("risk_id");
            riskEntryLogDTO.setEntry_id(riskEntry.getEntry_id());

            riskEntryMapper.updateEntryLog(riskEntryLogDTO);
        }

        riskEntryMapper.updateRiskEntry(riskEntryLogDTOUpdated);

        return riskEntryLogUpdateDTO;


    }

    @Override
    public void deleteRiskEntry(ViewDetailDTO viewDetailDTO) {
        riskEntryMapper.deleteRiskEntry(viewDetailDTO);
    }

    @Override
    public String getUserFromSystem() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        //to get current user logged in
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public ViewDetailDTO checkDuplicateRiskEntry(String member_account) {
        //check here if there's a duplicate in risk_entry table
        int entryId;
        RiskEntryDTO dupRiskEntry = riskEntryMapper.checkDuplicateRiskEntry(member_account);

        if(dupRiskEntry == null) {//if no duplicate found in db
            entryId = 0;
        } else {
            entryId = dupRiskEntry.getEntry_id();
        }
        ViewDetailDTO vDTO = checkAuditIdIfEmpty(1);
        vDTO.setEntry_id(entryId);
        return vDTO;
    }

    @Override
    public ViewDetailDTO checkAuditIdIfEmpty(int adjustId) {
        ViewDetailDTO vDTO = new ViewDetailDTO();
        /*  add entry log for add and delete
         *  *******************************************
         *  check if latest audit_id is null in the database,
         *  the code below is for the scenario when there is no
         *  data in the entry_logs database and we want to add
         *  a new entry_log there for add or delete
         */
        List<EntryLog> tableLst = entryLogMapper.getEntryLogTable();
        int size = tableLst.size();
        if(size == 0 || tableLst.get(size-1).getAudit_id() < 1) {
            /* size == 0 means entry_logs table is empty
             * the second expression is used to check if the latest entry_logs row
             * in the DB has an audit_id of 0
             */
            vDTO.setAudit_id(adjustId);
        } else {
            // if the database have consistent numbering on the audit_id column
            vDTO.setAudit_id(tableLst.get(size-1).getAudit_id() + adjustId);//to be used in update, but remove "+1"
        }
        return vDTO;
    }

    @Override
    public Boolean checkIfDeleted(int entry_id) {
        return riskEntryMapper.checkIfDeleted(entry_id);
    }
}
