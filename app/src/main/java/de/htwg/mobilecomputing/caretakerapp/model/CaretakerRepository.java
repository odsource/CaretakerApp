package de.htwg.mobilecomputing.caretakerapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.network.Webservice;

public class CaretakerRepository {
    private CaretakerDao mUserDao;
    private LiveData<List<Caretaker>> mAllCaretaker;
    private Webservice webservice;
/*
    public CaretakerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.caretakerDao();
        mAllCaretaker = mUserDao.getAll();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Caretaker>> getAllCaretaker() {
        return mAllCaretaker;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Caretaker user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }*/
}