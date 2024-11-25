package com.app.skillbox_laba4.repository;

import com.app.skillbox_laba4.domain.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID>, PagingAndSortingRepository<News, UUID> {

    @Query("select n from News n join n.categories c join n.author a " +
            "where a.login in :authors and c.name in :categories")
    List<News> findAllPageable(@Param("authors") Collection<String> authors, @Param("categories") Collection<String> categories, Pageable pageable);

}
