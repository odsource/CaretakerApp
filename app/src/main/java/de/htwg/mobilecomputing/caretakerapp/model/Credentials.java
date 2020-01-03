package de.htwg.mobilecomputing.caretakerapp.model;

public class Credentials {
    private String email;
    private  String password;

    private String[] preferredLanguages = {"de_CH", "de"};
    private String timezone = "Europe/Zurich";
    private String[] consent = {"privacy_note", "eula", "general"};

    public Credentials(String mail, String password) {
        this.email = mail;
        this.password = password;
    }
}
