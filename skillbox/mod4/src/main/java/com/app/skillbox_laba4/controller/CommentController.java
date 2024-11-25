package com.app.skillbox_laba4.controller;

import com.app.skillbox_laba4.aspect.ValidateUserAuthority;
import com.app.skillbox_laba4.domain.Comment;
import com.app.skillbox_laba4.domain.News;
import com.app.skillbox_laba4.dto.CommentDto;
import com.app.skillbox_laba4.mapper.CommentMapper;
import com.app.skillbox_laba4.repository.CommentRepository;
import com.app.skillbox_laba4.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/news/{newsId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final NewsRepository newsRepository;

    private final CommentMapper commentMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<CommentDto> create(@PathVariable UUID userId, @PathVariable UUID newsId, @RequestBody CommentDto commentDto) {
        if (!newsRepository.existsById(newsId)) {
            return ResponseEntity.notFound().build();
        }
        Comment comment = commentMapper.toEntity(commentDto);
        News news = newsRepository.findById(newsId).get();
        comment.setNews(news);
        comment = commentRepository.save(comment);
        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    @PutMapping("/{id}")
    @Transactional
    @ValidateUserAuthority
    public ResponseEntity<CommentDto> update(@PathVariable UUID userId, @PathVariable UUID newsId,
                                             @PathVariable UUID id, @RequestBody CommentDto commentDto) {
        if (!newsRepository.existsById(newsId) || !commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Comment comment = commentMapper.toEntity(commentDto);
        News news = newsRepository.findById(newsId).get();
        comment.setNews(news);
        comment.setId(id);
        comment = commentRepository.save(comment);
        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ValidateUserAuthority
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID newsId, @PathVariable UUID id) {
        if (!newsRepository.existsById(newsId) || !commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        newsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
