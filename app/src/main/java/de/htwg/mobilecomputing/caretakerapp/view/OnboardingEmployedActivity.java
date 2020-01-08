package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingEmployedBinding;

public class OnboardingEmployedActivity extends AppCompatActivity {
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingEmployedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_employed);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accessToken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        findViewById(R.id.employed_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(OnboardingEmployedActivity.this, OnboardingPersonalDataActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                startActivity(startIntent);
            }
        });

        findViewById(R.id.not_employed_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(OnboardingEmployedActivity.this, OnboardingPersonalDataActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                startActivity(startIntent);
            }
        });
    }
}
