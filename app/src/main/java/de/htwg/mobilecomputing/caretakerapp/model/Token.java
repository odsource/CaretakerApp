package de.htwg.mobilecomputing.caretakerapp.model;

public class Token {
    public String accessToken;
    public String refreshToken;

    public Token(String aT, String rT) {
        this.accessToken = aT;
        this.refreshToken = rT;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
