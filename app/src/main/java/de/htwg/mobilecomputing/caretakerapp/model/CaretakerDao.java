package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CaretakerDao {

    // Get all caretaker
    @Query("SELECT * FROM caretaker")
    LiveData<List<Caretaker>> getAll();

    // Search for specific caretaker by mail
    @Query("SELECT * FROM caretaker WHERE mEmail IN (:userIds)")
    List<Caretaker> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Caretaker... users);

    @Insert
    void insert(Caretaker user);

    @Delete
    void delete(Caretaker user);

}
