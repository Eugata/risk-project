package com.kkcloud.risk.mapper;

import com.kkcloud.risk.dto.HeaderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface PermissionUpdateMapper {
     void updateHeaderDetails(HeaderDTO headerDTO);
     Boolean validateHeader(HeaderDTO headerDTO);
//     Boolean validateDeleteHeader(HeaderDTO headerDTO);
     Boolean validateExistingDetails(@Param("userId") int userId, @Param("headerName") String headerName, @Param("detailsName") String detailsName);
//     void deleteHeaderDetails( @Param("headerId") int headerID, @Param("userName") String userName, @Param("updatedAt") LocalDateTime updatedAt);
     void addPermissionOfUser(@Param("userId") int userId, @Param("headerName") String headerName, @Param("detailsName") String detailsName,
                              @Param("isAllowed") Boolean isAllowed,
                              @Param("createdAt") LocalDateTime createdAt,
                              @Param("updatedAt") LocalDateTime updatedAt,
                              @Param("userName") String userName);
     void updateDetails(@Param("detailsName") String detailsName, @Param("isAllowed") Boolean isAllowed, @Param("userId") int userId, @Param("headerName") String headerName);

}
