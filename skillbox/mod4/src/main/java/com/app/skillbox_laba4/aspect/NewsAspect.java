package com.app.skillbox_laba4.aspect;

import com.app.skillbox_laba4.exception.UnauthorizedException;
import com.app.skillbox_laba4.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class NewsAspect {

    private final NewsRepository newsRepository;

    @Pointcut("@annotation(ValidateUserAuthority) && args(userId, id) && execution(* NewsController.*(..))")
    public void authorityValidation() { }

    @Before("authorityValidation()")
    public void validateAuthority(UUID userId, UUID id) {
         newsRepository.findById(id).ifPresentOrElse(news -> {
            if (news.getAuthor().getId() != userId) {
                throw new UnauthorizedException("User isn't author of news");
            }
        }, () -> {
            throw new EntityNotFoundException("News not found");
        });
    }
}
