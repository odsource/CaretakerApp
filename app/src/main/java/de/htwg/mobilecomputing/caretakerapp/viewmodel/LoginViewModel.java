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

    private CaretakerRepository mRepository;
    public LoginViewModel(Application application) {
        super(application);
        mRepository = new CaretakerRepository(application);
    }

    public void register(Caretaker caretaker) {
        mRepository.register(caretaker);
    }

    private MutableLiveData<Token> mUserToken;

    public LiveData getUserToken() {
        if (mUserToken == null) {
            mUserToken = new MutableLiveData<>();
        }
        return mUserToken;
    }

    private void login(LoginInfo loginInfo) {
        Token token = mRepository.login(loginInfo);
        mUserToken.postValue(token);
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

    private MutableLiveData<String> email;

    public LiveData<String> getEmail() {
        if (email == null) {
            email = new MutableLiveData<>();
        }
        return email;
    }

    private MutableLiveData<String> password;

    public LiveData<String> getPassword() {
        if (password == null) {
            password = new MutableLiveData<>();
        }
        return password;
    }

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
        }, 2000);
    }
}
