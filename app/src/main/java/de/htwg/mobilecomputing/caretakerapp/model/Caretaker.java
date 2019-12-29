package de.htwg.mobilecomputing.caretakerapp.model;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "caretaker")
public class Caretaker {
    @PrimaryKey
    @NonNull
    private String mEmail;

    @ColumnInfo
    private String mPassword;

    @ColumnInfo
    private String gender;

    @ColumnInfo
    private String first_name;

    @ColumnInfo
    private String surname;

    @ColumnInfo
    private Date birthday;

    @ColumnInfo
    private String phone;

    @ColumnInfo
    private String street;

    @ColumnInfo
    private String number;

    @ColumnInfo
    private String zip;

    @ColumnInfo
    private String city;

    @ColumnInfo
    private String country;

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
