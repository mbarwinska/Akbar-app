package pl.barwinscy.Akbarapp.repositories;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.entities.*;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SchoolRepositoryTest {
/*    private static final String FILE_PATH_TEST = "src/test/resources/testSchools.csv";
    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv(FILE_PATH_TEST);
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).filter(Objects::nonNull).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).filter(Objects::nonNull).collect(Collectors.toList());
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
    Employee employee1 = new Employee( "Jan", "Nowak");
    Employee employee2 = new Employee( "Marian", "Kowalski");


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

    *//*@Order(2)
    @Rollback(false)
    @Test
    public void shouldLinkPhoneWithSchool() {
        School schoolFromDB = schoolRepository.findById(2L).get();
        List<Phone> phones = phoneRepository.findBySchoolRSPO(schoolFromDB.getRspo());
        Phone phone = phones.get(0);

        schoolFromDB.setPhones(phone);
        schoolRepository.save(schoolFromDB);
        School schoolWithPhone = schoolRepository.findById(2L).get();

        assertThat(schoolWithPhone.getPhones().size()).isEqualTo(1);
    }*//*

    @Order(3)
    @Rollback(false)
    @Test
    public void shouldLinkStatusWithSchool() {
        School schoolFromDB1 = schoolRepository.findById(1L).get();
        School schoolFromDB2 = schoolRepository.findById(2L).get();

        schoolFromDB1.setStatus(status1);
        schoolFromDB2.setStatus(status2);
        schoolRepository.save(schoolFromDB1);
        schoolRepository.save(schoolFromDB2);
        Optional<School> schoolWithStatus1 = schoolRepository.findById(1L);
        Optional<School> schoolWithStatus2 = schoolRepository.findById(2L);

        assertThat(schoolWithStatus1.get().getStatus().getId()).isGreaterThan(0);
        assertThat(schoolWithStatus2.get().getStatus().getId()).isGreaterThan(0);
    }

    @Order(4)
    @Rollback(false)
    @Test
    public void shouldLinkScheduleWithSchool() {
        School schoolFromDb1 = schoolRepository.findById(3L).get();
        School schoolFromDb2 = schoolRepository.findById(4L).get();

        schoolFromDb1.setSchedule(schedule1);
        schoolFromDb2.setSchedule(schedule2);
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
        School schoolWithSchedule1 = schoolRepository.findById(3L).get();
        School schoolWithSchedule2 = schoolRepository.findById(4L).get();

        assertThat(schoolWithSchedule1.getSchedule().getId()).isGreaterThan(0);
        assertThat(schoolWithSchedule2.getSchedule().getId()).isGreaterThan(0);
    }

    @Order(5)
    @Rollback(false)
    @Test
    public void shouldLinkSchoolWithAdditionalInfo() {
        School schoolFromDb1 = schoolRepository.findById(3L).get();
        School schoolFromDb2 = schoolRepository.findById(4L).get();

        schoolFromDb1.setAdditionalInfo(additionalInfo1);
        schoolFromDb2.setAdditionalInfo(additionalInfo2);
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
        School schoolWithInfo1 = schoolRepository.findById(3L).get();
        School schoolWithInfo2 = schoolRepository.findById(4L).get();

        assertThat(schoolWithInfo1.getAdditionalInfo().getId()).isGreaterThan(0);
        assertThat(schoolWithInfo2.getAdditionalInfo().getId()).isGreaterThan(0);
    }

    @Order(6)
    @Rollback(false)
    @Test
    public void shouldLinkEmployeeWithSchool() {
        School schoolFromDb1 = schoolRepository.findById(3L).get();
        School schoolFromDb2 = schoolRepository.findById(4L).get();

        schoolFromDb1.setEmployee(employee1);
        schoolFromDb2.setEmployee(employee2);
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
        School schoolWithEmployee1 = schoolRepository.findById(3L).get();
        School schoolWithEmployee2 = schoolRepository.findById(4L).get();

        assertThat(schoolWithEmployee1.getEmployee().getId()).isGreaterThan(0);
        assertThat(schoolWithEmployee2.getEmployee().getLastName()).isEqualTo("Kowalski");
    }

    @Order(7)
    @Rollback(false)
    @Test
    public void shouldUpdateSchool() {
        School schoolFromDB = schoolRepository.findById(3L).get();
        String newName = "LULASOWO 123";
        String newType = SchoolType.OTHER.name();

        schoolFromDB.setName(newName);
        schoolFromDB.setType(newType);
        schoolRepository.save(schoolFromDB);
        School schoolAfterUpdate = schoolRepository.findById(3L).get();

        assertThat(schoolAfterUpdate.getName()).isEqualTo(newName);
        assertThat(schoolAfterUpdate.getType()).isEqualTo(newType);
    }

    @Order(8)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsAddress() {
        School schoolFromDB = schoolRepository.findById(5L).get();
        String newStreet = "Królika 5/29";
        String newBorough = "Rabbitstan";

        schoolFromDB.getAddress().setStreet(newStreet);
        schoolFromDB.getAddress().setBorough(newBorough);
        schoolRepository.save(schoolFromDB);
        School schoolWithNewAddress = schoolRepository.findById(5L).get();

        assertThat(schoolWithNewAddress.getAddress().getStreet()).isEqualTo(newStreet);
        assertThat(schoolWithNewAddress.getAddress().getBorough()).isEqualTo(newBorough);
    }

    @Order(8)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsAdditionalInfo() {
        School schoolFromDB = schoolRepository.findById(3L).get();
        String newInfo = "to jest zmieniona notatka";

        schoolFromDB.getAdditionalInfo().setNote1(newInfo);
        schoolRepository.save(schoolFromDB);
        School schoolWithNewInfo = schoolRepository.findById(3L).get();

        assertThat(schoolWithNewInfo.getAdditionalInfo().getNote1()).isEqualTo(newInfo);
    }

    @Order(10)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsSchedule() {
        School schoolFromDB = schoolRepository.findById(3L).get();
        schoolFromDB.getSchedule().setContactDate(LocalDate.now());

        schoolRepository.save(schoolFromDB);
        School schoolWithNewSchedule = schoolRepository.findById(3L).get();

        assertThat(schoolWithNewSchedule.getSchedule().getContactDate().isEqual(LocalDate.now()));
    }

    @Order(11)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsStatus() {
        School schoolFromDB = schoolRepository.findById(2L).get();
        schoolFromDB.getStatus().setContracted(true);

        schoolRepository.save(schoolFromDB);
        School schoolWithNewStatus = schoolRepository.findById(2L).get();

        assertThat(schoolWithNewStatus.getStatus().isContracted()).isEqualTo(true);
    }

    @Order(12)
    @Rollback(false)
    @Test
    public void shouldDeleteSchoolAndAllEntitiesRelatedToItExceptEmployee() {
        School schoolFromDB = schoolRepository.findById(3L).get();
        Phone phone = new Phone("66655444");
        Status status = new Status(true, false, 0);
        schoolFromDB.setPhones(phone);
        schoolFromDB.setStatus(status);

        schoolRepository.save(schoolFromDB);
        School schoolToDelete = schoolRepository.findById(3L).get();
        schoolRepository.delete(schoolToDelete);

        Optional<School> afterDelete = schoolRepository.findById(3L);
        assertThat(afterDelete).isEmpty();
    }

    @Order(13)
    @Rollback(false)
    @Test
    public void shouldAddEmployeeToDB() {
        Employee employeeToAdd = new Employee("Bob", "Budowniczy");
        employeeRepository.save(employeeToAdd);

        Employee addedEmployee = employeeRepository.findById(1L).get();
        assertThat(addedEmployee.getId()).isGreaterThan(0);
    }

    @Order(14)
    @Rollback(false)
    @Test
    public void shouldAddSecondSchoolToEmployee() {
        Employee addedEmployee = employeeRepository.findById(1L).get();
        School schoolFromDB1 = schoolRepository.findById(5L).get();
        School schoolFromDB2 = schoolRepository.findById(6L).get();

        schoolFromDB1.setEmployee(addedEmployee);
        schoolFromDB2.setEmployee(addedEmployee);
        schoolRepository.save(schoolFromDB1);
        schoolRepository.save(schoolFromDB2);

        School schoolAfterUpdate1 = schoolRepository.findById(5L).get();
        School schoolAfterUpdate2 = schoolRepository.findById(6L).get();

        assertThat(schoolAfterUpdate1.getEmployee().getId()).isEqualTo(1L);
        assertThat(schoolAfterUpdate2.getEmployee().getId()).isEqualTo(1L);
        assertThat(addedEmployee.getSchools().size()).isEqualTo(2);
    }*/
}