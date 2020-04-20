package pl.barwinscy.Akbarapp.services;

import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.entities.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee addEmployee(EmployeeDto employeeDto);
}
