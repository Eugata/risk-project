package com.kkcloud.risk.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.LoginMapper;
import com.kkcloud.risk.mapper.RiskEntryListMapper;
import com.kkcloud.risk.model.*;
import com.kkcloud.risk.service.iface.RiskEntryListService;
import com.kkcloud.risk.utils.RiskEntryListCsvHelper;
import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RiskEntryListServiceImpl implements RiskEntryListService {



    @Autowired
    RiskEntryListMapper riskEntryListMapper;

    @Autowired
    LoginMapper loginMapper;

    @Override
    public ResponseVO<RiskResultDTO> findAllRisk(RiskEntryListDTO riskEntryListDto) {
        try {
            PageHelper.startPage(riskEntryListDto.getPage_no(), riskEntryListDto.getPage_size());
            Page<RiskEntryList> risk = riskEntryListMapper.QueryListByFilter(riskEntryListDto);
            RiskResultDTO result = new RiskResultDTO();
            result.setRisk_count(riskEntryListMapper.getRiskTotal());
            result.setEntry_list(risk);
            if (risk.isEmpty() || risk == null || risk.size() == 0) {
                return ResponseHelper.nullData();
            } else {
                return ResponseHelper.success(result);
                }
        } catch (Exception e) {
            e.printStackTrace();
                return ResponseHelper.error();
        }
    }

    @Override
    public ResponseVO uploadRiskList(MultipartFile file) {
        try {
            int nullCounter=0;
            int dupCounter =0;
            int rowCounter = 0;
            RiskEntryListCsvCounterDTO entryCounter = new RiskEntryListCsvCounterDTO();
            List<RiskEntryImport> riskEntryImports = RiskEntryListCsvHelper.csvToRiskHelper(file.getInputStream());
            for(RiskEntryImport object : riskEntryImports){
                if(checkNullValues(object) && checkRiskId(object.getRisk_id())){
                    nullCounter++;
                }
                else {
                    if(riskEntryListMapper.hasDuplicate(object)){
                        //getting oldobject for comparison of changes
                        RiskEntry oldObject = riskEntryListMapper.getOldObjectToBeUpdated(object.getMember_account());
                        //checking field changes and logging into entry logs and update entry if hasChanges == true
                        checkChangesInEntry(oldObject, object);
                        dupCounter++;
                    }
                    else{
                        // add new risk_entry object
                        riskEntryListMapper.uploadRiskEntry(object);
                        //add new entry_log object
                        addEntryLog(object);
                    }
                }
                rowCounter++;
            }
            entryCounter.setX(rowCounter);
            entryCounter.setY((dupCounter));
            entryCounter.setZ(nullCounter);
            entryCounter.setA(rowCounter - (nullCounter + dupCounter));
            return ResponseHelper.success(entryCounter);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream downloadRiskList(RiskEntryListDTO riskEntryListDto) {
            PageHelper.startPage(riskEntryListDto.getPage_no(), riskEntryListDto.getPage_size());
            Page<RiskEntryExport> risk =  riskEntryListMapper.downloadRiskEntry(riskEntryListDto);
            ByteArrayInputStream downLoad = RiskEntryListCsvHelper.riskHelperToCsv(risk);
            return downLoad;
    }

    private void addEntryLog(RiskEntryImport object){
        //getting the newly added entry in risk_entry
        RiskEntry riskEntry = riskEntryListMapper.getRiskEntry(object.getMember_account());
        // creating new EntryLog object ready to be saved in entrylog table
        EntryLogDTO newEntry = new EntryLogDTO();
        newEntry.setEntry_id(riskEntry.getEntry_id())
                .setUser_id(getLoggedInUser().getId())
                .setOperation("add")
                .setAudit_id(riskEntryListMapper.getMaxAuditId() + 1)
                .setField_type(null)
                .setBefore_value(null)
                .setUpdated_value(null)
                .setCreated_date(object.getCreated_at())
                .setUpdated_date(LocalDateTime.now().toString());
        //save data to entry logs
        riskEntryListMapper.insertEntryLogs(newEntry);
    }


    private Boolean checkNullValues(RiskEntryImport object){
        if(object.getMember_account().isEmpty() || object.getMember_name().isEmpty() ||
                object.getDepositor_name().isEmpty() || object.getBank_account_no().isEmpty() ||
                object.getCreated_by().isEmpty() || object.getRisk_id() < 1 || object.getRisk_id() > 4){
            return true;
        }
        return false;
    }

    private List<Integer> getRiskIdList(){
        List<Integer> riskIdList= riskEntryListMapper.getRiskIdList();
        System.out.println(riskIdList);
        return riskIdList;
    }

    private Boolean checkRiskId(int riskId){
        return !getRiskIdList().contains(riskId) ? true:false;
    }

    private void checkChangesInEntry(RiskEntry oldObject, RiskEntryImport newObject){
        boolean hasChanges = false;
        int indexOfMemberNameField = 0;
        EntryLogDTO entryLog = new EntryLogDTO();
        //setting operation here since this function is for updating entry
        entryLog.setOperation("update");

        List oldArrayObject = objectToArray(oldObject);
        List newArrayObject = objectToArray(newObject);

        int objectIndex = newArrayObject.size();

        //one audit id per changes in row
        entryLog.setAudit_id(riskEntryListMapper.getMaxAuditId() + 1);
        for(int index = 0; index < objectIndex ; index++){
            // index 7 is created_at field, skipping
            if(++index == 7) continue;
            if(!oldArrayObject.get(index+1).equals(newArrayObject.get(index))){
                hasChanges = true;
                //index 0 for setting id in the entrylog because csv files does not contain id
                entryLog.setEntry_id((Integer) oldArrayObject.get(0));
                //index 8 is created_at field in risk_entry object
                entryLog.setCreated_date(LocalDateTime.now().toString());
                //set user id to current user logged in
                entryLog.setUser_id(getLoggedInUser().getId());
                entryLog.setBefore_value(oldArrayObject.get(index+1).toString());
                entryLog.setUpdated_value((newArrayObject.get(index).toString()));
                entryLog.setUpdated_date(LocalDateTime.now().toString());
                switch(index){
                    //index 0 for logging changes in member_name field
                    case 0 : entryLog.setField_type("member_name");
                        break;
                    //skipped index 1 which member account because it cannot be changed.
                    case 1 :
                        break;
                    case 2 : entryLog.setField_type("depositor_name");
                        break;
                    //bank account number field
                    case 3 : entryLog.setField_type("bank_account_no");
                        break;
                    //entry reason field
                    case 4 : entryLog.setField_type("entry_reason");
                        break;
                    //risk id field
                    case 5: entryLog.setField_type("risk_id");
                        break;
                    //contact number field
                    case 6 : entryLog.setField_type("contact_number");
                        break;
                    //skipped created at field
                    case 7 :
                        break;
                    //remarks field
                    case 8 : entryLog.setField_type("remarks");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + index);
                }
                riskEntryListMapper.insertEntryLogs(entryLog);
            }
        }
        if(hasChanges){
            newObject.setUpdated_by(getLoggedInUser().getUserName());
            riskEntryListMapper.updateDuplicatedEntry(newObject);
        }
    }


    private List objectToArray(Object object){
        List arrayObject = new ArrayList();
        if(object instanceof RiskEntry){
            arrayObject.add(((RiskEntry) object).getEntry_id());
            arrayObject.add(((RiskEntry) object).getMember_name());
            arrayObject.add(((RiskEntry) object).getMember_account());
            arrayObject.add(((RiskEntry) object).getDepositor_name());
            arrayObject.add(((RiskEntry) object).getBank_account_no());
            arrayObject.add(((RiskEntry) object).getEntry_reason());
            arrayObject.add(((RiskEntry) object).getRisk_id());
            arrayObject.add(((RiskEntry) object).getContact_number());
            arrayObject.add(((RiskEntry) object).getCreated_at());
            arrayObject.add(((RiskEntry) object).getRemarks());
            arrayObject.add(((RiskEntry) object).getCreated_by());
        }
        else if (object instanceof RiskEntryImport){
            arrayObject.add(((RiskEntryImport) object).getMember_name());
            arrayObject.add(((RiskEntryImport) object).getMember_account());
            arrayObject.add(((RiskEntryImport) object).getDepositor_name());
            arrayObject.add(((RiskEntryImport) object).getBank_account_no());
            arrayObject.add(((RiskEntryImport) object).getEntry_reason());
            arrayObject.add(((RiskEntryImport) object).getRisk_id());
            arrayObject.add(((RiskEntryImport) object).getContact_number());
            arrayObject.add(((RiskEntryImport) object).getCreated_at());
            arrayObject.add(((RiskEntryImport) object).getRemarks());
            arrayObject.add(((RiskEntryImport) object).getCreated_by());
        }
        return arrayObject;
    }

    public User getLoggedInUser(){
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = loginMapper.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username:" + username));
        return user;
    }
}
