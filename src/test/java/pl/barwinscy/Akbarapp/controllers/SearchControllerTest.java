package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.services.SearchServiceImpl;
import pl.barwinscy.Akbarapp.utils.SearchQueryCreator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SearchControllerTest {


    @Mock
    private SchoolRepository schoolRepository;
    private SearchService searchService;
    private SearchController searchController;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getSearchPage() throws Exception {
        searchService = new SearchServiceImpl(schoolRepository);
        searchController = new SearchController(searchService);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();

        mockMvc.perform(get("/search/"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attributeExists("voivodeships"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("counties"));
    }

    //TODO
    @Disabled("not ready yet")
    @Test
    void getSearchResults() throws Exception {

        SearchQueryCreator mockSearchQueryCreator = Mockito.mock(SearchQueryCreator.class);
        SearchServiceImpl mockService = Mockito.mock(SearchServiceImpl.class);
//        searchService = new SearchServiceImpl(schoolRepository);
        searchController = new SearchController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();

        List<School> schools = new ArrayList<>();
        schools.add(new School
                (123456L, SchoolType.SZKOŁA_PODSTAWOWA.getName(), "SP 109",
                        new Address(Voivodeship.ŁÓDZKIE, "brzeziński", "Łódź", "Brzeziny", "", ""),
                        "", "", ""));

        Mockito.when(mockSearchQueryCreator.createSearchQuery()).thenReturn("");
        Mockito.when(mockService.getSearchedSchools(mockSearchQueryCreator.createSearchQuery())).thenReturn(schools);
//        Mockito.when(searchService.getSearchedSchools(Mockito.anyString())).thenReturn(schools);

//        assertThat(mockService.getSearchedSchools("bla")).size().isEqualTo(1);
        assertThat(mockSearchQueryCreator.createSearchQuery()).isEqualTo("");

/*
        mockMvc.perform(get("/search-result"))
                .andExpect(status().isOk());
*/

    }

}
