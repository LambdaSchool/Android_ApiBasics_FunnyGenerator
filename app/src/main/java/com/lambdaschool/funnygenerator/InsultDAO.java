package com.lambdaschool.funnygenerator;

import org.json.JSONException;
import org.json.JSONObject;

public class InsultDAO {
    private static final String BASE_URL = "https://evilinsult.com/generate_insult.php";

    public static String getRandomInsult() {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?type=json&lang=en");
        String result = NetworkAdapter.httpRequest(urlBuilder.toString());

        String insult = "";
        try {
            insult = new JSONObject(result).getString("insult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return insult;
    }
}
