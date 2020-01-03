package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CaretakerDao {
    @Query(("SELECT * FROM caretaker_table WHERE mEmail = :mail"))
    LiveData<Caretaker> getSpecificCaretaker(String mail);

    @Insert
    void insert(Caretaker user);
}
