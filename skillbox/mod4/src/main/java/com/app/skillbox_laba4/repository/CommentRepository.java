package com.app.skillbox_laba4.repository;

import com.app.skillbox_laba4.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
