package com.example.choonb.domain.post.entity;

import com.example.choonb.domain.post.dto.PostRequestDto;
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
@Getter
@NoArgsConstructor
public class Post extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private boolean adminPermission;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Post(PostRequestDto postRequestDto, User user) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
    this.user = user;
    this.adminPermission = postRequestDto.isAdminPermission();
  }

  public void update(PostRequestDto postRequestDto) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
    this.adminPermission = postRequestDto.isAdminPermission();
  }
}
