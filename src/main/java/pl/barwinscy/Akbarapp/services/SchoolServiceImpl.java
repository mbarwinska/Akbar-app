package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;
import pl.barwinscy.Akbarapp.mappers.SchoolMapper;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.PhoneRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;
    private EmployeeRepository employeeRepository;

    private PhoneRepository phoneRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository, PhoneRepository phoneRepository, EmployeeRepository employeeRepository) {
        this.schoolRepository = schoolRepository;
        this.employeeRepository = employeeRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public SchoolDto getSchoolWithAllData(Long schoolId){
        School schoolToView = schoolRepository.findSchoolWithAllInfo(schoolId);
        return SchoolMapper.mapSchoolEntityToDto(SchoolMapper.mapSchoolToView(schoolToView));
    }


    @Transactional
    @Override
    public School save(SchoolDto schoolDto) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        if (!schoolDto.getEmployee().isEmpty()){
            Employee employee = employeeRepository.findById(Long.valueOf(schoolDto.getEmployee())).get();
            school.setEmployee(employee);
        }
        School save = schoolRepository.save(school);
        Phone phone = new Phone(schoolDto.getPhoneNumber());
        save.setPhones(phone);
        schoolRepository.save(save);
        return save;

    }
    @Transactional
    @Override
    public School update(SchoolDto schoolDto, PhoneDTO phoneDTO) {
        School school = SchoolMapper.mapDtoToEntity(schoolDto);
        if (!schoolDto.getEmployee().isEmpty()){
            Employee employee = employeeRepository.findById(Long.valueOf(schoolDto.getEmployee())).get();
            school.setEmployee(employee);
        }


        if (!phoneDTO.getNumber().isEmpty()){
            Phone phone = new Phone(phoneDTO.getNumber());
            phone.setNote(phoneDTO.getNote());
            school.setPhones(phone);
        }
        return schoolRepository.save(school);
    }

    @Transactional
    public void deletePhone(Long phoneId){
        phoneRepository.deleteById(phoneId);
    }
}
