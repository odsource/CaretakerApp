package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.Calendar;
import java.util.Date;

import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;

public class DashboardViewModel extends AndroidViewModel {
    MutableLiveData<String> date;

    public MutableLiveData<String> getDate() {
        if (date == null) {
            date = new MutableLiveData<>();
        }
        return date;
    }

    private CaretakerRepository mRepository;
    public DashboardViewModel(Application application) {
        super(application);
        mRepository = new CaretakerRepository(application);
        //Date currentDate = Calendar.getInstance().toString();
        date.postValue(Calendar.getInstance().toString());
    }
}
