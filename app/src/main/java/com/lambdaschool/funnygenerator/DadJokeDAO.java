package com.lambdaschool.funnygenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DadJokeDAO {
    private static final String URL = "https://icanhazdadjoke.com/";

    public static String getRandomJoke() {
        // S03M02-8 adding header properties to request
        Map<String, String> headerProperties = new HashMap<>();
        headerProperties.put("Accept", "application/json");
        String result = NetworkAdapter.httpRequest(URL, headerProperties);
        String joke = "";
        try {
            joke = new JSONObject(result).getString("joke");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return joke;
    }
}
