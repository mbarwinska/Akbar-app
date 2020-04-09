package pl.barwinscy.Akbarapp.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;

import java.util.List;
import java.util.stream.Collectors;


class EntityMapperTest {

    private CsvReader csvReader;
    private List<SchoolDataCsv> schoolsFromCsv;

    @BeforeEach
    public void setUp() {
        csvReader = new CsvReader();
        schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    }

    @Test
    void mapToPhoneEntity() {
        Address address = new Address(Voivodeship.ŁÓDZKIE,
                "Łódź",
                "Łódź - gmina miejska",
                "Łódź", "ul. Drewnowska 151",
                "91-008");

        School expectedSchool = School.builder()
                .type(SchoolType.SZKOŁA_PODSTAWOWA.name())
                .name("SZKOŁA PODSTAWOWA SPECJALNA NR 211")
                .address(address)
                .email("mow3lodz@gmail.com")
                .website("http://www.mow3.szkoly.lodz.pl")
                .publicStatus("publiczna")
                .build();

        Phone phone = new Phone("426122935");

        //phone.setSchool(expectedSchool);
        System.out.println("Expected school: " + expectedSchool);
        phone.setSchool(expectedSchool);


        List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());

        List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
        School actualSchool = schools.get(0);



        System.out.println("Expected telefon: " + expectedSchool.getPhones());
        System.out.println("Actual school: " + actualSchool);
        System.out.println("Expected school: " + expectedSchool);

        boolean isVoivodeshipEqual = expectedSchool.getAddress().getVoivodeship().equals(actualSchool.getAddress().getVoivodeship());

        System.out.println(isVoivodeshipEqual);

    }
}