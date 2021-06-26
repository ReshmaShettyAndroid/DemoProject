package com.example.demoproject.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.demoproject.R;

public class CommonUtils {
    Context context;
    private ProgressDialog progressDialog;

    public CommonUtils(Context context) {
        this.context = context;
    }

    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(context, context.getResources().getString(R.string.loading_txt), context.getResources().getString(R.string.please_wait_txt), true);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager ConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true)
            return true;
        else
            return false;
    }
}
