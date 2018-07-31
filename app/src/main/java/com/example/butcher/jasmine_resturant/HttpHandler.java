package com.example.butcher.jasmine_resturant;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by devhanan on 9/1/2016.
 */
public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String serviceCall(String requrl) {

        String response = null;

        try {
            URL url = new URL(requrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            InputStream File = new BufferedInputStream(connection.getInputStream());

            response = convertFileToString(File);

        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }


    private String convertFileToString(InputStream file) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {

            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line + "\n");

            }

        } catch (IOException ioe) {

            ioe.printStackTrace();

        } finally {

            try {

                file.close();

            } catch (IOException ioe2) {

                ioe2.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}
