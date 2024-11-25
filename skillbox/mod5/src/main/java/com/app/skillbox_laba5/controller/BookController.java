package com.app.skillbox_laba5.controller;

import com.app.skillbox_laba5.domain.Book;
import com.app.skillbox_laba5.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book saved = bookService.create(book);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book saved = bookService.update(book, id);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Book> findByTittleAndAuthor(
            @RequestParam(name = "author") String author,
            @RequestParam(name = "title") String title) {
        Optional<Book> book = bookService.findByTitleAndAuthor(title, author);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> findByCategory(@RequestParam(name = "category") String category) {
        List<Book> books = bookService.findByCategory(category);
        return ResponseEntity.ok(books);
    }

}
