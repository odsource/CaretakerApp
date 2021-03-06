package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityDashboardBinding;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.DashboardViewModel;

public class DashboardActivity extends AppCompatActivity {
    private String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        DashboardViewModel viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        accessToken = intent.getStringExtra(MainActivity.EXTRA_ACCESS_TOKEN);

        viewModel.accessToken.setValue(accessToken);

        viewModel.getLogout().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                }
            }
        });

        findViewById(R.id.refresh_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.contact_request_box).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.contact_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.accepted).setVisibility(View.VISIBLE);
                findViewById(R.id.contact_button).setVisibility(View.GONE);
            }
        });
    }
}
