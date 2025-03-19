package com.mulit.bookmarkerapi.dto;

import com.mulit.bookmarkerapi.domain.BookMark;
import org.springframework.stereotype.Component;


@Component
public class BookmarkMapper {

    public BookmarkDTO toDTO(BookMark bookmark) {
        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        bookmarkDTO.setId(bookmark.getId());
        bookmarkDTO.setTitle(bookmark.getTitle());
        bookmarkDTO.setUrl(bookmark.getUrl());
        bookmarkDTO.setCreatedAt(bookmark.getCreatedAt());

        return bookmarkDTO;


    }
}
