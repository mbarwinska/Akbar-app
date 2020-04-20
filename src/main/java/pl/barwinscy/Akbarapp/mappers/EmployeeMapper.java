package pl.barwinscy.Akbarapp.mappers;

import org.modelmapper.ModelMapper;
import pl.barwinscy.Akbarapp.dto.EmployeeDto;
import pl.barwinscy.Akbarapp.entities.Employee;

public class EmployeeMapper {

    private EmployeeMapper(){

    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        ModelMapper mapper = new ModelMapper();
        return  mapper.map(employeeDto, Employee.class);
    }
}
