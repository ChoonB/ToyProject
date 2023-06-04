package com.example.choonb.domain.post.dto;

import com.example.choonb.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EntirePostResponseDto {

  private String title;
  private String nickname;
  private LocalDateTime createdAt;

  public EntirePostResponseDto(Post post) {
    this.title = post.getTitle();
    this.nickname = post.getUser().getNickname();
    this.createdAt = post.getCreatedAt();
  }
}
