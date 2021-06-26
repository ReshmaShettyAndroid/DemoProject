package com.example.demoproject.viewmodel;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.example.demoproject.R;
import com.example.demoproject.service.ApiCallbackListner;
import com.example.demoproject.service.VolleyNetworkCall;

import java.util.HashMap;

public class MainViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> userMutableLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMutableData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void getApiResponse() {
        String url = context.getResources().getString(R.string.base_url);
        HashMap<String, String> header = new HashMap<>();
        VolleyNetworkCall volleyNetworkCall = new VolleyNetworkCall(context, new ApiCallbackListner() {
            @Override
            public void onSuccess(String response) {
                Log.d("Testing", "response from volley");
                userMutableLiveData.setValue(response);
            }
        });
        volleyNetworkCall.CommonApiRequest(Request.Method.GET, url, header, null);
    }
}
