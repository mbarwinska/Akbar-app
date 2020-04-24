package pl.barwinscy.Akbarapp.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;
import pl.barwinscy.Akbarapp.mappers.EmployeeMapper;
import pl.barwinscy.Akbarapp.repositories.EmployeeRepository;
import pl.barwinscy.Akbarapp.repositories.SalesmanRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;
    private SalesmanRepository salesmanRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SalesmanRepository salesmanRepository) {
        this.employeeRepository = employeeRepository;
        this.salesmanRepository = salesmanRepository;
    }

    @Override
    public List<Employee> getAllPhotographers() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Salesman> getAllSalesmen() {
        return salesmanRepository.findAll();
    }

    @Override
    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        return employeeRepository.save(employee);
    }

    @Override
    public Salesman addSalesman(SalesmanDto salesmanDto) {
        Salesman salesman = EmployeeMapper.mapToSalesman(salesmanDto);
        return salesmanRepository.save(salesman);
    }
}
