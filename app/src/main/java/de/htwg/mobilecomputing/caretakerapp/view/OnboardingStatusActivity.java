package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingStatusBinding;

public class OnboardingStatusActivity extends AppCompatActivity {
    private String accesstoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingStatusBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_status);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accesstoken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        findViewById(R.id.onboarding_status_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(OnboardingStatusActivity.this, OnboardingVerificationActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accesstoken);
                startActivity(startIntent);
            }
        });
    }
}
