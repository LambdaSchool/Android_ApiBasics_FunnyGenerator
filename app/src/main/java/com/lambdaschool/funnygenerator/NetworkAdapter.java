package com.lambdaschool.funnygenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {

    public static final int CONNECT_TIMEOUT = 3000;
    public static final int READ_TIMEOUT = 3000;

    static String httpRequest(String urlString) {
        String result = "";
        InputStream inputStream = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.connect();

            final int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                if(inputStream != null) {
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder builder = new StringBuilder();

                    String line = reader.readLine();
                    while(line != null) {
                        builder.append(line);
                        line = reader.readLine();
                    }
                    result = builder.toString();
                }
            } else {
                throw new IOException();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = "MalformedUrl";
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOException";
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}
