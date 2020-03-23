package pl.barwinscy.Akbarapp.utils;

import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;

public class EntityMapper {

    private EntityMapper() {

    }

    public static School mapToSchoolEntity(SchoolDataCsv schoolFromCsv) {
        return School.builder()
                .id(Long.valueOf(schoolFromCsv.getRspo()))
                .name(schoolFromCsv.getName())
                .type(schoolFromCsv.getType())
                .address(getFullAddress(schoolFromCsv))
                .email(schoolFromCsv.getEmail())
                .website(schoolFromCsv.getWebsite())
                .status(schoolFromCsv.getStatus())
                .build();
    }

    private static Address getFullAddress(SchoolDataCsv schoolFromCsv) {
        return new Address(schoolFromCsv.getVoivodeship(),
                schoolFromCsv.getCounty().trim(),
                schoolFromCsv.getBorough().trim(),
                schoolFromCsv.getCity().trim(),
                getFullStreet(schoolFromCsv).trim(),
                schoolFromCsv.getZipCode().trim());
    }

    private static String getFullStreet(SchoolDataCsv schoolFromCsv) {
        StringBuilder streetBuilder = new StringBuilder();
        streetBuilder.append(schoolFromCsv.getStreet().trim())
                .append(" ")
                .append(schoolFromCsv.getBuildingNumber().trim())
                .append(" ")
                .append(schoolFromCsv.getLocalNumber().trim());
        return streetBuilder.toString();
    }

    public static Phone mapToPhoneEntity(SchoolDataCsv schoolFromCsv) {
        return new Phone(schoolFromCsv.getPhone(), Long.valueOf(schoolFromCsv.getRspo()));
    }

}

