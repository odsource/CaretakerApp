package de.htwg.mobilecomputing.caretakerapp.network;

import java.util.List;

import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.Credentials;
import de.htwg.mobilecomputing.caretakerapp.model.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Webservice {
    @GET("registration")
    Call<List<Caretaker>> getAllCaretaker();

    @Headers("Content-Type: application/json")
    @POST("registration")
    Call<Void> register(@Body Credentials credentials);

    @POST("sessions")
    Call<Token> login(@Body Credentials credentials);

    @DELETE("sessions")
    Call<Void> logout(@Header("Authorization") String header);
}
