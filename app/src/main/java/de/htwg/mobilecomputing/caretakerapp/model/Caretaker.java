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
    private String mGender;

    @ColumnInfo
    private String mFirst_name;

    @ColumnInfo
    private String mSurname;

    @ColumnInfo
    private String mBirthday;

    @ColumnInfo
    private String mPhone;

    @ColumnInfo
    private String mStreet;

    @ColumnInfo
    private String mNumber;

    @ColumnInfo
    private int mZip;

    @ColumnInfo
    private String mCity;

    @ColumnInfo
    private String mCountry;

    public Caretaker(String email, String password, String gender, String first_name, String surname, String birthday, String phone, String street, String number, int zip, String city, String country) {
        this.mEmail = email;
        this.mPassword = password;
        this.mGender = gender;
        this.mFirst_name = first_name;
        this.mSurname = surname;
        this.mBirthday = birthday;
        this.mPhone = phone;
        this.mStreet = street;
        this.mNumber = number;
        this.mZip = zip;
        this.mCity = city;
        this.mCountry = country;
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

    public String getGender() {
        if (mGender == null) {
            return "";
        }
        return mGender;
    }

    public String getFirst_name() {
        if (mFirst_name == null) {
            return "";
        }
        return mFirst_name;
    }

    public String getSurname() {
        if (mSurname == null) {
            return "";
        }
        return mSurname;
    }

    public String getBirthday() {
        if (mBirthday == null) {
            return "";
        }
        return mBirthday;
    }

    public String getPhone() {
        if (mPhone == null) {
            return "";
        }
        return mPhone;
    }

    public String getStreet() {
        if (mStreet == null) {
            return "";
        }
        return mStreet;
    }

    public String getNumber() {
        if (mNumber == null) {
            return "";
        }
        return mNumber;
    }

    public int getZip() {
        return mZip;
    }

    public String getCity() {
        if (mCity == null) {
            return "";
        }
        return mCity;
    }

    public String getCountry() {
        if (mCountry == null) {
            return "";
        }
        return mCountry;
    }
}
