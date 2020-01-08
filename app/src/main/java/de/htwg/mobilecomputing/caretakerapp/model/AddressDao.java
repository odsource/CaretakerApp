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

    @Query("SELECT * FROM address_table")
    Address getAddress();

    @Insert
    void insertAddress(Address address);

    @Update
    void updateAddress(Address address);
}
