package pl.barwinscy.Akbarapp.utils;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SearchQueryCreatorTest {
/*
    private static final String FILE_PATH_TEST = "src/test/resources/testSchools.csv";
    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv(FILE_PATH_TEST);
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).filter(Objects::nonNull).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).filter(Objects::nonNull).collect(Collectors.toList());
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    public void savePhonesToDB() {
        phoneRepository.saveAll(phones);
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


    @Order(1)
    @Rollback(false)
    @Test
    public void shouldAddSchoolsAndPhonesToDB() {
        saveSchoolsToDB();
        savePhonesToDB();
        linkPhonesToSchools();
    }

    @Order(5)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByCountyBoroughAndStreet(){
        SearchQueryCreator searchByCountyBoroughAndStreet = new SearchQueryCreator();
        searchByCountyBoroughAndStreet.setVoivodeship("");
        searchByCountyBoroughAndStreet.setBorough("Łódź-Górna");
        searchByCountyBoroughAndStreet.setCounty("Łódź");
        searchByCountyBoroughAndStreet.setCity("");
        searchByCountyBoroughAndStreet.setStreet("Politechniki");
        searchByCountyBoroughAndStreet.setType("");
        searchByCountyBoroughAndStreet.setName("");
        searchByCountyBoroughAndStreet.setPhone("");


        List<School> foundSchool = schoolRepository.searchByQuery(searchByCountyBoroughAndStreet.createSearchQuery());
        assertThat(foundSchool.get(0).getName()).isEqualTo("ZESPÓŁ SZKÓŁ POLITECHNICZNYCH IM. KOMISJI EDUKACJI NARODOWEJ");
    }

    @Order(3)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByName(){
        SearchQueryCreator searchByName = new SearchQueryCreator();

        searchByName.setVoivodeship("");
        searchByName.setBorough("");
        searchByName.setCounty("");
        searchByName.setCounty("");
        searchByName.setCity("");
        searchByName.setStreet("");
        searchByName.setType("");
        searchByName.setName("SZKOŁA PODSTAWOWA NR 1");
        searchByName.setPhone("");

        List<School> foundSchool = schoolRepository.searchByQuery(searchByName.createSearchQuery());

        assertThat(foundSchool.get(0).getName()).isEqualTo("SZKOŁA PODSTAWOWA NR 1 IM. ADAMA MICKIEWICZA");
    }

    @Order(4)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByPhone(){
        SearchQueryCreator searchByPhone = new SearchQueryCreator();
        searchByPhone.setVoivodeship("Łódzkie");
        searchByPhone.setBorough("");
        searchByPhone.setCounty("");
        searchByPhone.setCounty("");
        searchByPhone.setCity("");
        searchByPhone.setStreet("");
        searchByPhone.setType("");
        searchByPhone.setName("");
        searchByPhone.setPhone("42 643-06-64");

        System.out.println(searchByPhone.createSearchQuery());

        List<School> foundSchool = schoolRepository.searchByQuery(searchByPhone.createSearchQuery());

        assertThat(foundSchool.get(0).getName()).isEqualTo("PRZEDSZKOLE MIEJSKIE NR 156");
    }*/

}