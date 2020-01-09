package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.Date;

import de.htwg.mobilecomputing.caretakerapp.model.BasicApp;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;

public class DashboardViewModel extends AndroidViewModel {
    MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<String> accessToken = new MutableLiveData<>();
    private MutableLiveData<Boolean> logout = new MutableLiveData<>();

    public LiveData<Boolean> getLogout() {
        return logout;
    }

    public MutableLiveData<String> getAccessToken() {
        return accessToken;
    }

    public MutableLiveData<String> getDate() {
        return date;
    }

    private CaretakerRepository mRepository;
    public DashboardViewModel(Application application) {
        super(application);
        mRepository = ((BasicApp) application).getRepository();
        //Date currentDate = Calendar.getInstance().toString();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        month += 1;
        String m;
        if (month < 10) {
            m = "0" + Integer.toString(month);
        } else {
            m = Integer.toString(month);
        }

        String d;
        if (day < 10) {
            d = "0" + Integer.toString(day);
        } else {
            d = Integer.toString(day);
        }


        String cal = d + "." + m + "." + Integer.toString(year);
        date.postValue(cal);
    }

    public void logout() {
        mRepository.logout(accessToken.getValue());
        logout.postValue(true);
    }
}
