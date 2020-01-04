package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityOnboardingPersonalDataBinding;

public class OnboardingPersonalDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityOnboardingPersonalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_personal_data);

        binding.setLifecycleOwner(this);
        setContentView(R.layout.activity_onboarding_personal_data);
    }
}
