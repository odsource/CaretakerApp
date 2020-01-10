package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import de.htwg.mobilecomputing.caretakerapp.model.BasicApp;
import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;
import de.htwg.mobilecomputing.caretakerapp.model.LoginInfo;
import de.htwg.mobilecomputing.caretakerapp.model.Token;


public class LoginViewModel extends AndroidViewModel {
    MutableLiveData<Boolean> firstLogin = new MutableLiveData<>();

    public MutableLiveData<Boolean> getFirstLogin() {
        return firstLogin;
    }

    private CaretakerRepository mRepository;
    public LoginViewModel(Application application) {
        super(application);
        mRepository = ((BasicApp) application).getRepository();
        userToken = mRepository.getToken();
        firstLogin.postValue(mRepository.firstLogin());
        loginClicked.setValue(false);
    }

    public void register(Caretaker caretaker) {
        mRepository.register(caretaker);
    }

    private MutableLiveData<Token> userToken;

    public LiveData<Token> getUserToken() {
        return userToken;
    }

    private void login(LoginInfo loginInfo) {
        mRepository.login(loginInfo);
    }

    private MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    public MutableLiveData<Boolean> loginClicked = new MutableLiveData<>();

    public LiveData<Boolean> getLoginClicked() {
        return loginClicked;
    }

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public void onLoginClicked() {
        getBusy().setValue(0); //View.VISIBLE
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                login(new LoginInfo(email.getValue(), password.getValue()));
                loginClicked.setValue(true);
                busy.setValue(8); //8 == View.GONE
            }
        }, 1000);
    }
}
