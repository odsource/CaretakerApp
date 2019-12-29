package de.htwg.mobilecomputing.caretakerapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Caretaker.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CaretakerDao caretakerDao();
}

