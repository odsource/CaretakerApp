package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;

public class DashboardViewModel extends AndroidViewModel {

    private CaretakerRepository mRepository;
    public DashboardViewModel(Application application) {
        super(application);
        mRepository = new CaretakerRepository(application);
    }
}
