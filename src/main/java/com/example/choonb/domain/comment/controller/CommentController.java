package com.example.choonb.domain.comment.controller;

import com.example.choonb.domain.comment.dto.CommentRequestDto;
import com.example.choonb.domain.comment.dto.CommentResponseDto;
import com.example.choonb.domain.comment.service.CommentService;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.global.security.UserDetailsImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}/comment")
  public CommentResponseDto writeComment(@PathVariable final Long postId,
      @RequestBody @Valid final CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal final UserDetailsImpl userDetails) {
    return commentService.writeComment(postId, commentRequestDto, userDetails.getUser());
  }

  @PatchMapping("/{postId}/comment/{commentId}")
  public CommentResponseDto updateComment(@PathVariable final Long commentId,
      @RequestBody @Valid final CommentRequestDto commentRequestDto,
      @AuthenticationPrincipal final UserDetailsImpl userDetails) {
    return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
  }

  @DeleteMapping("/{postId}/comment/{commentId}")
  public MessageResponseDto deleteComment(@PathVariable final Long commentId,
      @AuthenticationPrincipal final UserDetailsImpl userDetails) {
    return commentService.deleteComment(commentId, userDetails.getUser());
  }

}
