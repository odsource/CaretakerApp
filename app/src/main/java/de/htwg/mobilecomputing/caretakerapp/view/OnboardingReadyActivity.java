package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingReadyBinding;

public class OnboardingReadyActivity extends AppCompatActivity {
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingReadyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_ready);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accessToken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        findViewById(R.id.onboarding_ready_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(OnboardingReadyActivity.this, DashboardActivity.class);
                startIntent.putExtra(MainActivity.EXTRA_ACCESS_TOKEN, accessToken);
                startActivity(startIntent);
            }
        });
    }
}
