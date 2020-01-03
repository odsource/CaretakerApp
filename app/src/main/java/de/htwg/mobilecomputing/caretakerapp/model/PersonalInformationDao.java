package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface PersonalInformationDao {

    @Query("SELECT personalInformation FROM caretaker where mEmail = (:mail)")
    LiveData<PersonalInformation> getPersonalInformation(String mail);

    @Insert
    void insertPersonalInformation(PersonalInformation personalInformation);
}
