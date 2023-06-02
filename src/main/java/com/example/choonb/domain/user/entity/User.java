package com.example.choonb.domain.user.entity;

import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.user.dto.SignupRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false, unique = true)
  private String nickname;
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role = UserRoleEnum.USER;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Post> postList = new ArrayList<>();

  public User(SignupRequestDto signupRequestDto, String password) {
    this.username = signupRequestDto.getUsername();
    this.nickname = signupRequestDto.getNickname();
    this.password = password;
  }
}
