package pl.barwinscy.Akbarapp.repositories;

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
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SchoolRepositoryTest {

    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AdditionalInfoRepository additionalInfoRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    Status status1 = new Status(true, true, 15);
    Status status2 = new Status(true, false, 0);
    Schedule schedule1 = new Schedule(LocalDate.of(2020, 02, 12), LocalDate.of(2020, 03, 02), null);
    Schedule schedule2 = new Schedule(LocalDate.of(2020, 02, 12), LocalDate.of(2020, 03, 02), null);
    AdditionalInfo additionalInfo1 = new AdditionalInfo("Notatka1.A", "Notatka1.B", "Notatka1.C");
    AdditionalInfo additionalInfo2 = new AdditionalInfo("Notatka2.A", null, "Notatka2.C");
    Employee employee1 = new Employee(28L, "Jan", "Nowak");
    Employee employee2 = new Employee(50L, "Marian", "Kowalski");


    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    public void savePhonesToDB() {
        phoneRepository.saveAll(phones);
    }

/*    @Test
    @Rollback(false)
    public void findByNameContains() {
        saveSchoolsToDB();

        List<School> foundSchool = schoolRepository.findByNameContains("6");
        assertThat(foundSchool.size()).isEqualTo(3);

    }*/

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
    public void shouldLinkPhoneWithSchool() {

        Optional<School> schoolFromDB = schoolRepository.findById(23063L);
        List<Phone> phones = phoneRepository.findBySchoolRSPO(schoolFromDB.get().getId());
        Phone phone = phones.get(0);

        schoolFromDB.get().setPhones(phone);
        phoneRepository.save(phone);

        Optional<School> schoolWithPhone = schoolRepository.findById(23063L);
        assertThat(schoolWithPhone.get().getPhones().size()).isEqualTo(1);
    }

    @Order(3)
    @Rollback(false)
    @Test
    public void shouldLinkStatusWithSchool() {
        Optional<School> schoolFromDB1 = schoolRepository.findById(34195L);
        Optional<School> schoolFromDB2 = schoolRepository.findById(26243L);

        schoolFromDB1.get().setStatus(status1);
        schoolFromDB2.get().setStatus(status2);

        statusRepository.save(status1);
        statusRepository.save(status2);

        Optional<School> schoolWithStatus1 = schoolRepository.findById(34195L);
        Optional<School> schoolWithStatus2 = schoolRepository.findById(26243L);

        assertThat(schoolWithStatus1.get().getStatus().getId()).isGreaterThan(0);
        assertThat(schoolWithStatus2.get().getStatus().getId()).isGreaterThan(0);
    }

    @Order(4)
    @Rollback(false)
    @Test
    public void shouldLinkScheduleWithSchool() {
        School schoolFromDb1 = schoolRepository.findById(41993L).get();
        School schoolFromDb2 = schoolRepository.findById(41195L).get();

        schoolFromDb1.setSchedule(schedule1);
        schoolFromDb2.setSchedule(schedule2);

        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);

        School schoolWithSchedule1 = schoolRepository.findById(41993L).get();
        School schoolWithSchedule2 = schoolRepository.findById(41195L).get();

        assertThat(schoolWithSchedule1.getSchedule().getId()).isGreaterThan(0);
        assertThat(schoolWithSchedule2.getSchedule().getId()).isGreaterThan(0);
    }

    @Order(5)
    @Rollback(false)
    @Test
    public void shouldLinkSchoolWithAdditionalInfo() {
        School schoolFromDb1 = schoolRepository.findById(41993L).get();
        School schoolFromDb2 = schoolRepository.findById(41195L).get();

        schoolFromDb1.setAdditionalInfo(additionalInfo1);
        schoolFromDb2.setAdditionalInfo(additionalInfo2);

        additionalInfoRepository.save(additionalInfo1);
        additionalInfoRepository.save(additionalInfo2);

        School schoolWithInfo1 = schoolRepository.findById(41993L).get();
        School schoolWithInfo2 = schoolRepository.findById(41195L).get();

        assertThat(schoolWithInfo1.getAdditionalInfo().getId()).isGreaterThan(0);
        assertThat(schoolWithInfo2.getAdditionalInfo().getId()).isGreaterThan(0);
    }

    @Order(6)
    @Rollback(false)
    @Test
    public void shouldLinkEmployeeWithSchool() {
        School schoolFromDb1 = schoolRepository.findById(41993L).get();
        School schoolFromDb2 = schoolRepository.findById(41195L).get();

//        schoolFromDb1.setEmployee(employee1);
        //schoolFromDb2.setEmployee(employee2);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //School schoolWithEmployee1 = schoolRepository.findById(41993L).get();
        //School schoolWithEmployee2 = schoolRepository.findById(41195L).get();

        //assertThat(schoolWithEmployee1.getEmployee().getId()).isGreaterThan(0);
        //assertThat(schoolWithEmployee2.getEmployee().getLastName()).isEqualTo("Kowalski");
    }

}