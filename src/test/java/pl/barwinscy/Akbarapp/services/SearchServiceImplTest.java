package pl.barwinscy.Akbarapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.Schedule;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SalesmanRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SearchServiceImplTest {

    private static final String FILE_PATH_TEST = "src/test/resources/testSchools.csv";
    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv(FILE_PATH_TEST);
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).filter(Objects::nonNull).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).filter(Objects::nonNull).collect(Collectors.toList());

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    SearchService searchService;

    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    public void savePhonesToDB() {
        phoneRepository.saveAll(phones);
    }

    @BeforeEach
    public void setUp() throws Exception {
        searchService = new SearchServiceImpl(schoolRepository);
        saveSchoolsToDB();
        savePhonesToDB();
        linkPhonesToSchools();
    }
    
    private void linkPhonesToSchools() {
        for (School school : schools) {
            for (Phone phone : phones) {
                if (phone.getSchoolRSPO().equals(school.getRspo())) {
                    school.setPhones(phone);
                }
            }
        }
    }

    @Test
    void shouldReturnSchoolsByCityAndSchoolType() {
        String cityAndPreschool = "SELECT DISTINCT school FROM School as school WHERE school.address.voivodeship = 'Łódzkie' AND school.address.city like 'Łódź%' AND school.type = 'Przedszkole'";
        List<School> searchedSchools = searchService.getSearchedSchools(cityAndPreschool);
        assertThat(searchedSchools.size()).isGreaterThan(1);

    }

    /*@Test
    void shouldReturnSchoolsByPhoneNumber() {
        String phoneNumber = "SELECT DISTINCT school FROM School as school JOIN Phone as phone on school.id = phone.school.id WHERE school.id = (SELECT phone.school.id FROM Phone as phone WHERE phone.phoneNumber = '42 643-06-64') AND school.address.voivodeship = 'Łódzkie'";
        List<School> searchedSchools = searchService.getSearchedSchools(phoneNumber);
        assertThat(searchedSchools.size()).isEqualTo(1);
    }*/

    @Test
    void shouldReturnSchoolsByNameAndName() {
        String addressAndName = "SELECT DISTINCT school FROM School as school WHERE school.address.voivodeship = 'Łódzkie' AND school.address.street like '%Sterlinga 24%' AND school.name like '%adama mickiewicza%'";
        List<School> searchedSchools = searchService.getSearchedSchools(addressAndName);
        assertThat(searchedSchools.size()).isGreaterThan(0);
    }


    @Test
    void shouldReturnSchoolWithoutDateInputAndContactDateType() {
        LocalDate today = LocalDate.now();
        Schedule schedule1 = new Schedule(today, null, null);
        Schedule schedule2 = new Schedule(today, null, null);
        Schedule schedule3 = new Schedule(today.plusDays(1), null, null);
        Schedule schedule4 = new Schedule(today.plusDays(2), null, null);
        schools.get(0).setSchedule(schedule1);
        schools.get(1).setSchedule(schedule2);
        schools.get(2).setSchedule(schedule3);
        schools.get(3).setSchedule(schedule4);
        schoolRepository.saveAll(schools);
        schoolRepository.flush();
        String query = "SELECT school FROM School as school JOIN Schedule as schedule on school.id = schedule.school.id WHERE schedule.contactDate BETWEEN '" + today.toString() + "' AND '" + today.plusDays(30).toString() + "'";
        Map<LocalDate, List<School>> schoolByContactDate = searchService.schoolMapByDate(query, "contactDate");
        assertThat(schoolByContactDate.size()).isEqualTo(3);
    }
}