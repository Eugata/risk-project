package com.kkcloud.risk.service.iface;
import com.kkcloud.risk.dto.UpdateDetailNameAllDTO;
import com.kkcloud.risk.vo.ResponseVO;


public interface UpdateDetailNameAllService {

    String updateDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto);
//    String deleteDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto);  //delete column removed for now thus removing this

}
