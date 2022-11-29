package com.kkcloud.risk.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RegisterDTO{
    private int user_id;

    private String user_name;

    @Email(regexp = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-z]{2,4}|[0-9]{1,3})(\\]?)$",
            message = "Please provide a valid email format")
    @NotEmpty(message = " Please provide an e-mail")
    private String user_email;

    @NotBlank(message = "Password cannot be empty or null.")
    private String user_password;


    private LocalDateTime created_at;

    private LocalDateTime updated_at;

}
