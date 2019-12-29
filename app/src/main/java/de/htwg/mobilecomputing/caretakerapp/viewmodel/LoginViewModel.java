package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.os.Handler;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;


public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    public LoginViewModel() {

    }

    private MutableLiveData<Caretaker> userMutableLiveData;

    public LiveData<Caretaker> getCaretaker() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Caretaker caretaker = new Caretaker(email.getValue(), password.getValue());

                if (!caretaker.isEmailValid()) {
                    errorEmail.setValue("Enter a valid email address");
                } else {
                    errorEmail.setValue(null);
                }

                if (!caretaker.isPasswordLengthGreaterThan7())
                    errorPassword.setValue("Password Length should be greater than 5");
                else {
                    errorPassword.setValue(null);
                }

                userMutableLiveData.setValue(caretaker);
                busy.setValue(8); //8 == View.GONE

            }
        }, 2000);
    }
}
