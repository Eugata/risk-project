package com.kkcloud.risk.controller;

import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.service.iface.RiskEntryListService;
import com.kkcloud.risk.service.iface.IEntryLogService;
import com.kkcloud.risk.service.iface.IRiskEntryService;
import com.kkcloud.risk.service.iface.IViewDetailService;
import com.kkcloud.risk.utils.RiskEntryListCsvHelper;
import com.kkcloud.risk.vo.ResponseHelper;
import com.kkcloud.risk.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

@Slf4j
@RestController
@CrossOrigin(origins = "127.0.0.1")
@RequestMapping("/api/riskmanagement")
public class RiskEntryController {

    @Autowired
    private IRiskEntryService riskService;

    @Autowired
    private IEntryLogService entryLogService;

    @Autowired
    private IViewDetailService viewDetailService;

    @Autowired
    RiskEntryListService riskEntryListService;

    public RiskEntryController() {

    }

    @PostMapping("/riskEntry/v1/addEntry")
    public ResponseVO addRiskEntry(@Valid @RequestBody RiskEntryDTO riskEntryDTO) {
        try {
            RiskEntryDTO riskEntryFromDB;
            ViewDetailDTO vDTO = riskService.checkDuplicateRiskEntry(riskEntryDTO.getMember_account());
            int entryId = vDTO.getEntry_id();

            //add risk entry
            if(entryId > 0) {
                //duplicate found
                vDTO.setOperation(("update"));
                riskEntryFromDB = riskService.getRiskEntryDTOById(vDTO.getEntry_id());//gets data stored in db
                riskEntryDTO.setEntry_id(entryId);
                riskService.overwriteDuplicateRow(riskEntryDTO);
            } else {
                //no duplicate found
                log.info("Success {}",riskEntryDTO);
                vDTO.setOperation(("add"));
                riskEntryFromDB = null;
                riskService.addRiskEntry(riskEntryDTO);
                entryId = riskService.getMaxRiskEntryId().getEntry_id();
            }
            log.info("Success {}",riskEntryDTO);
            vDTO.setUser_email(riskService.getUserFromSystem());
            vDTO.setEntry_id(entryId);
            entryLogService.addEntryLog(vDTO, riskEntryDTO, riskEntryFromDB);
            return ResponseHelper.success(new ArrayList());
        } catch(Exception e) {
            log.error("Error!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    @PostMapping("/riskEntry/v1/updateEntry")
    public ResponseVO updateRiskEntry(@Valid @RequestBody RiskEntryLogUpdateDTO riskEntryLogUpdateDTO) {
        try {

            int entryId = riskEntryLogUpdateDTO.getEntry_id();
            riskService.getRiskEntry(riskEntryLogUpdateDTO);
            entryId = riskService.getMaxRiskEntryId().getEntry_id();
            log.info("ken: {}", String.valueOf(entryId));
            log.info("Success {}", riskEntryLogUpdateDTO);
            return ResponseHelper.success();

        }   catch(Exception e) {
            log.error("Error!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    @PostMapping("/riskEntry/v1/deleteEntry")
    public ResponseVO deleteRiskEntry(@Valid @RequestBody ViewDetailDTO viewDetailDTO) {
        try {
            if(riskService.checkIfDeleted(viewDetailDTO.getEntry_id()) == false) {
                if(riskService.checkIfRiskEntryExists(viewDetailDTO) != null) {
                    ViewDetailDTO vDTO = riskService.checkAuditIdIfEmpty(1);
                    viewDetailDTO.setAudit_id(vDTO.getAudit_id());
                    viewDetailDTO.setUser_email(riskService.getUserFromSystem());
                    riskService.deleteRiskEntry(viewDetailDTO);
                    entryLogService.deletedEntryLog(viewDetailDTO);
                }
            }
            log.info("Success {}",viewDetailDTO);
            return ResponseHelper.success(new ArrayList());
        } catch(Exception e) {
            log.error("Error!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    @PostMapping("/riskEntry/v1/batchDeleteEntry")
    public ResponseVO batchDeleteRiskEntry(@RequestBody ViewDetailDTO viewDetailDTO) {
        try {
            ViewDetailDTO vDTO = riskService.checkAuditIdIfEmpty(0);
            vDTO.setUser_email(riskService.getUserFromSystem());
            for(int entryId : viewDetailDTO.getEntry_ids()) {
                if(riskService.checkIfDeleted(entryId) == false) {
                    vDTO.setEntry_id(entryId);
                    if(riskService.checkIfRiskEntryExists(vDTO) != null) {
                        vDTO.setAudit_id(vDTO.getAudit_id() + 1);
                        vDTO.setUpdated_at(viewDetailDTO.getUpdated_at());
                        vDTO.setUpdated_by(viewDetailDTO.getUpdated_by());
                        riskService.deleteRiskEntry(vDTO);
                        entryLogService.deletedEntryLog(vDTO);
                    }
                }
            }
            log.info("Success {}",viewDetailDTO);
            return ResponseHelper.success(new ArrayList());
        } catch(Exception e) {
            log.error("Error!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    //entryLogs
    @PostMapping("/riskEntry/v1/entryLogs")
    public ResponseVO outputViewLog(@Valid @RequestBody ViewDetailDTO viewDetailDTO) {
        try {
            List<ViewLogsDTO> viewLogs = viewDetailService.outputViewLog(viewDetailDTO);
            log.info("Success {}",viewDetailDTO);
            return ResponseHelper.success(viewLogs);
        } catch(Exception e) {
            log.error("Error!", e.getMessage(), e);
            e.printStackTrace();
            return ResponseHelper.error();
        }
    }

    @PostMapping("/riskEntry/v1/entryListImport")
    public ResponseVO uploadCsvFile(@RequestParam("file") MultipartFile file) {
        if (RiskEntryListCsvHelper.hasCSVFormat(file)) {
            log.info("Success");
            return riskEntryListService.uploadRiskList(file);
        }
        log.error("error {}",ResponseHelper.fileError());
        return ResponseHelper.fileError();
    }

    @PostMapping("/riskEntry/v1/entryList")
    public ResponseVO<RiskResultDTO> getQueryList(@RequestBody RiskEntryListDTO riskEntryListDto) {
        log.info("Success {}",riskEntryListDto);
        return riskEntryListService.findAllRisk(riskEntryListDto);
    }

    @PostMapping("/riskEntry/v1/entryListExport")
    public ResponseEntity<Resource> downloadFile(@RequestBody RiskEntryListDTO riskEntryListDTO) {
        InputStreamResource file = new InputStreamResource(riskEntryListService.downloadRiskList(riskEntryListDTO));
        log.info("Success {}",riskEntryListDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + now()+"_Risk_Control_List.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}

