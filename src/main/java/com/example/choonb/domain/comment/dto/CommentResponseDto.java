package com.example.choonb.domain.comment.dto;

import com.example.choonb.domain.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentResponseDto {

  private Long id;
  private String nickname;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getId();
    this.nickname = comment.getUser().getNickname();
    this.content = comment.getContent();
    this.createdAt = comment.getCreatedAt();
    this.modifiedAt = comment.getModifiedAt();
  }
}
