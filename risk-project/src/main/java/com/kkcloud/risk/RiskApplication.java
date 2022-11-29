package com.kkcloud.risk;

import com.kkcloud.risk.dto.RegisterDTO;
import com.kkcloud.risk.service.iface.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableApolloConfig
public class RiskApplication {

    public static void main(String[] args) {
        System.out.println("FAT TESTING . . .");
        SpringApplication.run(RiskApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(UserService userService) {
        return (args) -> {
//            RegisterDTO registerDTO = new RegisterDTO();
//            registerDTO.setUser_id(11);
//            registerDTO.setUser_name("admin");
//            registerDTO.setUser_email("admin@itcom888.com");
//            registerDTO.setUser_password("password");
//            registerDTO.setCreated_at(LocalDateTime.now());
//            registerDTO.setUpdated_at(LocalDateTime.now());
//
////            LoginDTO loginDTO = new LoginDTO();
////            loginDTO.setUser_name("ronsky");
////            loginDTO.setUser_password("password");
//
//            userService.registerUser(registerDTO);

//           System.out.println(userService.getAllUsers());
            for (String arg : args) {
                System.out.println(arg);
            }
        };
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*");
//            }
//        };
//    }

}
