package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityRegFinishedBinding;

public class RegFinishedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegFinishedBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_reg_finished);
        binding.setLifecycleOwner(this);

        findViewById(R.id.login_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegFinishedActivity.this, MainActivity.class));
            }
        });
    }
}
