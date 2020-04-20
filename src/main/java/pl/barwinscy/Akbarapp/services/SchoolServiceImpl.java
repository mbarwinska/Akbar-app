package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.entities.Status;
import pl.barwinscy.Akbarapp.mappers.SchoolMapper;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;
import pl.barwinscy.Akbarapp.repositories.StatusRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;
    private EmployeeRepository employeeRepository;
    private StatusRepository statusRepository;
    private PhoneRepository phoneRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, StatusRepository statusRepository, PhoneRepository phoneRepository, EmployeeRepository employeeRepository) {
        this.schoolRepository = schoolRepository;
        this.employeeRepository = employeeRepository;
        this.statusRepository = statusRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public SchoolDto getSchoolWithAllData(Long schoolId){
        School schoolToView = schoolRepository.findSchoolWithAllInfo(schoolId);
        //return SchoolMapper.mapSchoolToView(schoolToView);
        return SchoolMapper.mapSchoolEntityToDto(SchoolMapper.mapSchoolToView(schoolToView));
    }


    @Transactional
    @Override
    public School save(SchoolDto schoolDto) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        Employee employee = employeeRepository.findById(Long.valueOf(schoolDto.getEmployee())).get();
        school.setEmployee(employee);
        School save = schoolRepository.save(school);
        Phone phone = new Phone(schoolDto.getPhoneNumber());
        //phone.setSchool(save);
        save.setPhones(phone);
        schoolRepository.save(save);

        return save;

    }
    @Transactional
    @Override
    public School update(SchoolDto schoolDto, PhoneDTO phoneDTO) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        Employee employee = employeeRepository.findById(Long.valueOf(schoolDto.getEmployee())).get();
        school.setEmployee(employee);

        if (!phoneDTO.getNumber().isEmpty()){
            Phone phone = new Phone(phoneDTO.getNumber());
            phone.setNote(phoneDTO.getNote());
            school.setPhones(phone);
        }
        School save = schoolRepository.save(school);

        return save;
    }

    @Transactional
    @Override
    public void phoneDelete(Long phoneId){
        phoneRepository.deleteById(phoneId);
    }
}
