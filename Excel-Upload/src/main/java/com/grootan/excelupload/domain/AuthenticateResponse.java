package com.grootan.excelupload.domain;

import lombok.Getter;

@Getter
public class AuthenticateResponse {
    private final String access_token;

    public AuthenticateResponse(String jwt) {
        access_token = jwt;
    }
}


