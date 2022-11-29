package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.RiskEntryListDTO;
import com.kkcloud.risk.dto.RiskResultDTO;
import com.kkcloud.risk.vo.ResponseVO;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;

public interface RiskEntryListService {

    ResponseVO uploadRiskList(MultipartFile file);
    ByteArrayInputStream downloadRiskList(RiskEntryListDTO riskEntryListDto);
    ResponseVO<RiskResultDTO> findAllRisk(RiskEntryListDTO riskEntryListDto);

}
