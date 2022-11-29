package com.kkcloud.risk.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class LoginDTO {
    private int userId;

    @Email
    private String user_email;

    @NotEmpty
    @NotBlank
    private String user_password;

    private String createdDate;
    private String updatedDate;

}
