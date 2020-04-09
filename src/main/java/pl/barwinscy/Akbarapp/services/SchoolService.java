package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

public interface SchoolService {

    School getSchoolWithAllData(Long schoolId);

    School save(SchoolDto schoolDto);
}
