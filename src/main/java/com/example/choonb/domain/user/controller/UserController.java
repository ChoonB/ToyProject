package com.example.choonb.domain.user.controller;

import com.example.choonb.domain.user.dto.LoginRequestDto;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.domain.user.dto.SignupRequestDto;
import com.example.choonb.domain.user.service.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;


  @PostMapping("/signup")
  public MessageResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
    return userService.signup(signupRequestDto);
  }

  @GetMapping("/login")
  public MessageResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
    return userService.login(loginRequestDto, response);
  }

}
