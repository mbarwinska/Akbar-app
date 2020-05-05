package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.services.SearchServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FiltersControllerTest {

    @Mock
    private SchoolRepository schoolRepository;
    private SearchService searchService;
    private FiltersController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldShowFiltersPage() throws Exception {
        searchService = new SearchServiceImpl(schoolRepository);
        controller = new FiltersController(searchService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/filters/"))
                .andExpect(status().is(200))
                .andExpect(view().name("filters"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attribute("voivodeships", Voivodeship.values()))
                .andExpect(model().attribute("types", SchoolType.values()))
                .andExpect(model().attribute("counties", searchService.getAllCounties()));
    }

}