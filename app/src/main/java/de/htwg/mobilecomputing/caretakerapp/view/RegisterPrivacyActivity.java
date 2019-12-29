package de.htwg.mobilecomputing.caretakerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import de.htwg.mobilecomputing.caretakerapp.R;
import de.htwg.mobilecomputing.caretakerapp.databinding.ActivityRegisterPrivacyBinding;
import de.htwg.mobilecomputing.caretakerapp.model.Caretaker;
import de.htwg.mobilecomputing.caretakerapp.network.DownloadCallback;
import de.htwg.mobilecomputing.caretakerapp.network.NetworkFragment;
import de.htwg.mobilecomputing.caretakerapp.viewmodel.PrivacyViewModel;

public class RegisterPrivacyActivity extends AppCompatActivity implements DownloadCallback{
    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment networkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean downloading = false;

    private String mail = null;
    private String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterPrivacyBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_register_privacy);
        PrivacyViewModel privacyViewModel = ViewModelProviders.of(this).get(PrivacyViewModel.class);
        binding.setPrivacyViewModel(privacyViewModel);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();
        mail = intent.getStringExtra(RegActivity.EXTRA_MAIL);
        password = intent.getStringExtra(RegActivity.EXTRA_PASSWORD);

        networkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://dev.api.digital-nursing-service.ucura.com/api/v1");

        privacyViewModel.getCheck().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                startDownload(1);
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
            // TODO: Change class for intent
            startActivity(new Intent(RegisterPrivacyActivity.this, RegFinishedActivity.class));
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
            case DownloadCallback.Progress.ERROR:
                Toast.makeText(getApplicationContext(), "ERROR : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case DownloadCallback.Progress.CONNECT_SUCCESS:
                Toast.makeText(getApplicationContext(), "CONNECT_SUCCESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS:
                Toast.makeText(getApplicationContext(), "GET_INPUT_STREAM_SUCCESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                Toast.makeText(getApplicationContext(), "PROCESS_INPUT_STREAM_IN_PROGRESS : " + progressCode, Toast.LENGTH_SHORT).show();
                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS:
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
        return mail;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
