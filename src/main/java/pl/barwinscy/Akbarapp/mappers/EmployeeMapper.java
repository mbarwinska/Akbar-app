package pl.barwinscy.Akbarapp.mappers;

import org.modelmapper.ModelMapper;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.dto.SalesmanDto;
import pl.barwinscy.Akbarapp.entities.Employee;
import pl.barwinscy.Akbarapp.entities.Salesman;

public class EmployeeMapper {

    private EmployeeMapper() {

    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(employeeDto, Employee.class);
    }

    public static Salesman mapToSalesman(SalesmanDto salesmanDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(salesmanDto, Salesman.class);
    }
}
