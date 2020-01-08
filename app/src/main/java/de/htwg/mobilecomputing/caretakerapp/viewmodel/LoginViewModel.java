package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;
import de.htwg.mobilecomputing.caretakerapp.model.LoginInfo;
import de.htwg.mobilecomputing.caretakerapp.model.Token;


public class LoginViewModel extends AndroidViewModel {
    MutableLiveData<Boolean> firstLogin;

    public MutableLiveData<Boolean> getFirstLogin() {
        if (firstLogin == null) {
            firstLogin = new MutableLiveData<>();
        }
        return firstLogin;
    }

    private CaretakerRepository mRepository;
    public LoginViewModel(Application application) {
        super(application);
        mRepository = new CaretakerRepository(application);
        userToken = mRepository.getToken();
        firstLogin.postValue(mRepository.firstLogin());
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

    private MutableLiveData<Boolean> loginClicked;

    public LiveData<Boolean> getLoginClicked() {
        if (loginClicked == null) {
            loginClicked = new MutableLiveData<>();
        }

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
                //loginClicked.setValue(true);
                busy.setValue(8); //8 == View.GONE
            }
        }, 1000);
    }
}
