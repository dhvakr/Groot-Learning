package com.grootan.excelupload.domain;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String username;
    private String password;

    public AuthenticateRequest() {

    }

    public AuthenticateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
