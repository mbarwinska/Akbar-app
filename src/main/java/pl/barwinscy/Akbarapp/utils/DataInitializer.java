package pl.barwinscy.Akbarapp.utils;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private SchoolRepository schoolRepository;
    private PhoneRepository phoneRepository;

    private CsvReader csvReader = new CsvReader();
    private List<SchoolDataCsv> schoolsFromCsv = csvReader.getAllSchoolDataFromCsv();
    private List<School> schools = schoolsFromCsv.stream().map(EntityMapper::mapToSchoolEntity).collect(Collectors.toList());
    private List<Phone> phones = schoolsFromCsv.stream().map(EntityMapper::mapToPhoneEntity).collect(Collectors.toList());

    public DataInitializer(SchoolRepository schoolRepository, PhoneRepository phoneRepository) {
        this.schoolRepository = schoolRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        schoolRepository.saveAll(schools);
        phoneRepository.saveAll(phones);
    }

}
