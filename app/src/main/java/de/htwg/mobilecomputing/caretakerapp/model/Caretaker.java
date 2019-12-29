package de.htwg.mobilecomputing.caretakerapp.model;

import android.util.Patterns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Caretaker {
    @PrimaryKey
    private String mEmail;

    @ColumnInfo(name = "password")
    private String mPassword;

    public Caretaker(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }

    public String getPassword() {
        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan7() {
        return getPassword().length() > 7;
    }
}
