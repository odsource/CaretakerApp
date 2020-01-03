package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityMainBinding;
import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.model.LoginInfo;
import de.htwg.mobilecomputing.caretakerapp.model.Token;
import de.htwg.mobilecomputing.caretakerapp.network.Webservice;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.LoginViewModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_ACCESS_TOKEN = "EXTRA_ACCESS_TOKEN";
    private String accessToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

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

        loginViewModel.getUserToken().observe(this, new Observer<Token>() {
            @Override
            public void onChanged(Token token) {
                Intent intent = new Intent(MainActivity.this, OnboardingConfirmationActivity.class);
                intent.putExtra(EXTRA_ACCESS_TOKEN, token.accessToken);
                Toast.makeText(getApplicationContext(), "AccessToken: "+token.accessToken, Toast.LENGTH_LONG).show();
            }
        });

        loginViewModel.getLoginClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean user) {
                EditText mail = findViewById(R.id.inEmail);
                EditText password = findViewById(R.id.inPassword);
                LoginInfo loginInfo = new LoginInfo(mail.getText().toString(), password.getText().toString());
                /*Call<Token> call = webservice.login(loginInfo);
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        Token token = response.body();
                        accessToken = token.accessToken;

                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {

                    }
                });*/
            }
        });

        findViewById(R.id.changeToRegButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegActivity.class));
            }
        });
    }
}
