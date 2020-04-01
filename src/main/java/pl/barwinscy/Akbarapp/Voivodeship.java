package pl.barwinscy.Akbarapp;

public enum Voivodeship {

    MAZOWIECKIE("Mazowieckie"),
    ŁÓDZKIE(" Łódzkie"),
    ŚWIĘTOKRZYSKIE("Świętokrzyskie"),
    OTHER("Inne");

    private String name;

    Voivodeship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
