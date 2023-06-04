package com.example.choonb.domain.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

  @NotBlank
  private String title;

  @NotBlank
  private String content;

  @NotNull
  private boolean adminPermission;
}
