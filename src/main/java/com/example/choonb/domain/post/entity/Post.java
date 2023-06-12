package com.example.choonb.domain.post.entity;

import com.example.choonb.domain.comment.entity.Comment;
import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "post")
  private List<Comment> commentList = new ArrayList<>();


  public Post(PostRequestDto postRequestDto, User user) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
    this.user = user;
  }

  public void update(PostRequestDto postRequestDto) {
    this.title = postRequestDto.getTitle();
    this.content = postRequestDto.getContent();
  }

  public void addComment(Comment comment) {
    this.commentList.add(comment);
  }
}
