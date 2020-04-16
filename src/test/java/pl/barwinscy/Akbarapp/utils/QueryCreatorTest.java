package pl.barwinscy.Akbarapp.utils;

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
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class QueryCreatorTest {

    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());
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

    @Order(1)
    @Rollback(false)
    @Test
    public void shouldAddSchoolsAndPhonesToDB() {
        saveSchoolsToDB();
        savePhonesToDB();
    }

    @Order(5)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByCountyBoroughAndStreet(){
        QueryCreator searchByCountyBoroughAndStreet = new QueryCreator("", "Łódź", "Łódź-Górna","", "Politechniki","","","" );

        System.out.println(searchByCountyBoroughAndStreet.createSearchQuery());
        List<School> foundSchool = schoolRepository.searchByQuery(searchByCountyBoroughAndStreet.createSearchQuery());
        assertThat(foundSchool.get(0).getName()).isEqualTo("ZESPÓŁ SZKÓŁ POLITECHNICZNYCH IM. KOMISJI EDUKACJI NARODOWEJ");
    }

    @Order(3)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByName(){
        QueryCreator searchByName = new QueryCreator("", "", "","", "","","SZKOŁA PODSTAWOWA NR 1","" );

        System.out.println(searchByName.createSearchQuery());

        List<School> foundSchool = schoolRepository.searchByQuery(searchByName.createSearchQuery());
        System.out.println(foundSchool.get(0).getName());
        assertThat(foundSchool.get(0).getName()).isEqualTo("SZKOŁA PODSTAWOWA NR 1 IM. ADAMA MICKIEWICZA");
    }

    @Order(4)
    @Rollback(false)
    @Test
    public void shouldFindSchoolByPhone(){
        QueryCreator searchByPhone = new QueryCreator("", "", "","", "","","","426430664" );

        System.out.println(searchByPhone.createSearchQuery());

        List<School> foundSchool = schoolRepository.searchByQuery(searchByPhone.createSearchQuery());
        System.out.println(foundSchool.get(0).getName());
        assertThat(foundSchool.get(0).getName()).isEqualTo("PRZEDSZKOLE MIEJSKIE NR 156");
    }

}