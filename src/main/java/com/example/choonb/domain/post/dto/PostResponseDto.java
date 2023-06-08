package com.example.choonb.domain.post.dto;

import com.example.choonb.domain.comment.dto.CommentResponseDto;
import com.example.choonb.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

  private Long id;
  private String nickname;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();


  public PostResponseDto(Post post) {
    this.id = post.getId();
    this.nickname = post.getUser().getNickname();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.createdAt = post.getCreatedAt();
    this.modifiedAt = post.getModifiedAt();
    if (!post.getCommentList().isEmpty()) {
      this.commentResponseDtoList = post.getCommentList()
          .stream().map(CommentResponseDto::new)
          .collect(Collectors.toList());
    }
  }
}
