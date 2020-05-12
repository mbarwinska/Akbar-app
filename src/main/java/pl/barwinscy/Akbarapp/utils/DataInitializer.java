package pl.barwinscy.Akbarapp.utils;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.entities.*;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private SchoolRepository schoolRepository;
    private static final String FILE_PATH = "src/main/resources/toHeroku.csv";
    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv(FILE_PATH);
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());

    Status status1 = new Status(true, true, 15);
    Status status2 = new Status(true, true, 0);
    Status status3 = new Status(true, false, 0);
    Status status4 = new Status(true, false, 20);
    Schedule schedule1 = new Schedule(LocalDate.of(2020, 4, 25), LocalDate.of(2020, 3, 2), null);
    AdditionalInfo additionalInfo2 = new AdditionalInfo("Notatka2.A", "", "Notatka2.C");
    Employee employee = new Employee("Bill", "Clinton");
    Salesman salesman = new Salesman("Chandler", "Bing");


    public DataInitializer(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        linkPhonesToSchools();
        List<School> schoolsToSave = changeCountiesToLowerCase(this.schools);
        schoolRepository.saveAll(schoolsToSave);
        //addRelationalDataToSchools();
    }

    private void linkPhonesToSchools() {
        for (School school : schools) {
            for (int i = 0; i < phones.size(); i++){
                Phone phone = phones.get(i);
                if (phones.get(i) != null && phone.getSchoolRSPO().equals(school.getRspo())){
                    school.setPhones(phone);
                    phones.remove(i);
                    break;
                }
            }
        }
    }

    private List<School> changeCountiesToLowerCase(List<School> schools) {
        List<School> schoolsWithCountiesLowerCase = new ArrayList<>();
        for (School school : schools) {
            school.getAddress().setCounty(school.getAddress().getCounty().toLowerCase());
            schoolsWithCountiesLowerCase.add(school);
        }
        return schoolsWithCountiesLowerCase;
    }

    private void addRelationalDataToSchools() {
        School schoolFromDB = schoolRepository.findByRspo(23063L);
        School schoolFromDB2 = schoolRepository.findById(1L).get();
        School schoolFromDB3 = schoolRepository.findById(2L).get();
        School schoolFromDB4 = schoolRepository.findById(3L).get();

        Phone phone = new Phone("888-999-444", schoolFromDB.getRspo());
        phone.setNote("To jest notatka ");
        schoolFromDB.setPhones(phone);
        schoolFromDB.setStatus(status1);
        schoolFromDB.setSchedule(schedule1);
        schoolFromDB.setAdditionalInfo(additionalInfo2);
        schoolFromDB.setEmployee(employee);

        schoolFromDB2.setStatus(status2);
        schoolFromDB3.setStatus(status3);
        schoolFromDB4.setStatus(status4);
        schoolFromDB4.setSalesman(salesman);

        schoolRepository.save(schoolFromDB);
    }
}
