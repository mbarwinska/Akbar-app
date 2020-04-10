package pl.barwinscy.Akbarapp.mappers;

import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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



//        Schedule schedule = new Schedule(LocalDate.parse(schoolDto.getContactDate()),
//                LocalDate.parse(schoolDto.getPhotographingDate()), LocalDate.parse(schoolDto.getPayOffDate()));
        Schedule schedule = dateParse(schoolDto);




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

    public static School mapSchoolToView(School school){

       school.getPhones().stream()
                .filter(phone -> phone.getNote() == null)
        .forEach(phone -> phone.setNote(""));

        if (school.getStatus() == null){
            school.setStatus(new Status(false, false, 0));
        }

        if (school.getSchedule() == null){
            school.setSchedule(new Schedule(null, null, null));
        }

        if (school.getAdditionalInfo() == null){
            school.setAdditionalInfo(new AdditionalInfo("", "", ""));
        }

        return school;
    }

    private static Schedule dateParse(SchoolDto school){

        LocalDate contact;
        LocalDate photograph;
        LocalDate payOff;

        String contractDate = school.getContactDate();
        if (!contractDate.isBlank()){
            contact = LocalDate.parse(contractDate);
        } else {
            contact = null;
        }

        String photoDate = school.getPhotographingDate();
        if (!photoDate.isBlank()){
            photograph = LocalDate.parse(photoDate);
        } else {
            photograph = null;
        }

        String payOffDate = school.getPayOffDate();
        if (!payOffDate.isBlank()){
            payOff = LocalDate.parse(payOffDate);
        } else {
            payOff = null;
        }

        return new Schedule(contact, photograph, payOff);
    }
}
