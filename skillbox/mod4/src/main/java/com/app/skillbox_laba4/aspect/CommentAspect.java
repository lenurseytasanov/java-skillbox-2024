package com.app.skillbox_laba4.aspect;

import com.app.skillbox_laba4.exception.UnauthorizedException;
import com.app.skillbox_laba4.repository.CommentRepository;
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
public class CommentAspect {

    private final CommentRepository commentRepository;

    @Pointcut("@annotation(ValidateUserAuthority) && args(userId, id) && execution(* CommentController.*(..))")
    public void authorityValidation() { }

    @Before("authorityValidation()")
    public void validateAuthority(UUID userId, UUID id) {
        commentRepository.findById(id).ifPresentOrElse(comment -> {
                if (comment.getAuthor().getId() != userId) {
                    throw new UnauthorizedException("User isn't author of comment");
                }
            }, () -> {
                throw new EntityNotFoundException("Comment not found");
            });
    }

}
