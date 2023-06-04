package com.example.choonb.domain.user.service;

import com.example.choonb.domain.user.dto.LoginRequestDto;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.domain.user.dto.SignupRequestDto;
import com.example.choonb.domain.user.entity.User;
import com.example.choonb.domain.user.repository.UserRepository;
import com.example.choonb.global.exception.CustomException;
import com.example.choonb.global.exception.ExceptionType;
import com.example.choonb.global.jwt.JwtUtil;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public MessageResponseDto signup(SignupRequestDto signupRequestDto) {

    boolean isUsernameExist = userRepository.existsByUsername(signupRequestDto.getUsername());
    if (isUsernameExist) throw new CustomException(ExceptionType.EXISTED_USERNAME_EXCEPTION);

    boolean isNicknameExist = userRepository.existsByNickname(signupRequestDto.getNickname());
    if (isNicknameExist) throw new CustomException(ExceptionType.EXISTED_NICKNAME_EXCEPTION);

    String encryptedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

    User user = new User(signupRequestDto, encryptedPassword);
    userRepository.saveAndFlush(user);

    return new MessageResponseDto(HttpStatus.CREATED, "회원가입 완료");
  }

  public MessageResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

    User user = userRepository.findByUsername(loginRequestDto.getUsername());

    String encryptPassword = user.getPassword();
    if (!passwordEncoder.matches(loginRequestDto.getPassword(), encryptPassword)) {
      throw new CustomException(ExceptionType.PASSWORD_NOT_MATCHING_EXCEPTION);
    }

    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getNickname(), user.getRole()));

    return new MessageResponseDto(HttpStatus.OK, "로그인 완료");
  }
}
