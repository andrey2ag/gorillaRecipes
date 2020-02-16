package com.uvita.myapp.general.exceptions;


import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;

public class RestException extends BaseException {
    private int statusCode;
    private boolean success;
    private List<String> errors;

    public RestException(int statusCode, boolean success, List<String> errors) {
        super(errors.get(0));
        this.statusCode = statusCode;
        this.success = success;
        this.errors = errors;
    }

    public RestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.errors.get(0);
    }

    public static RestException create(ResponseBody responseBody) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(responseBody.string(), RestException.class);
        } catch (IOException e) {
            ArrayList<String> paramErrors= new ArrayList<>();
            return new RestException(HttpURLConnection.HTTP_INTERNAL_ERROR, false, paramErrors);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}