package de.htwg.mobilecomputing.caretakerapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.network.Webservice;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaretakerRepository {
    private CaretakerDao mUserDao;
    private PersonalInformationDao informationDao;
    private AddressDao addressDao;
    private LiveData<List<Caretaker>> mAllCaretaker;
    private Webservice webservice;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dev.api.digital-nursing-service.ucura.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CaretakerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.caretakerDao();
        informationDao = db.personalInformationDao();
        addressDao = db.addressDao();
        //mAllCaretaker = mUserDao.getAll();
        webservice = retrofit.create(Webservice.class);
    }

    /*
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Caretaker>> getAllCaretaker() {
        return mAllCaretaker;
    }
    */

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Caretaker user) {
        /*AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });*/
        mUserDao.insert(user);
    }
}