package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.ViewLogsDTO;
import com.kkcloud.risk.mapper.ViewDetailMapper;
import com.kkcloud.risk.dto.ViewDetailDTO;
import com.kkcloud.risk.service.iface.IViewDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewDetailServiceImpl implements IViewDetailService {

    @Autowired
    private ViewDetailMapper viewDetailMapper;

    @Override
    public List<ViewLogsDTO> outputViewLog(ViewDetailDTO viewDetailDTO) {
        return viewDetailMapper.outputViewLog(viewDetailDTO);
    }
}
