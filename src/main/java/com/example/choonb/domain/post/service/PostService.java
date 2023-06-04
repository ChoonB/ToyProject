package com.example.choonb.domain.post.service;

import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.post.dto.PostResponseDto;
import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.post.repository.PostRepository;
import com.example.choonb.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponseDto writePost(PostRequestDto postRequestDto, User user) {
    return new PostResponseDto(postRepository.saveAndFlush(new Post(postRequestDto, user)));
  }
}
