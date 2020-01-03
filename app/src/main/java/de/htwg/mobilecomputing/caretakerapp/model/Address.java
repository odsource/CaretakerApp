package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "address", foreignKeys = @ForeignKey(entity = Caretaker.class, parentColumns = "address", childColumns = "caretakerMail"))
public class Address {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo
    private String caretakerMail;

    @ColumnInfo
    private String street;

    @ColumnInfo
    private String number;

    @ColumnInfo
    private int zip;

    @ColumnInfo
    private String city;

    @ColumnInfo
    private String country;

    public Address(String caretakerMail, String street, String number, int zip, String city, String country) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }
}
