package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityRegBinding;
import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.RegViewModel;

public class RegActivity extends AppCompatActivity {
    public static final String EXTRA_MAIL = "EXTRA_MAIL";
    public static final String EXTRA_PASSWORD = "EXTRA_PASSWORD";

    private Boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reg);
        RegViewModel regViewModel = ViewModelProviders.of(this).get(RegViewModel.class);
        binding.setRegViewModel(regViewModel);
        binding.setLifecycleOwner(this);

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password = findViewById(R.id.regPassword);
                String pw = password.getText().toString();
                EditText password2 = findViewById(R.id.regPasswordMatch);
                String pw2 = password2.getText().toString();
                if (pw.equals(pw2)) {
                    Intent reg = new Intent(RegActivity.this, RegisterPrivacyActivity.class);
                    EditText mail = findViewById(R.id.regEmail);
                    reg.putExtra(EXTRA_MAIL, mail.getText().toString());
                    reg.putExtra(EXTRA_PASSWORD, pw);
                    startActivity(reg);
                } else {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.show_pass1_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_password = findViewById(R.id.regPassword);
                if(view.getId()==R.id.show_pass1_button){
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

        findViewById(R.id.show_pass2_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_password = findViewById(R.id.regPasswordMatch);
                if(view.getId()==R.id.show_pass2_button){
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

        findViewById(R.id.log_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, MainActivity.class));
            }
        });
    }
}
