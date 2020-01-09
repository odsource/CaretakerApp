package de.htwg.mobilecomputing.caretakerapp.model;

import android.app.Application;

public class BasicApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getDatabase(this);
    }

    public CaretakerRepository getRepository() {
        return CaretakerRepository.getInstance(getDatabase());
    }
}
