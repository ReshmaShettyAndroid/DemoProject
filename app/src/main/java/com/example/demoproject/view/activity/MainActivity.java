package com.example.demoproject.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.demoproject.R;
import com.example.demoproject.model.ResponseDto;
import com.example.demoproject.utils.CommonUtils;
import com.example.demoproject.viewmodel.MainViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private CommonUtils commonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objectInitailization();
        liveData();
        apiCall();
    }

    public void objectInitailization() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        commonUtils = new CommonUtils(this);
    }

    public void liveData() {
        mainViewModel.getMutableData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String response) {
                try {
                    commonUtils.dismissProgressDialog();
                    Log.d("Testing", "Response mutable:-- " + response);
                    ResponseDto responseDto = new Gson().fromJson(new JSONObject(response).getJSONObject("data").toString(), ResponseDto.class);
                    Toast.makeText(MainActivity.this,"First usr Id:-"+responseDto.getUsers().get(0).getId(),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void apiCall() {

        if (commonUtils.isNetworkConnected()) {
            mainViewModel.getApiResponse();
            commonUtils.showProgressDialog();
        } else {
            Toast.makeText(this, getString(R.string.internet_txt), Toast.LENGTH_SHORT).show();
        }
    }
}

