package de.htwg.mobilecomputing.caretakerapp.model;

public class Token {
    private String mAccessToken;
    private String mRefreshToken;

    public Token(String aT, String rT) {
        this.mAccessToken = aT;
        this.mRefreshToken = rT;
    }
}
