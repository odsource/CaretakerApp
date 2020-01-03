package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityRegisterPrivacyBinding;
import de.htwg.mobilecomputing.caretakerapp.model.Credentials;
import de.htwg.mobilecomputing.caretakerapp.network.Webservice;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.PrivacyViewModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPrivacyActivity extends AppCompatActivity{
    private String mail = null;
    private String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterPrivacyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register_privacy);
        PrivacyViewModel privacyViewModel = ViewModelProviders.of(this).get(PrivacyViewModel.class);
        binding.setPrivacyViewModel(privacyViewModel);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        mail = intent.getStringExtra(RegActivity.EXTRA_MAIL);
        password = intent.getStringExtra(RegActivity.EXTRA_PASSWORD);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.api.digital-nursing-service.ucura.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Webservice webservice = retrofit.create(Webservice.class);

        privacyViewModel.getCheck().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Credentials credentials = new Credentials(mail, password);
                Call<Void>  call = webservice.register(credentials);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getApplicationContext(), "Responsecode: " + response.code(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failure: " + t, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
