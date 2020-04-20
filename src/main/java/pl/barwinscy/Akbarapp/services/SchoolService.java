package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

public interface SchoolService {

    SchoolDto getSchoolWithAllData(Long schoolId);

    School save(SchoolDto schoolDto);

    School update(SchoolDto schoolDto, PhoneDTO phoneDTO);

    void phoneDelete(Long id);
}
