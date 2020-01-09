package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.Date;

import de.htwg.mobilecomputing.caretakerapp.model.BasicApp;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;

public class DashboardViewModel extends AndroidViewModel {
    MutableLiveData<String> date = new MutableLiveData<>();

    public MutableLiveData<String> getDate() {
        return date;
    }

    private CaretakerRepository mRepository;
    public DashboardViewModel(Application application) {
        super(application);
        mRepository = ((BasicApp) application).getRepository();
        //Date currentDate = Calendar.getInstance().toString();
        date.postValue(Calendar.getInstance().toString());
    }
}
