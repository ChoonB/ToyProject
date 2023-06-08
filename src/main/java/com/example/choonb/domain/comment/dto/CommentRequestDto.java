package com.example.choonb.domain.comment.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

  @NotBlank
  private String content;

}
