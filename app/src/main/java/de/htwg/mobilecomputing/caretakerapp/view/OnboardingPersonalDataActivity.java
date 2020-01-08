package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingPersonalDataBinding;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.OnboardingPersonalViewModel;

public class OnboardingPersonalDataActivity extends AppCompatActivity {
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingPersonalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_personal_data);
        OnboardingPersonalViewModel viewModel = ViewModelProviders.of(this).get(OnboardingPersonalViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accessToken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);
        viewModel.accessToken.setValue(accessToken);

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), "Error: " + s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getSuccess().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                Toast.makeText(getApplicationContext(), "Success: " + s.toString(), Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(OnboardingPersonalDataActivity.this, OnboardingGoalsActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                startActivity(startIntent);
            }
        });
    }
}
