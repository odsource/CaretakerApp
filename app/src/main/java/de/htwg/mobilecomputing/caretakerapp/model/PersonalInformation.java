package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "personalInformation", foreignKeys = @ForeignKey(entity = Caretaker.class, parentColumns = "personalInformation", childColumns = "caretakerMail"))
public class PersonalInformation {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo
    private String caretakerMail;

    @ColumnInfo
    private String gender;

    @ColumnInfo
    private String first_name;

    @ColumnInfo
    private String surname;

    @ColumnInfo
    private String birthday;

    @ColumnInfo
    private String phone;

    public PersonalInformation(String caretakerMail, String gender, String first_name, String surname, String birthday, String phone) {
        this.gender = gender;
        this.first_name = first_name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
    }
}
