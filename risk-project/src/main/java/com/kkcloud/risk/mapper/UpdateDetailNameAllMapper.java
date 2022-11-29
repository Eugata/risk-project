package com.kkcloud.risk.mapper;
import com.kkcloud.risk.dto.UpdateDetailNameAllDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateDetailNameAllMapper {

    void updateDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto);
    void deleteDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto);
    boolean updateDetailChecker(UpdateDetailNameAllDTO updateDetailNameAlldto);
    boolean deleteChecker(UpdateDetailNameAllDTO updateDetailNameAlldto);
}
