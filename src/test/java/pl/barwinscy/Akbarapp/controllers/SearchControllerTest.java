package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.barwinscy.Akbarapp.services.SearchService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SearchControllerTest {

    private SearchService searchService;
    private  SearchController searchController;
    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        searchController = new SearchController(searchService);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void getSearchPage() throws Exception {

        mockMvc.perform(get("/search/"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attributeExists("voivodeships"))
                .andExpect(model().attributeExists("types"));
    }

    @Test
    void getSearchResults() throws Exception {

    }

    }
