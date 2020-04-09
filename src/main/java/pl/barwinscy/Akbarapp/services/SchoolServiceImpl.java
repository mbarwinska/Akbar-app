package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.mappers.SchoolMapper;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School getSchoolWithAllData(Long schoolId){
        School schoolToView = schoolRepository.findSchoolWithAllInfo(schoolId);
        return SchoolMapper.mapSchoolToView(schoolToView);
    }

    @Transactional
    @Override
    public School save(SchoolDto schoolDto) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        School save = schoolRepository.save(school);
        Phone phone = new Phone(schoolDto.getPhoneNumber());
        //phone.setSchool(save);
        save.setPhones(phone);
        schoolRepository.save(save);

        return save;

    }
}
