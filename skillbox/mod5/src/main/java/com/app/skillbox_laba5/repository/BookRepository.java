package com.app.skillbox_laba5.repository;

import com.app.skillbox_laba5.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleAndAuthor(String title, String author);

    List<Book> findAllByCategory_Name(String category);

}
