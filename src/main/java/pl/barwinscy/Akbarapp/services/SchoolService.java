package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School getSchoolWithAllData(Long schoolId){
        School schoolWithAllInfo = schoolRepository.findSchoolWithAllInfo(schoolId);
        return schoolWithAllInfo;
    }
}
