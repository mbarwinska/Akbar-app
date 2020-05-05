package pl.barwinscy.Akbarapp.controllers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.entities.*;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class SchoolServiceIT {

    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    Status status1 = new Status(true, true, 15);
    Status status2 = new Status(true, false, 0);
    Schedule schedule1 = new Schedule(LocalDate.of(2020, 02, 12), LocalDate.of(2020, 03, 02), null);
    Schedule schedule2 = new Schedule(LocalDate.of(2020, 02, 12), LocalDate.of(2020, 03, 02), null);
    AdditionalInfo additionalInfo1 = new AdditionalInfo("Notatka1.A", "Notatka1.B", "Notatka1.C");
    AdditionalInfo additionalInfo2 = new AdditionalInfo("Notatka2.A", null, "Notatka2.C");
    Employee employee1 = new Employee("Jan", "Nowak");
    Employee employee2 = new Employee("Marian", "Kowalski");


    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    public void savePhonesToDB() {
        phoneRepository.saveAll(phones);
    }

    @Order(1)
    @Rollback(false)
    @Test
    public void shouldAddSchoolsAndPhonesToDB() {
        saveSchoolsToDB();
        savePhonesToDB();
    }

    @Order(2)
    @Rollback(false)
    @Test
    public void shouldAddDataToSchool() {
        School schoolFromDB = schoolRepository.findById(23063L).get();

        schoolFromDB.setPhones(new Phone("888-999-444", schoolFromDB.getId()));
        schoolFromDB.setStatus(status1);
        schoolFromDB.setSchedule(schedule1);
        schoolFromDB.setAdditionalInfo(additionalInfo2);

        schoolRepository.save(schoolFromDB);
    }
}
