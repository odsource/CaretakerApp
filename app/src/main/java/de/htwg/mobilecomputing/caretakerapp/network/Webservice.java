package de.htwg.mobilecomputing.caretakerapp.network;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.Credentials;
import de.htwg.mobilecomputing.caretakerapp.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Webservice {
    @GET("registration")
    Call<List<Caretaker>> getAllCaretaker();

    @POST("registration")
    Call<Void> pushCaretaker();

    @POST("sessions")
    Call<Token> login(@Body Credentials credentials);
}
