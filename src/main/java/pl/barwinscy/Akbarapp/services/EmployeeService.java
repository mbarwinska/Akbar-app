package pl.barwinscy.Akbarapp.services;

import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllPhotographers();
    List<Salesman> getAllSalesmen();

    Employee addEmployee(EmployeeDto employeeDto);
    Salesman addSalesman(SalesmanDto salesmanDto);
}
