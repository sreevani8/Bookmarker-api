package com.mulit.bookmarkerapi.service;

import com.mulit.bookmarkerapi.domain.BookMark;
import com.mulit.bookmarkerapi.domain.BookmarkVM;
import com.mulit.bookmarkerapi.domain.CreateBookmarkRequest;
import com.mulit.bookmarkerapi.dto.BookmarkDTO;
import com.mulit.bookmarkerapi.dto.BookmarkMapper;
import com.mulit.bookmarkerapi.dto.BookmarksDTO;
import com.mulit.bookmarkerapi.repository.BookmarkRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;




@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)

    public BookmarksDTO getBookmarks(Integer page) {
        int pageNo = page<1?1:page-1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC,"createdAt");
        Page<BookmarkDTO> bookmarkDTOPage=  bookmarkRepository.findAll(pageable).map(bookmarkMapper::toDTO);
        return new BookmarksDTO(bookmarkDTOPage);

    }


    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(String query, Integer page) {
        int pageNo = page<1?1:page-1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC,"createdAt");
        Page<BookmarkDTO> bookmarkDTOPage=  bookmarkRepository.searchBookmarks(query, pageable) ;
        Page<BookmarkVM> bookmarkVMPage=  bookmarkRepository.findByTitleContainsIgnoreCase(query, pageable) ;
        return new BookmarksDTO(bookmarkDTOPage);


    }


    public BookmarkDTO createBookmark(@Valid CreateBookmarkRequest request) {
        BookMark bookmark = new BookMark(null,request.getTitle(), request.getUrl(), Instant.now());
        BookMark savedBookmark = bookmarkRepository.save(bookmark);
         return bookmarkMapper.toDTO(savedBookmark);

    }
}
