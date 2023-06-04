package com.example.choonb.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class ConnectionController {

  @GetMapping("")
  public ModelAndView mainPage() {
    return new ModelAndView("index");
  }

  @GetMapping("/post")
  public ModelAndView postList() {
    return new ModelAndView("PostList");
  }

  @GetMapping("/post/{id}")
  public ModelAndView detailPost(@PathVariable Long id) {
    return new ModelAndView("GetPost");
  }

  @GetMapping("/login")
  public ModelAndView loginPage() {
    return new ModelAndView("login");
  }

  @GetMapping("/post/write")
  public ModelAndView writePage() {
    return new ModelAndView("writePost");
  }

}
