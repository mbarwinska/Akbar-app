package pl.barwinscy.Akbarapp.mappers;

import pl.barwinscy.Akbarapp.Voivodeship;
import pl.barwinscy.Akbarapp.dto.PhoneDTO;
import pl.barwinscy.Akbarapp.dto.SchoolDto;
import pl.barwinscy.Akbarapp.entities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SchoolMapper {
    private SchoolMapper() {

    }

    public static School mapDtoToEntity(SchoolDto schoolDto) {
        Status status = new Status(schoolDto.isOurs(), schoolDto.isContracted(), schoolDto.getCalendarsLeftNumber());
        Schedule schedule = dateParse(schoolDto);

        AdditionalInfo additionalInfo = new AdditionalInfo(schoolDto.getNote1(), schoolDto.getNote2(), schoolDto.getNote3());
        School school = School.builder()
                .type(schoolDto.getType())
                .name(schoolDto.getName())
                .address(addressMapper(schoolDto))
                .email(schoolDto.getEmail())
                .website(schoolDto.getWebsite())
                .publicStatus(schoolDto.getPublicStatus())
                .build();

        if (schoolDto.getId() != null){
            school.setId(schoolDto.getId());
        }
        if (schoolDto.getAdditionInfoId()!= null){
            additionalInfo.setId(schoolDto.getAdditionInfoId());
        }
        if (schoolDto.getStatusId()!= null){
            status.setId(schoolDto.getStatusId());
        }
        if (schoolDto.getScheduleId()!= null){
            schedule.setId(schoolDto.getScheduleId());
        }

        school.setStatus(status);
        school.setSchedule(schedule);
        school.setAdditionalInfo(additionalInfo);

        if (schoolDto.getPhones() != null){
            phoneListMapper(schoolDto.getPhones(), school);
        }
        return school;
    }

    private static void phoneListMapper(List<PhoneDTO> phoneList, School school){
        for (PhoneDTO phoneDTO : phoneList) {
            Phone phone = new Phone(phoneDTO.getNumber());
            phone.setId(phoneDTO.getId());
            phone.setNote(phoneDTO.getNote());
            school.setPhones(phone);
        }
    }

    private static Address addressMapper(SchoolDto schoolDto){
        Address address = new Address();
        if (schoolDto.getVoivodeship().equalsIgnoreCase("ŁÓDZKIE")) {
            address.setVoivodeship(Voivodeship.ŁÓDZKIE);
        } else if (schoolDto.getVoivodeship().equalsIgnoreCase("MAZOWIECKIE")) {
            address.setVoivodeship(Voivodeship.MAZOWIECKIE);
        } else if (schoolDto.getVoivodeship().equalsIgnoreCase("ŚWIĘTOKRZYSKIE")) {
            address.setVoivodeship(Voivodeship.ŚWIĘTOKRZYSKIE);
        } else {
            address.setVoivodeship(Voivodeship.OTHER);
        }
        address.setCounty(schoolDto.getCounty());
        address.setBorough(schoolDto.getBorough());
        address.setCity(schoolDto.getCity());
        address.setStreet(schoolDto.getStreet());
        address.setZipCode(schoolDto.getZipCode());
        return address;
    }

    public static SchoolDto mapSchoolEntityToDto(School school){
        List<PhoneDTO> phones = new ArrayList<>();
        school.getPhones().forEach(phone -> phones.add(new PhoneDTO(phone.getId(), phone.getPhoneNumber(), phone.getNote())));

        for (PhoneDTO phoneDTO : phones) {
            if (phoneDTO.getNote().isEmpty()) {
                phoneDTO.setNote("notatka");
            }
        }

        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(school.getId());
        schoolDto.setName(school.getName());
        schoolDto.setType(school.getType());
        schoolDto.setStreet(school.getAddress().getStreet());
        schoolDto.setZipCode(school.getAddress().getZipCode());
        schoolDto.setCity(school.getAddress().getCity());
        schoolDto.setVoivodeship(school.getAddress().getVoivodeship().getName());
        schoolDto.setCounty(school.getAddress().getCounty());
        schoolDto.setBorough(school.getAddress().getBorough());
        schoolDto.setPhones(phones);
        schoolDto.setEmail(school.getEmail());
        schoolDto.setWebsite(school.getWebsite());
        schoolDto.setPublicStatus(school.getPublicStatus());
        schoolDto.setOurs(school.getStatus().isOurs());
        schoolDto.setContracted(school.getStatus().isContracted());
        schoolDto.setCalendarsLeftNumber(school.getStatus().getCalendarsLeftNumber());
        schoolDto.setContactDate((school.getSchedule().getContactDate() != null) ? school.getSchedule().getContactDate().toString() : null);
        schoolDto.setPhotographingDate((school.getSchedule().getPhotographingDate() != null) ? school.getSchedule().getPhotographingDate().toString() : null);
        schoolDto.setPayOffDate((school.getSchedule().getPayoffDate()!= null) ? school.getSchedule().getPayoffDate().toString() : null);
        schoolDto.setAnotherDate((school.getSchedule().getOtherDate() != null) ? school.getSchedule().getOtherDate().toString() : null );
        schoolDto.setEmployee((school.getEmployee() != null) ?school.getEmployee().getFirstName() + " " + school.getEmployee().getLastName() : null );
        schoolDto.setNote1(school.getAdditionalInfo().getNote1());
        schoolDto.setNote2(school.getAdditionalInfo().getNote2());
        schoolDto.setNote3(school.getAdditionalInfo().getNote3());
        schoolDto.setStatusId(school.getStatus().getId());
        schoolDto.setAdditionInfoId(school.getAdditionalInfo().getId());
        schoolDto.setScheduleId(school.getSchedule().getId());
        return schoolDto;
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
