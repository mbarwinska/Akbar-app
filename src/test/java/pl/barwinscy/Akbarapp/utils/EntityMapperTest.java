package pl.barwinscy.Akbarapp.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class EntityMapperTest {

    private CsvReader csvReader;
    private List<SchoolDataCsv> schoolsFromCsv;

    @BeforeEach
    public void setUp(){
        csvReader = new CsvReader();
        schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    }

    @Test
    void mapToSchoolEntity() {

        Address address = new Address("ŁÓDZKIE",
                "Łódź",
                "Łódź - gmina miejska",
                "Łódź","ul. Drewnowska 151  ",
                "91-008 ");

        School expectedSchool = School.builder()
                .type("Szkoła podstawowa")
                .name("SZKOŁA PODSTAWOWA SPECJALNA NR 211")
                .address(address)
                .email("mow3lodz@gmail.com")
                .website("http://www.mow3.szkoly.lodz.pl")
                .status("publiczna")
                .build();

//        Phone phone = new Phone("426122935",expectedSchool);
//        expectedSchool.setPhones(phone);
//       expectedSchool.setPhones(new Phone("426122935",expectedSchool));

        List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());

        List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
       School actualSchool = schools.get(0);
//      actualSchool.setPhones(phones.get(0));

        System.out.println("Expected school: " + expectedSchool);
        System.out.println("Actual school: " + actualSchool);


        assertThat(actualSchool).isEqualTo(expectedSchool);

    }

    @Test
    void mapToPhoneEntity() {
    }
}