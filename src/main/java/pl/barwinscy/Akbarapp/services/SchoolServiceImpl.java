package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
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
        return schoolRepository.findSchoolWithAllInfo(schoolId);
    }

    @Transactional
    @Override
    public void save(SchoolDto schoolDto) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        schoolRepository.saveAndFlush(school);
    }
}
