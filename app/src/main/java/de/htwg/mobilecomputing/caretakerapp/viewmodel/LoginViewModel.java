package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;


public class LoginViewModel extends AndroidViewModel {

    private CaretakerRepository mRepository;
    private LiveData<List<Caretaker>> mAllCaretaker;
    public LoginViewModel(Application application) {
        super(application);
        /*mRepository = new CaretakerRepository(application);
        mAllCaretaker = mRepository.getAllCaretaker();*/
    }

    LiveData<List<Caretaker>> getmAllCaretaker() {
        return mAllCaretaker;
    }

    public void insert(Caretaker caretaker) {
        mRepository.insert(caretaker);
    }

    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }

    private MutableLiveData<Caretaker> userMutableLiveData;

    public LiveData<Caretaker> getCaretaker() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    private MutableLiveData<Boolean> loginClicked;

    public LiveData<Boolean> getLoginClicked() {
        if (loginClicked == null) {
            loginClicked = new MutableLiveData<>();
        }

        return loginClicked;
    }

    public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginClicked.setValue(true);
                busy.setValue(8); //8 == View.GONE
            }
        }, 2000);
    }
}
