package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyViewModel extends ViewModel {
    private Boolean cb1 = false;
    private Boolean cb2 = false;
    private MutableLiveData<Boolean> checkMutableLiveData;

    public LiveData<Boolean> getCheck() {
        if (checkMutableLiveData == null) {
            checkMutableLiveData = new MutableLiveData<>();
        }

        return checkMutableLiveData;
    }

    public PrivacyViewModel() {

    }

    public void onCheck1Clicked() {
        cb1 = !cb1;
    }

    public void onCheck2Clicked() {
        cb2 = !cb2;
    }

    public void onRegClicked() {
        if (cb1 && cb2) {
            checkMutableLiveData.setValue(true);
        }
    }
}
