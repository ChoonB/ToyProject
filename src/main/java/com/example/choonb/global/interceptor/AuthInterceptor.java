package com.example.choonb.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

//  로그인 하지 않은 유저는 login 페이지로 redirect.
//  로그인 한 유저만 접근 가능하게 설정
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (!isUserLogin()) {
      log.info("로그인 x");
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }

  private boolean isUserLogin(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
      log.info("로그인 o");
      return true;
    }
    return false;
  }
}
