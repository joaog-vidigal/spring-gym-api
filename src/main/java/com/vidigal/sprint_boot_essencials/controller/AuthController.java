package com.vidigal.sprint_boot_essencials.controller;

import com.vidigal.sprint_boot_essencials.dto.LoginRequestDto;
import com.vidigal.sprint_boot_essencials.dto.RegisterRequestDto;
import com.vidigal.sprint_boot_essencials.dto.TokenResponseDto;
import com.vidigal.sprint_boot_essencials.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {

        authenticationService.register(registerRequestDto);
    }


    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception{

        return authenticationService.login(loginRequestDto);
    }


}
