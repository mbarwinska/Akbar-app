package pl.barwinscy.Akbarapp.services;

import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.School;

public interface SchoolService {

    SchoolDto getSchoolWithAllData(Long schoolId);

    School save(SchoolDto schoolDto);

    School update(SchoolDto schoolDto, PhoneDTO phoneDTO);

    void deletePhone(Long id);
}
