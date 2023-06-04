package com.example.choonb.domain.post.service;

import com.example.choonb.domain.post.dto.EntirePostResponseDto;
import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.post.dto.PostResponseDto;
import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.post.repository.PostRepository;
import com.example.choonb.domain.user.entity.User;
import com.example.choonb.global.exception.CustomException;
import com.example.choonb.global.exception.ExceptionType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponseDto writePost(PostRequestDto postRequestDto, User user) {
    return new PostResponseDto(postRepository.saveAndFlush(new Post(postRequestDto, user)));
  }

  public PostResponseDto getPost(Long id) {
    return postRepository.findById(id)
        .map(PostResponseDto::new)
        .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_POST_EXCEPTION));
  }


  public List<EntirePostResponseDto> getPostList() {
    return postRepository.findAll()
        .stream()
        .map(EntirePostResponseDto::new)
        .toList();
  }
}
