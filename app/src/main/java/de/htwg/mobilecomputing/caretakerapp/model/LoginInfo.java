package de.htwg.mobilecomputing.caretakerapp.model;

public class LoginInfo {
    private String loginId;
    private String password;

    public LoginInfo(String mail, String password) {
        this.loginId = mail;
        this.password = password;
    }
}
