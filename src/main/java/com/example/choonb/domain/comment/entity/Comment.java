package com.example.choonb.domain.comment.entity;

import com.example.choonb.domain.comment.dto.CommentRequestDto;
import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.post.entity.Timestamped;
import com.example.choonb.domain.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public Comment(CommentRequestDto commentRequestDto, User user, Post post) {
    this.content = commentRequestDto.getContent();
    this.user = user;
    this.post = post;
    post.addComment(this);
  }

  public void updateComment(CommentRequestDto commentRequestDto){
    this.content = commentRequestDto.getContent();
  }

}
