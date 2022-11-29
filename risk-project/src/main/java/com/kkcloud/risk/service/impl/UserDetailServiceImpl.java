package com.kkcloud.risk.service.impl;

import com.kkcloud.risk.dto.UserDTO;
import com.kkcloud.risk.dto.UserDetailsDTO;
import com.kkcloud.risk.mapper.LoginMapper;
import com.kkcloud.risk.mapper.UserPermissionMapper;
import com.kkcloud.risk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    UserPermissionMapper userPermissionMapper;


    private final Set<GrantedAuthority> authorities = new HashSet<>();

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        User user = loginMapper.findByUsername(userEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username:" + userEmail));


        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getId());

        List<UserDetailsDTO> userDetailsDTO = userPermissionMapper.findById(userDTO);
        return new org.springframework.security.core.userdetails.User(userEmail,
                user.getPassword(), getAuthorities(userDetailsDTO));
    }

//    public UserDetailServiceImpl(){
//
//        authorities.add(new SimpleGrantedAuthority("USER"));
//
//
//    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<UserDetailsDTO> userDetailsDTOS) {

        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (UserDetailsDTO userDetailsDTO : userDetailsDTOS) {

            authorities.add(new SimpleGrantedAuthority(userDetailsDTO.getHeader_name()));
            userDetailsDTO.getDetails().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getDetail_name()))
                    .forEach(authorities::add);
        }

        return authorities;
    }
}