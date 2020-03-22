package pl.barwinscy.Akbarapp.utils;

import pl.barwinscy.Akbarapp.entities.Address;
import pl.barwinscy.Akbarapp.entities.Phone;
import pl.barwinscy.Akbarapp.entities.School;

public class EntityMapper {

    private EntityMapper() {

    }

    public static School mapToSchoolEntity(SchoolDataCsv schoolFromCsv) {
        return School.builder()
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
                schoolFromCsv.getCounty(),
                schoolFromCsv.getBorough(),
                schoolFromCsv.getCity(),
                getFullStreet(schoolFromCsv),
                schoolFromCsv.getZipCode());
    }

    private static String getFullStreet(SchoolDataCsv schoolFromCsv) {
        StringBuilder streetBuilder = new StringBuilder();
        streetBuilder.append(schoolFromCsv.getStreet())
                .append(" ")
                .append(schoolFromCsv.getBuildingNumber())
                .append(schoolFromCsv.getLocalNumber());
        return streetBuilder.toString();
    }

    public static Phone mapToPhoneEntity(SchoolDataCsv schoolFromCsv) {
        return new Phone(schoolFromCsv.getPhone(), mapToSchoolEntity(schoolFromCsv));
    }

}
