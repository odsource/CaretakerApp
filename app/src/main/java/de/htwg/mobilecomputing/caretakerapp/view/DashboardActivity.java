package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityDashboardBinding;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.DashboardViewModel;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        DashboardViewModel viewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}
