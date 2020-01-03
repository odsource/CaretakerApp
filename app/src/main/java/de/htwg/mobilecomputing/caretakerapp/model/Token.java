package de.htwg.mobilecomputing.caretakerapp.model;

public class Token {
    private String mAccessToken;
    private String mRefreshToken;

    public Token(String aT, String rT) {
        this.mAccessToken = aT;
        this.mRefreshToken = rT;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }
}
