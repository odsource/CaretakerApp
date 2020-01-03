package de.htwg.mobilecomputing.caretakerapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import de.htwg.mobilecomputing.caretakerapp.network.Webservice;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CaretakerRepository {
    private CaretakerDao mUserDao;
    private PersonalInformationDao informationDao;
    private AddressDao addressDao;
    private Webservice webservice;

    public CaretakerRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.caretakerDao();
        informationDao = db.personalInformationDao();
        addressDao = db.addressDao();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.api.digital-nursing-service.ucura.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        webservice = retrofit.create(Webservice.class);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void register(Caretaker user) {
        /*AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });*/
        mUserDao.createCaretaker(user);
        Call<Void> call = webservice.register(new Credentials(user.getEmail(), user.getPassword()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private MutableLiveData<Token> token;
    public MutableLiveData<Token> getToken() {
        if (token == null) {
            token = new MutableLiveData<>();
        }
        return token;
    }

    public void login(LoginInfo loginInfo) {
        loginIntern(loginInfo);
    }

    private void loginIntern(LoginInfo loginInfo) {
        Call<Token> call = webservice.login(loginInfo);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                token.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }
}