package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "address_table")
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

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

    public Address(String street, String number, String zip, String city, String country) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }
}
