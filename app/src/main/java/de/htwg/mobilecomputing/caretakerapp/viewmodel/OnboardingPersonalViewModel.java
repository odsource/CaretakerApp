package de.htwg.mobilecomputing.caretakerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import de.htwg.mobilecomputing.caretakerapp.model.Address;
import de.htwg.mobilecomputing.caretakerapp.model.CaretakerRepository;
import de.htwg.mobilecomputing.caretakerapp.model.PersonalInformation;
import de.htwg.mobilecomputing.caretakerapp.model.Token;

public class OnboardingPersonalViewModel extends AndroidViewModel {

    private CaretakerRepository caretakerRepository;
    private MutableLiveData<Token> userToken;
    private MutableLiveData<String> error;
    private MutableLiveData<Integer> success;

    public MutableLiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public MutableLiveData<Integer> getSuccess() {
        if (success == null) {
            success = new MutableLiveData<>();
        }
        return success;
    }

    public OnboardingPersonalViewModel(Application application) {
        super(application);
        caretakerRepository = new CaretakerRepository(application);
        userToken = caretakerRepository.getToken();
        error = caretakerRepository.getError();
        success = caretakerRepository.getSuccess();
    }

    public void nextClicked() {
        String name = userName.getValue();
        String[] parts = name.split(" ");
        PersonalInformation personalInformation = new PersonalInformation(gender.getValue(), parts[0], parts[1],birthdate.getValue(), phone.getValue());
        Address address = new Address(street.getValue(), number.getValue(), zip.getValue(), city.getValue(), country.getValue());
        caretakerRepository.createPersonalInformation(personalInformation);
        caretakerRepository.createAddress(address);
    }

    private MutableLiveData<String> gender = new MutableLiveData<>();

    public LiveData<String> getGender() {
        return gender;
    }

    private MutableLiveData<String> userName;

    public MutableLiveData<String> getUserName() {
        if (userName == null) {
            userName = new MutableLiveData<>();
        }
        return userName;
    }

    private MutableLiveData<String> birthdate;

    public MutableLiveData<String> getBirthdate() {
        if (birthdate == null) {
            birthdate = new MutableLiveData<>();
        }
        return birthdate;
    }

    private MutableLiveData<String> phone;

    public MutableLiveData<String> getPhone() {
        if (phone == null) {
            phone = new MutableLiveData<>();
        }
        return phone;
    }

    private MutableLiveData<String> street;

    public MutableLiveData<String> getStreet() {
        if (street == null) {
            street = new MutableLiveData<>();
        }
        return street;
    }

    private MutableLiveData<String> number;

    public MutableLiveData<String> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
        }
        return number;
    }

    private MutableLiveData<String> zip;

    public MutableLiveData<String> getZip() {
        if (zip == null) {
            zip = new MutableLiveData<>();
        }
        return zip;
    }

    private MutableLiveData<String> city;

    public MutableLiveData<String> getCity() {
        if (city == null) {
            city = new MutableLiveData<>();
        }
        return city;
    }

    private MutableLiveData<String> country;

    public MutableLiveData<String> getCountry() {
        if (country == null) {
            country = new MutableLiveData<>();
        }
        return country;
    }

    public void maleGender() {
        gender.postValue("Male");
    }

    public void femaleGender() {
        gender.postValue("Female");
    }
}
