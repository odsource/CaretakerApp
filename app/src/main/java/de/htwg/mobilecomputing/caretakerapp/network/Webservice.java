package de.htwg.mobilecomputing.caretakerapp.network;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Webservice {
    @GET("registration")
    Call<List<Caretaker>> getAllCaretaker();

    @POST("registration")
    Call<Caretaker> pushCaretaker();

    @POST("sessions")
    Call<Caretaker> login();
}
