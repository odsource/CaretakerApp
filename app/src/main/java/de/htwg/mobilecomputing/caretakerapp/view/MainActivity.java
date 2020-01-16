package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Boolean logout = false;

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
                Intent intent;
                if (token == null) {
                    Toast.makeText(getApplicationContext(), "No valid credentials", Toast.LENGTH_LONG).show();
                } else {
                    if (loginViewModel.loginClicked.getValue() == true) {
                        loginViewModel.loginClicked.setValue(false);
                        EditText mail = findViewById(R.id.inEmail);
                        String s_mail = mail.getText().toString();
                        if (s_mail.equals("christoph.kaiser93@outlook.de")) {
                            intent = new Intent(MainActivity.this, DashboardActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, OnboardingConfirmationActivity.class);
                        }
                        intent.putExtra(EXTRA_ACCESS_TOKEN, token.accessToken);
                        startActivity(intent);
                    } else {

                    }

                }

            }
        });

        findViewById(R.id.changeToRegButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegActivity.class));
            }
        });

        findViewById(R.id.show_pass_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_password = findViewById(R.id.inPassword);
                if(view.getId()==R.id.show_pass_button){
                    if(edit_password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        ((ImageView) (view)).setImageResource(R.drawable.hide_password);

                        //Show Password
                        edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else {
                        ((ImageView) (view)).setImageResource(R.drawable.show_password);

                        //Hide Password
                        edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });
    }
}
