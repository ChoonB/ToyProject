package com.example.choonb.domain.post.service;

import com.example.choonb.domain.post.dto.EntirePostResponseDto;
import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.post.dto.PostResponseDto;
import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.post.repository.PostRepository;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.domain.user.entity.User;
import com.example.choonb.domain.user.entity.UserRoleEnum;
import com.example.choonb.global.exception.CustomException;
import com.example.choonb.global.exception.ExceptionType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

  public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto, User user) {
    Post post = findPostById(id);
    checkUser(post, user);

    post.update(postRequestDto);
    postRepository.saveAndFlush(post);
    return new PostResponseDto(post);

  }

  public MessageResponseDto deletePost(Long id, User user) {
    Post post = findPostById(id);
    checkUser(post, user);
    postRepository.delete(post);
    return new MessageResponseDto(HttpStatus.NO_CONTENT, "게시글이 정상적으로 삭제되었습니다.");
  }


  private Post findPostById(Long id){
    return postRepository.findById(id).orElseThrow(
        () -> new CustomException(ExceptionType.NOT_FOUND_POST_EXCEPTION)
    );
  }
  private void checkUser(Post post, User user) {
    if (!user.getRole().equals(UserRoleEnum.ADMIN) && !user.getId().equals(post.getUser().getId())) {
      throw new CustomException(ExceptionType.USER_UNAUTHORIZED_EXCEPTION);
    }
  }

}
