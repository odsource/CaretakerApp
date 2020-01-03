package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface AddressDao {
    // Get all caretaker
    @Query("SELECT * FROM caretaker")
    LiveData<List<Caretaker>> getAll();

    // Search for specific caretaker by mail
    @Query("SELECT * FROM caretaker WHERE mEmail IN (:userIds)")
    List<Caretaker> loadAllByIds(int[] userIds);

    @Query(("SELECT * FROM caretaker WHERE mEmail = :mail"))
    LiveData<Caretaker> getSpecificCaretaker(String mail);

    @Insert
    void insertAll(Caretaker... users);

    @Insert
    void insert(Caretaker user);

    @Delete
    void delete(Caretaker user);
}
