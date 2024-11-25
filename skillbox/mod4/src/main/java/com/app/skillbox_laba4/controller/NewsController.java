package com.app.skillbox_laba4.controller;

import com.app.skillbox_laba4.aspect.ValidateUserAuthority;
import com.app.skillbox_laba4.domain.News;
import com.app.skillbox_laba4.dto.NewsDetailedDto;
import com.app.skillbox_laba4.dto.NewsDto;
import com.app.skillbox_laba4.mapper.NewsMapper;
import com.app.skillbox_laba4.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsRepository newsRepository;

    private final NewsMapper newsMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<NewsDto> create(@RequestBody NewsDto newsDto, @PathVariable UUID userId) {
        News news = newsMapper.toEntity(newsDto);
        news = newsRepository.save(news);
        return ResponseEntity.ok(newsMapper.toDto(news));
    }

    @PutMapping("/{id}")
    @Transactional
    @ValidateUserAuthority
    public ResponseEntity<NewsDto> update(@RequestBody NewsDto newsDto, @PathVariable UUID userId, @PathVariable UUID id) {
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        News news = newsMapper.toEntity(newsDto);
        news.setId(id);
        news = newsRepository.save(news);
        return ResponseEntity.ok(newsMapper.toDto(news));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<NewsDetailedDto> find(@PathVariable UUID userId, @PathVariable UUID id) {
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        News news = newsRepository.findById(id).get();
        return ResponseEntity.ok(newsMapper.toDetailedDto(news));
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<NewsDto>> findAll(
            @PathVariable UUID userId,
            @RequestParam(name = "authors", required = false) List<String> authors,
            @RequestParam(name = "cats", required = false) List<String> cats,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        Pageable pageable;
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size);
        } else if (page == null && size != null) {
            pageable = PageRequest.ofSize(size);
        } else {
            pageable = Pageable.unpaged();
        }
        List<News> newsList = newsRepository.findAllPageable(authors, cats, pageable);
        return ResponseEntity.ok(newsList.stream()
                .map(newsMapper::toDto).toList());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ValidateUserAuthority
    public ResponseEntity<Void> delete(@PathVariable UUID userId, @PathVariable UUID id) {
        if (!newsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        newsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}