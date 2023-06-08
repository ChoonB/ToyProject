package com.example.choonb.domain.post.controller;

import com.example.choonb.domain.post.dto.EntirePostResponseDto;
import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.post.dto.PostResponseDto;
import com.example.choonb.domain.post.service.PostService;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.global.security.UserDetailsImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

  private final PostService postService;

//  글쓰기
  @PostMapping("")
  public PostResponseDto writePost(@RequestBody @Valid PostRequestDto postRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    return postService.writePost(postRequestDto, userDetails.getUser());
  }

//  상세조회
  @GetMapping("/{id}")
  public PostResponseDto getPost(@PathVariable Long id) {
    return postService.getPost(id);
  }

//  전체조회
  @GetMapping("")
  public List<EntirePostResponseDto> getPostList(){
    return postService.getPostList();
  }

//  글 수정
  @PatchMapping("/{id}")
  public PostResponseDto updatePost(@RequestBody @Valid PostRequestDto postRequestDto,
      @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return postService.updatePost(id, postRequestDto, userDetails.getUser());
  }

//  글 삭제
  @DeleteMapping("/{id}")
  public MessageResponseDto deletePost(@PathVariable Long id,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    return postService.deletePost(id, userDetails.getUser());
  }

}
