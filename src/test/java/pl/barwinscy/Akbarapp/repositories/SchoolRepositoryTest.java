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
        School schoolFromDB = schoolRepository.findById(23063L).get();
        List<Phone> phones = phoneRepository.findBySchoolRSPO(schoolFromDB.getId());
        Phone phone = phones.get(0);

        schoolFromDB.setPhones(phone);
        schoolRepository.save(schoolFromDB);
        School schoolWithPhone = schoolRepository.findById(23063L).get();

        assertThat(schoolWithPhone.getPhones().size()).isEqualTo(1);
    }

    @Order(3)
    @Rollback(false)
    @Test
    public void shouldLinkStatusWithSchool() {
        School schoolFromDB1 = schoolRepository.findById(34195L).get();
        School schoolFromDB2 = schoolRepository.findById(26243L).get();

        schoolFromDB1.setStatus(status1);
        schoolFromDB2.setStatus(status2);
        schoolRepository.save(schoolFromDB1);
        schoolRepository.save(schoolFromDB2);
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
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
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
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
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

        schoolFromDb1.setEmployee(employee1);
        schoolFromDb2.setEmployee(employee2);
        schoolRepository.save(schoolFromDb1);
        schoolRepository.save(schoolFromDb2);
        School schoolWithEmployee1 = schoolRepository.findById(41993L).get();
        School schoolWithEmployee2 = schoolRepository.findById(41195L).get();

        assertThat(schoolWithEmployee1.getEmployee().getId()).isGreaterThan(0);
        assertThat(schoolWithEmployee2.getEmployee().getLastName()).isEqualTo("Kowalski");
    }

    @Order(7)
    @Rollback(false)
    @Test
    public void shouldUpdateSchool(){
        School schoolFromDB = schoolRepository.findById(41993L).get();
        String newName = "LULASOWO 123";
        String newType = "Szkola kiuikow";

        schoolFromDB.setName(newName);
        schoolFromDB.setType(newType);
        schoolRepository.save(schoolFromDB);
        School schoolAfterUpdate = schoolRepository.findById(41993L).get();

        assertThat(schoolAfterUpdate.getName()).isEqualTo(newName);
        assertThat(schoolAfterUpdate.getType()).isEqualTo(newType);
    }

    @Order(8)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsAddress(){
        School schoolFromDB = schoolRepository.findById(23063L).get();
        String newStreet = "Królika 5/29";
        String newBorough = "Rabbitstan";

        schoolFromDB.getAddress().setStreet(newStreet);
        schoolFromDB.getAddress().setBorough(newBorough);
        schoolRepository.save(schoolFromDB);
        School schoolWithNewAddress = schoolRepository.findById(23063L).get();

        assertThat(schoolWithNewAddress.getAddress().getStreet()).isEqualTo(newStreet);
        assertThat(schoolWithNewAddress.getAddress().getBorough()).isEqualTo(newBorough);
    }

    @Order(8)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsAdditionalInfo(){
        School schoolFromDB = schoolRepository.findById(41993L).get();
        String newInfo = "to jest zmieniona notatka";

        schoolFromDB.getAdditionalInfo().setNote1(newInfo);
        schoolRepository.save(schoolFromDB);
        School schoolWithNewInfo = schoolRepository.findById(41993L).get();

        assertThat(schoolWithNewInfo.getAdditionalInfo().getNote1()).isEqualTo(newInfo);
    }
    //TODO
    @Order(9)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsPhoneAndAddSecondPhone(){
        School schoolFromDB = schoolRepository.findById(41993L).get();


        schoolRepository.save(schoolFromDB);
        School schoolWithNewInfo = schoolRepository.findById(41993L).get();

        assertThat(schoolWithNewInfo.getPhones().size()).isEqualTo();
    }
    //TODO
    @Order(10)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsSchedule(){
        School schoolFromDB = schoolRepository.findById(41993L).get();


        schoolRepository.save(schoolFromDB);
        School schoolWithNewInfo = schoolRepository.findById(41993L).get();

        assertThat(schoolWithNewInfo.getPhones().size()).isEqualTo();
    }
//TODO
    @Order(11)
    @Rollback(false)
    @Test
    public void shouldUpdateSchoolsStatus(){
        School schoolFromDB = schoolRepository.findById(41993L).get();


        schoolRepository.save(schoolFromDB);
        School schoolWithNewInfo = schoolRepository.findById(41993L).get();

        assertThat(schoolWithNewInfo.getPhones().size()).isEqualTo();
    }

}