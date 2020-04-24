package pl.barwinscy.Akbarapp.services;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.SalesmanRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EmployeeServiceImplTest {


    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SalesmanRepository salesmanRepository;


    private EmployeeService employeeService;

    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    @BeforeEach
    public void setUp() throws Exception {
        employeeService = new EmployeeServiceImpl(employeeRepository, salesmanRepository);
    }
    @Order(1)
    @Rollback(false)
    @Test
    public void shouldAddSchoolsAndPhonesToDB() {
        saveSchoolsToDB();
    }

    @Order(2)
    @Rollback(false)
    @Test
    void addEmployee() {
        EmployeeDto newEmployee = new EmployeeDto();
        String firstName = "Jan";
        String lastName = "Kowalski";
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);

        employeeService.addEmployee(newEmployee);
        Employee employeeFromDB = employeeRepository.findByFirstNameAndLastName(firstName, lastName);

        assertThat(employeeFromDB.getFirstName()).isEqualTo(firstName);
    }

    @Order(3)
    @Rollback(false)
    @Test
    void addSalesman() {
        SalesmanDto newSalesman = new SalesmanDto();
        String firstName = "Andrzej";
        String lastName = "Nowak";
        newSalesman.setFirstName(firstName);
        newSalesman.setLastName(lastName);

        employeeService.addSalesman(newSalesman);
        Salesman salesmanFromDB = salesmanRepository.findByFirstNameAndLastName(firstName, lastName);

        assertThat(salesmanFromDB.getFirstName()).isEqualTo(firstName);
    }

    @Order(4)
    @Rollback(false)
    @Test
    void getAllPhotographers() {
        EmployeeDto newEmployee = new EmployeeDto();
        String firstName = "Paweł";
        String lastName = "Bednarz";
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        employeeService.addEmployee(newEmployee);

        List<Employee> allPhotographers = employeeService.getAllPhotographers();

        assertThat(allPhotographers.size()).isEqualTo(2);
    }

    @Order(5)
    @Rollback(false)
    @Test
    void getAllSalesmen() {
        SalesmanDto newSalesman = new SalesmanDto();
        String firstName = "Michał";
        String lastName = "Lewczuk";
        newSalesman.setFirstName(firstName);
        newSalesman.setLastName(lastName);
        employeeService.addSalesman(newSalesman);

        List<Salesman> allSalesmen = employeeService.getAllSalesmen();

        assertThat(allSalesmen.size()).isEqualTo(2);
    }
}