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
                .rspo(Long.valueOf(schoolFromCsv.getRspo()))
                .name(schoolFromCsv.getName())
                .type(getTypeName(schoolFromCsv.getType()))
                .address(getFullAddress(schoolFromCsv))
                .email(schoolFromCsv.getEmail())
                .website(schoolFromCsv.getWebsite())
                .publicStatus(schoolFromCsv.getStatus())
                .build();
    }

    private static String getTypeName(String type) {
        switch (type) {
            case "Przedszkole":
                return SchoolType.PRZEDSZKOLE.getName();
            case "Szkoła podstawowa":
                return SchoolType.SZKOŁA_PODSTAWOWA.getName();
            case "Liceum ogólnokształcące":
                return SchoolType.LICEUM_OGÓLNOKSZTAŁCĄCE.getName();
            case "Liceum profilowane":
                return SchoolType.LICEUM_PROFILOWANE.getName();
            case "Technikum":
                return SchoolType.TECHNIKUM.getName();
            case "Zespół szkół i placówek oświatowych":
                return SchoolType.ZESPÓŁ_SZKÓŁ_I_PLACÓWEK_OŚWIATOWYCH.getName();
        }
        return SchoolType.OTHER.getName();
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
            case "ŁÓDZKIE":
                return Voivodeship.ŁÓDZKIE;
            case "ŚWIĘTOKRZYSKIE":
                return Voivodeship.ŚWIĘTOKRZYSKIE;
        }
        return Voivodeship.OTHER;
    }

    public static Phone mapToPhoneEntity(SchoolDataCsv schoolFromCsv) {
        return new Phone(phoneFormat(schoolFromCsv.getPhone()), Long.valueOf(schoolFromCsv.getRspo()));
    }

    private static String phoneFormat(String phone) {
        String readyPhone;
        StringBuilder stringBuilder = new StringBuilder(phone);
        readyPhone = stringBuilder.insert(2, " ").insert(6, "-").insert(9, "-").toString();
        return readyPhone;
    }

}

