package com.example.choonb.global.aop;

import com.example.choonb.global.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ProcessTimeAop {

  /*
  Admin 계정으로 글을 작성할 때 processTime을 측정
  writePost 메서드의 2번째 파라미터인 UserDetails를 활용해 admin 유저 여부 check
   */
  @Around("execution(public * com.example.choonb.domain.post.controller.PostController.writePost(..)) && args(.., userDetails))")
  public Object checkProcessTime(ProceedingJoinPoint joinPoint, UserDetailsImpl userDetails) throws Throwable {
    boolean isAdmin = userDetails.getAuthorities().stream()
        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

    if (!isAdmin) return joinPoint.proceed();

    long startTime = System.currentTimeMillis();

    try {
      return joinPoint.proceed();
    } finally {
      long processTime = System.currentTimeMillis() - startTime;
      log.info("[ProcessTime] : " + processTime + "ms");
    }
  }

}
