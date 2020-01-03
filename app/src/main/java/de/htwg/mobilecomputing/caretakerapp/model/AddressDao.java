package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface AddressDao {

    @Query("SELECT address FROM caretaker WHERE mEmail = (:mail)")
    LiveData<List<Caretaker>> getAddress(String mail);

    @Insert
    void insertAddress(Address address);

    @Update
    void updateAddress(Address address);
}
