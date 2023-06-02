package com.example.choonb.domain.user.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

  @NotBlank
  private String username;
  @NotBlank
  private String nickname;
  @NotBlank
  private String password;

}
