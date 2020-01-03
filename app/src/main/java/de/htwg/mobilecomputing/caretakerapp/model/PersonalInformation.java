package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "personalInformation_table")
public class PersonalInformation {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

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

    public PersonalInformation(String gender, String first_name, String surname, String birthday, String phone) {
        this.gender = gender;
        this.first_name = first_name;
        this.surname = surname;
        this.birthday = birthday;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }
}
