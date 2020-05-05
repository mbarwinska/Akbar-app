package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.exceptions.SchoolNotFoundException;
import pl.barwinscy.Akbarapp.services.EmployeeService;
import pl.barwinscy.Akbarapp.services.SchoolService;
import pl.barwinscy.Akbarapp.services.SearchService;
import pl.barwinscy.Akbarapp.validators.NewSchoolFormValidator;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SchoolControllerTest {

    @Mock
    private SchoolService schoolService;
    @Mock
    private SearchService searchService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private NewSchoolFormValidator validator;

    private SchoolController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetSchoolViewPage() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(50L);

        when(validator.supports(any())).thenReturn(true);
        when(schoolService.getSchoolWithAllData(anyLong())).thenReturn(schoolDto);

        mockMvc.perform(get("/school/50/"))
                .andExpect(status().isOk())
                .andExpect(view().name("school-view"))
                .andExpect(model().attributeExists("school"));

    }

    @Test
    public void shouldShowErrorPageWhenSchoolNotFound() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalHandlerController())
                .build();

        when(schoolService.getSchoolWithAllData(anyLong())).thenThrow(SchoolNotFoundException.class);

        mockMvc.perform(get("/school/666/"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error-page"));
    }

    @Test
    public void shouldShowNewSchoolForm() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(validator.supports(any())).thenReturn(true);

        mockMvc.perform(get("/school/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("school-form"))
                .andExpect(model().attributeExists("school"))
                .andExpect(model().attribute("types", SchoolType.values()));

    }

    @Test
    public void shouldAddNewSchoolAndRedirect() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        School school = new School(123456L, SchoolType.SZKOŁA_PODSTAWOWA.getName(), "SP 109",
                new Address(Voivodeship.ŁÓDZKIE, "brzeziński", "Łódź", "Brzeziny", "", ""),
                "", "", "");
        school.setId(12L);

        when(validator.supports(any())).thenReturn(true);
        when(schoolService.save(any(SchoolDto.class))).thenReturn(school);

        mockMvc.perform(post("/school")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Lulasowo 13")
                .param("publicStatus", "prywatny")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/school/12"));
    }

    //TODO
    @Disabled("not ready yet")
    @Test
    public void shouldReturnToNewSchoolFormWhenValidationFail() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).
                setValidator(validator).build();
        School school = new School(123456L, SchoolType.SZKOŁA_PODSTAWOWA.getName(), "SP 109",
                new Address(Voivodeship.ŁÓDZKIE, "brzeziński", "Łódź", "Brzeziny", "", ""),
                "", "", "");

        when(validator.supports(any())).thenReturn(true);
        doCallRealMethod().when(validator).validate(any(), any());
        when(schoolService.save(any(SchoolDto.class))).thenReturn(school);


        mockMvc.perform(post("/school")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("zipCode", "blabla")
                .param("publicStatus", "publiczny"))
                .andExpect(model().errorCount(1));

    }

    @Test
    public void shouldUpdateSchoolAndRedirect() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        School school = new School(123456L, SchoolType.SZKOŁA_PODSTAWOWA.getName(), "SP 109",
                new Address(Voivodeship.ŁÓDZKIE, "brzeziński", "Łódź", "Brzeziny", "", ""),
                "", "", "");
        school.setId(15L);

        when(validator.supports(any())).thenReturn(true);
        when(schoolService.update(any(SchoolDto.class), any(PhoneDTO.class))).thenReturn(school);

        mockMvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Lulasowo 13")
                .param("publicStatus", "prywatny")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/school/15"));
    }

    @Test
    public void shouldDeletePhoneAndReturnToUpdatePage() throws Exception {
        controller = new SchoolController(schoolService, searchService, employeeService, validator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        School school = new School(123456L, SchoolType.SZKOŁA_PODSTAWOWA.getName(), "SP 109",
                new Address(Voivodeship.ŁÓDZKIE, "brzeziński", "Łódź", "Brzeziny", "", ""),
                "", "", "");
        school.setId(15L);
        Phone phone = new Phone("42 646-63-82");
        phone.setId(2L);

        when(validator.supports(any())).thenReturn(true);

        mockMvc.perform(get("/update/15/delete-phone/2"))
                .andExpect(status().is3xxRedirection());
    }


}
