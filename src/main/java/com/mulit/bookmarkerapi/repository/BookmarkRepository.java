package com.mulit.bookmarkerapi.repository;

import com.mulit.bookmarkerapi.dto.BookmarkDTO;
import com.mulit.bookmarkerapi.domain.BookMark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  BookmarkRepository extends JpaRepository<BookMark, Long> {


    @Query("select new com.mulit.bookmarkerapi.dto.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from BookMark b")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);
}
