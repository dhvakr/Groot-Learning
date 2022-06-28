package com.grootan.excelupload.controller;

import com.grootan.excelupload.domain.AuthenticateRequest;
import com.grootan.excelupload.domain.AuthenticateResponse;
import com.grootan.excelupload.service.JwtUserDetailsService;
import com.grootan.excelupload.utility.JwtUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUserDetailsService myUserDetailService;

    @Autowired
    JwtUtility jwtUtility;

    @SecurityRequirement(name = "Basic Auth")
    @Operation(summary = "Access Token", description = "Rest service to create access token")
    @PostMapping(value = "/token", produces = "application/json")
    public ResponseEntity<AuthenticateResponse> createsAuthenticationToken(@RequestBody AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(),
                        authenticateRequest.getPassword())
        );

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticateRequest.getUsername());

        final String jwtToken = jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticateResponse(jwtToken), HttpStatus.OK);
    }
}
