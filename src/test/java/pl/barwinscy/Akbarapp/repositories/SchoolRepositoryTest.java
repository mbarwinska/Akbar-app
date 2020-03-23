package pl.barwinscy.Akbarapp.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.utils.CsvReader;
import pl.barwinscy.Akbarapp.utils.EntityMapper;
import pl.barwinscy.Akbarapp.utils.SchoolDataCsv;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class SchoolRepositoryTest {

    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    @Autowired
    private SchoolRepository schoolRepository;


    public void saveSchoolsToDB() {
        schoolRepository.saveAll(schools);
    }

    @Test
    @Rollback(false)
    public void findByNameContains() {
        saveSchoolsToDB();

        List<School> foundSchool = schoolRepository.findByNameContains("6");
        assertThat(foundSchool.size()).isEqualTo(3);

    }
}