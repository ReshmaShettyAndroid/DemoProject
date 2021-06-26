package com.example.demoproject.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyNetworkCall {

    ApiCallbackListner mResultCallback = null;
    Context context;

    public VolleyNetworkCall(Context context, ApiCallbackListner resultCallback) {
        mResultCallback = resultCallback;
        this.context = context;
        SSLCertificate.generateSSLCertificate();
    }

    public void CommonApiRequest(int requestType, String url, final HashMap<String, String> headerMap, final JSONObject jsonRequest) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonRequest jsonObjectRequest = new JsonRequest(requestType, url, jsonRequest, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (mResultCallback != null)
                    mResultCallback.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error :- " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = headerMap;
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return String.format("text/plain; charset=utf-8");
            }
        };
        queue.add(jsonObjectRequest);
    }
}

