package pl.barwinscy.Akbarapp.utils;

import pl.barwinscy.Akbarapp.SchoolType;
import pl.barwinscy.Akbarapp.Voivodeship;
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
                .type(getTypeAsEnum(schoolFromCsv.getType()))
                .address(getFullAddress(schoolFromCsv))
                .email(schoolFromCsv.getEmail())
                .website(schoolFromCsv.getWebsite())
                .publicStatus(schoolFromCsv.getStatus())
                .build();
    }

    private static SchoolType getTypeAsEnum(String type){
        switch (type){
            case "Przedszkole": return SchoolType.PRZEDSZKOLE;
            case "Szkoła podstawowa": return SchoolType.SZKOŁA_PODSTAWOWA;
            case  "Liceum ogólnokształcące": return SchoolType.LICEUM_OGÓLNOKSZTAŁCĄCE;
            case "Liceum profilowane": return SchoolType.LICEUM_PROFILOWANE;
            case "Technikum":return SchoolType.TECHNIKUM;
            case "Zespół szkół i placówek oświatowych": return SchoolType.ZESPÓŁ_SZKÓŁ_I_PLACÓWEK_OŚWIATOWYCH;
        }
        return SchoolType.OTHER;
    }

    private static Address getFullAddress(SchoolDataCsv schoolFromCsv) {
        return new Address(getVoidvodeshipAsEnum(schoolFromCsv.getVoivodeship()),
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

    private static Voivodeship getVoidvodeshipAsEnum(String voivodeship) {
        switch (voivodeship) {
            case "MAZOWIECKIE":
                return Voivodeship.MAZOWIECKIE;

            case " ŁÓDZKIE":
                return Voivodeship.ŁÓDZKIE;

            case " ŚWIĘTOKRZYSKIE":
                return Voivodeship.ŚWIĘTOKRZYSKIE;
        }
        return Voivodeship.OTHER;
    }

    public static Phone mapToPhoneEntity(SchoolDataCsv schoolFromCsv) {
        return new Phone(schoolFromCsv.getPhone(), Long.valueOf(schoolFromCsv.getRspo()));
    }

}

