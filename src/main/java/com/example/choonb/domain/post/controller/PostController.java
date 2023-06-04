package com.example.choonb.domain.post.controller;

import com.example.choonb.domain.post.dto.EntirePostResponseDto;
import com.example.choonb.domain.post.dto.PostRequestDto;
import com.example.choonb.domain.post.dto.PostResponseDto;
import com.example.choonb.domain.post.service.PostService;
import com.example.choonb.global.security.UserDetailsImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("")
  public PostResponseDto writePost(@RequestBody @Valid PostRequestDto postRequestDto,
      @AuthenticationPrincipal UserDetailsImpl userDetails){
    return postService.writePost(postRequestDto, userDetails.getUser());
  }

  @GetMapping("/{id}")
  public PostResponseDto getPost(@PathVariable Long id) {
    return postService.getPost(id);
  }

  @GetMapping("")
  public List<EntirePostResponseDto> getPostList(){
    return postService.getPostList();
  }

}
