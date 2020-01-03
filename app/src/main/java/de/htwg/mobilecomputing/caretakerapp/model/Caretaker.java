package de.htwg.mobilecomputing.caretakerapp.model;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
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
    private PersonalInformation personalInformation;

    @ColumnInfo
    private Address address;



    public Caretaker(String email, String password, String gender, String first_name, String surname, String birthday, String phone, String street, String number, int zip, String city, String country) {
        this.mEmail = email;
        this.mPassword = password;
        this.personalInformation = new PersonalInformation(email, gender, first_name, surname, birthday, phone);
        this.address = new Address(email, street, number, zip, city, country);
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

    public PersonalInformation getPersonalInformation() {
        if (personalInformation == null) {
            return new PersonalInformation("","", "", "", "", "");
        }
        return personalInformation;
    }

    public Address getAddress() {
        if (address == null) {
            return new Address("","", "", 0, "", "");
        }
        return address;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan7() {
        return getPassword().length() > 7;
    }
}
