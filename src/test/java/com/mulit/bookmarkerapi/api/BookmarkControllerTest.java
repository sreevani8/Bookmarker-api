package com.mulit.bookmarkerapi.api;


import com.mulit.bookmarkerapi.domain.BookMark;
import com.mulit.bookmarkerapi.repository.BookmarkRepository;
import com.mulit.bookmarkerapi.service.BookmarkService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"

})
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<BookMark> bookmarks;


    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();

        bookmarks.add(new BookMark(null,"SpringBlog","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Spring","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"SpringMVC","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"SpringBoot","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"SpringBatch","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Microservices","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"java","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"sql","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Postgres","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Docker","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"AWS","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Jenkins","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"c#","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,".NET","https://spring.io/blog", Instant.now()));
        bookmarks.add(new BookMark(null,"Python","https://spring.io/blog", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }


    @ParameterizedTest
    @CsvSource({
            "1,15,2,1,true,false,true,false",
            "2,15,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage, boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious ) throws Exception {


            mvc.perform(get("/api/bookmarks?page="+pageNo))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                    .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                    .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                    .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
                    .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
                    .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
                    .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))

            ;


    }


}
