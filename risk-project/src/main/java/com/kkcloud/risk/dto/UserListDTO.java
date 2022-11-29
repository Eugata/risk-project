package com.kkcloud.risk.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserListDTO {

    private int user_id;
    private String user_name;
    private String user_email;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;

    public UserListDTO(String user_name, String user_email, String created_at, String updated_at) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UserListDTO(int user_id, String user_name, String user_email, String created_at, String updated_at) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UserListDTO(int user_id, String user_name, String user_email, String created_at, String updated_at, String created_by, String updated_by) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
    }
}
