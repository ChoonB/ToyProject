package com.example.choonb.domain.comment.service;

import com.example.choonb.domain.comment.dto.CommentRequestDto;
import com.example.choonb.domain.comment.dto.CommentResponseDto;
import com.example.choonb.domain.comment.entity.Comment;
import com.example.choonb.domain.comment.repository.CommentRepository;
import com.example.choonb.domain.post.entity.Post;
import com.example.choonb.domain.post.repository.PostRepository;
import com.example.choonb.domain.user.dto.MessageResponseDto;
import com.example.choonb.domain.user.entity.User;
import com.example.choonb.global.exception.CustomException;
import com.example.choonb.global.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostRepository postRepository;

  public CommentResponseDto writeComment(Long postId, CommentRequestDto commentRequestDto, User user) {
    Post post = findPostByPostId(postId);
    return new CommentResponseDto(commentRepository.save(new Comment(commentRequestDto, user, post)));
  }


  public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
    Comment comment = findCommentByCommentId(commentId);
    comment.updateComment(commentRequestDto);
    return new CommentResponseDto(commentRepository.save(comment));
  }

  public MessageResponseDto deleteComment(Long commentId, User user) {
    Comment comment = findCommentByCommentId(commentId);
    if (user.getId().equals(comment.getUser().getId())){
      commentRepository.delete(comment);
    }
    return new MessageResponseDto(HttpStatus.NO_CONTENT, "댓글이 정상적으로 삭제되었습니다.");
  }

  private Post findPostByPostId(Long postId){
    return postRepository.findById(postId).orElseThrow(
        () -> new CustomException(ExceptionType.NOT_FOUND_POST_EXCEPTION)
    );
  }

  private Comment findCommentByCommentId(Long commentId){
    return commentRepository.findById(commentId).orElseThrow(
        () -> new CustomException(ExceptionType.NOT_FOUND_COMMENT_EXCEPTION)
    );
  }



}
