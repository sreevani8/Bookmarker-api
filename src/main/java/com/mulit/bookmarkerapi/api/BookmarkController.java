package com.mulit.bookmarkerapi.api;

import com.mulit.bookmarkerapi.DTO.BookmarksDTO;
import com.mulit.bookmarkerapi.domain.BookMark;
import com.mulit.bookmarkerapi.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page){
        return bookmarkService.getBookmarks(page);

    }
}
