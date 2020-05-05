package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.SalesmanRepository;
import pl.barwinscy.Akbarapp.services.EmployeeService;
import pl.barwinscy.Akbarapp.services.EmployeeServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private SalesmanRepository salesmanRepository;
    private EmployeeService employeeService;
    private EmployeeController controller;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getEmployeesPage() throws Exception {
        employeeService = new EmployeeServiceImpl(employeeRepository, salesmanRepository);
        controller = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("newEmployee"))
                .andExpect(model().attributeExists("newSalesman"))
                .andExpect(model().attributeExists("employees"))
                .andExpect(model().attributeExists("salesmen"));

    }

    @Test
    void addEmployee() throws Exception {
        EmployeeService mockService = mock(EmployeeServiceImpl.class);
        controller = new EmployeeController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Employee employee = new Employee("Jan", "Kowalski");

        when(mockService.addEmployee(any(EmployeeDto.class))).thenReturn(employee);

        mockMvc.perform(get("/employees/add/"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("employees"))
                .andExpect(model().attributeExists("salesmen"));

        verify(mockService, times(1)).addEmployee(any(EmployeeDto.class));
    }

    @Test
    void addSalesman() throws Exception {
        EmployeeService mockService = mock(EmployeeServiceImpl.class);
        controller = new EmployeeController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Salesman salesman = new Salesman("Marian", "");

        when(mockService.addSalesman(any(SalesmanDto.class))).thenReturn(salesman);

        mockMvc.perform(get("/salesmen/add/"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("salesmen"))
                .andExpect(model().attributeExists("employees"));

        verify(mockService, times(1)).addSalesman(any(SalesmanDto.class));
    }
}