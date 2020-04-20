package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.mappers.EmployeeMapper;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.SchoolRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private SchoolRepository schoolRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(SchoolRepository schoolRepository, EmployeeRepository employeeRepository) {
        this.schoolRepository = schoolRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
}
