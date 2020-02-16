package com.uvita.myapp.modules.repository.remote;

import androidx.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.uvita.myapp.BuildConfig;
import com.uvita.myapp.general.MyApp;
import com.uvita.myapp.general.exceptions.RestException;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepository<T extends Object> {
    protected final Retrofit retrofit;

    protected BaseRepository() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.UVITA_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new GsonStringConverterFactory())
                .client(httpClientBuilder.build())
                .build();
    }

    protected Map<String, String> getAuthHeaders() {
        if (MyApp.currentSession != null) {
            return MyApp.currentSession.toHeaders();
        }
        return new HashMap<>();
    }

    protected void handleResponse(@NonNull Response<T> response, int expectedStatusCode, @NonNull ResponseListener<T> listener) {
        if (expectedStatusCode == response.code()) {
            listener.successResponserHandler(response);
        } else if (response.errorBody() != null) {
            listener.errorResponseHandler(RestException.create(response.errorBody()));
        } else {
            listener.errorResponseHandler(new Exception("Unexpected error"));
        }
    }
}
