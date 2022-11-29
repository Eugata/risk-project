package com.kkcloud.risk.service.impl;
import com.kkcloud.risk.dto.*;
import com.kkcloud.risk.mapper.UpdateDetailNameAllMapper;
import com.kkcloud.risk.service.iface.UpdateDetailNameAllService;
import com.kkcloud.risk.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UpdateDetailNameAllServiceImpl implements UpdateDetailNameAllService {

    @Autowired
    UpdateDetailNameAllMapper updateDetailNameAllMapper;



    @Override
    public String updateDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto) {
//to get username and time of the one logged in //here
        String userName;
        LocalDateTime updated_at = LocalDateTime.now();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }

        updateDetailNameAlldto.setUsername(userName);

        updateDetailNameAlldto.setUpdated_at(updated_at);

        //to get username and time of the one logged in // ends here

        boolean ifnotMissing = updateDetailNameAllMapper.updateDetailChecker(updateDetailNameAlldto);

        if (ifnotMissing == true) {

            updateDetailNameAllMapper.updateDetailNames(updateDetailNameAlldto);

            return "Successful Operation";

        } else {
            return "Detail name not found";
        }

    }

    //delete removed for now due to removed delete column

//    @Override
//    public String deleteDetailNames(UpdateDetailNameAllDTO updateDetailNameAlldto){
//
//        //to get username and time of the one logged in //here
//        String userName;
//        LocalDateTime updated_at = LocalDateTime.now();
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails)principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//
//        updateDetailNameAlldto.setUsername(userName);
//
//        updateDetailNameAlldto.setUpdated_at(updated_at);
//
//        //to get username and time of the one logged in // ends here
//
//        boolean notyetDeleted = updateDetailNameAllMapper.deleteChecker(updateDetailNameAlldto);
//
//        if (notyetDeleted == true) {
//
//            updateDetailNameAllMapper.deleteDetailNames(updateDetailNameAlldto);
//
//            return "Successful Operation!";
//
//        }
//        else    {
//            return "Already deleted";
//        }
//    }



}