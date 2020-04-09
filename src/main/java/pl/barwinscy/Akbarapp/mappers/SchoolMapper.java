package pl.barwinscy.Akbarapp.mappers;

import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.*;

import java.time.LocalDate;

public class SchoolMapper {
    private SchoolMapper() {

    }

    public static School mapDtoToEntity(SchoolDto schoolDto) {
        Address address = new Address();
        if (schoolDto.getVoivodeship().equals("ŁÓDZKIE")) {
            address.setVoivodeship(Voivodeship.ŁÓDZKIE);
        } else if (schoolDto.getVoivodeship().equals("MAZOWIECKIE")) {
            address.setVoivodeship(Voivodeship.MAZOWIECKIE);
        } else if (schoolDto.getVoivodeship().equals("ŚWIĘTOKRZYSKIE")) {
            address.setVoivodeship(Voivodeship.ŚWIĘTOKRZYSKIE);
        } else {
            address.setVoivodeship(Voivodeship.OTHER);
        }
        address.setCounty(schoolDto.getCounty());
        address.setBorough(schoolDto.getBorough());
        address.setCity(schoolDto.getCity());
        address.setStreet(schoolDto.getStreet());
        address.setZipCode(schoolDto.getZipCode());

        Status status = new Status(schoolDto.isOurs(), schoolDto.isContracted(), schoolDto.getCalendarsLeftNumber());
        Schedule schedule = new Schedule(LocalDate.parse(schoolDto.getContactDate()),
                LocalDate.parse(schoolDto.getPhotographingDate()), LocalDate.parse(schoolDto.getPayOffDate()));


//        String[] employeeData = schoolDto.getEmployee().split(" ");
//        Employee employee = new Employee(1L, employeeData[0], employeeData[1]);
        AdditionalInfo additionalInfo = new AdditionalInfo(schoolDto.getNote1(), schoolDto.getNote2(), schoolDto.getNote3());
        School school = School.builder()
                .type(schoolDto.getType())
                .name(schoolDto.getName())
                .address(address)
                .email(schoolDto.getEmail())
                .website(schoolDto.getWebsite())
                .publicStatus(schoolDto.getPublicStatus())
                .build();

        school.setStatus(status);
        school.setSchedule(schedule);
//        school.setEmployee(employee);
        school.setAdditionalInfo(additionalInfo);

       //zapisanie do bazy
       //odczyt id z bazy

        //Phone phone = new Phone(schoolDto.getPhoneNumber(), schoolDto.getId());
        //school.setPhones(phone);
        return school;
    }
}
