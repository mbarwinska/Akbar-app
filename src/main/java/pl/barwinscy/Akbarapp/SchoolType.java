package pl.barwinscy.Akbarapp;

public enum SchoolType {

    PRZEDSZKOLE ("Przedszkole"),
    SZKOŁA_PODSTAWOWA ("Szkoła podstawowa"),
    LICEUM_OGÓLNOKSZTAŁCĄCE("Liceum ogólnokształcące"),
    LICEUM_PROFILOWANE ("Liceum profilowane"),
    TECHNIKUM("Technikum"),
    ZESPÓŁ_SZKÓŁ_I_PLACÓWEK_OŚWIATOWYCH("ZSP"),
    OTHER ("Inna");

    private String name;

    SchoolType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
