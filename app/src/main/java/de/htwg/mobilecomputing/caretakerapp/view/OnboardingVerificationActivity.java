package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingVerificationBinding;

public class OnboardingVerificationActivity extends AppCompatActivity {
    private String accesstoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingVerificationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_verification);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accesstoken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        findViewById(R.id.onboarding_verification_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(OnboardingVerificationActivity.this, OnboardingReadyActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accesstoken);
                startActivity(startIntent);
            }
        });
    }
}
