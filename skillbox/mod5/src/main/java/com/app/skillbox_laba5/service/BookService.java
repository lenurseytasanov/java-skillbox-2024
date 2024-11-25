package com.app.skillbox_laba5.service;

import com.app.skillbox_laba5.domain.Book;
import com.app.skillbox_laba5.repository.BookRepository;
import com.app.skillbox_laba5.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    private void setCategoryIfExists(Book book) {
        categoryRepository.findByName(book.getCategory().getName())
                .ifPresent(book::setCategory);
    }

    @Transactional
    @Caching(put = {
            @CachePut(cacheNames = "book", key = "#book.getTitle() + #book.getAuthor()"),
            @CachePut(cacheNames = "book", key = "#book.getCategory().getName()")
    })
    public Book create(@NonNull Book book) {
        setCategoryIfExists(book);
        return bookRepository.save(book);
    }

    @Transactional
    @Caching(put = {
            @CachePut(cacheNames = "book", key = "#book.getTitle() + #book.getAuthor()"),
            @CachePut(cacheNames = "book", key = "#book.getCategory().getName()")
    })
    public Book update(@NonNull Book book, @NonNull Long id) {
        setCategoryIfExists(book);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "book", key = "#result.getTitle() + #result.getAuthor()"),
            @CacheEvict(cacheNames = "book", key = "#result.getCategory().getName()")
    })
    public Book delete(@NonNull Long id) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookRepository.delete(book);
        return book;
    }


    @Cacheable(cacheNames = "book", key = "#title + #author")
    public Optional<Book> findByTitleAndAuthor(@NonNull String title, @NonNull String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Cacheable(cacheNames = "book", key = "#category")
    public List<Book> findByCategory(@NonNull String category) {
        return bookRepository.findAllByCategory_Name(category);
    }

}
