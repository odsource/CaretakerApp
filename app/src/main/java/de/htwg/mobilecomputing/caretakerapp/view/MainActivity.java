package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityMainBinding;
import de.htwg.mobilecomputing.caretakerapp.generated.callback.OnClickListener;
import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.network.DownloadCallback;
import de.htwg.mobilecomputing.caretakerapp.network.NetworkFragment;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.LoginViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity implements DownloadCallback {

    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment networkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean downloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        networkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://dev.api.digital-nursing-service.ucura.com/api/v1");

        loginViewModel.getCaretaker().observe(this, new Observer<Caretaker>() {
            @Override
            public void onChanged(@Nullable Caretaker user) {
                if (user.getEmail().length() > 0 || user.getPassword().length() > 0) {
                    startDownload(2);
                }
            }
        });

        findViewById(R.id.changeToRegButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegActivity.class));
            }
        });
    }

    private void startDownload(int t) {
        if (!downloading && networkFragment != null) {
            // Execute the async download.
            int downloadType = t;
            networkFragment.startDownload(downloadType);
            downloading = true;


        }
    }

    @Override
    public void updateFromDownload(Object result) {
        if (result == null) {
            Toast.makeText(getApplicationContext(), "No result", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Result : " + result.toString(), Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(MainActivity.this, DashboardActivity.class));
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Toast.makeText(getApplicationContext(), "getActiveNetworkInfo : ", Toast.LENGTH_SHORT).show();
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                Toast.makeText(getApplicationContext(), "ERROR : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case Progress.CONNECT_SUCCESS:
                Toast.makeText(getApplicationContext(), "CONNECT_SUCCESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                Toast.makeText(getApplicationContext(), "GET_INPUT_STREAM_SUCCESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                Toast.makeText(getApplicationContext(), "PROCESS_INPUT_STREAM_IN_PROGRESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                Toast.makeText(getApplicationContext(), "PROCESS_INPUT_STREAM_SUCCESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void finishDownloading() {
        downloading = false;
        if (networkFragment != null) {
            networkFragment.cancelDownload();
        }
    }

    @Override
    public String getMail() {
        EditText mail= findViewById(R.id.inEmail);
        return mail.getText().toString();
    }

    @Override
    public String getPassword() {
        EditText pw = findViewById(R.id.inPassword);
        return pw.getText().toString();
    }
}
