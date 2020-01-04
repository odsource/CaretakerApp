package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingInformationBinding;

public class OnboardingInformationActivity extends AppCompatActivity {
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingInformationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_information);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accessToken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(OnboardingInformationActivity.this, OnboardingEmployedActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                startActivity(startIntent);
            }
        });
    }
}
