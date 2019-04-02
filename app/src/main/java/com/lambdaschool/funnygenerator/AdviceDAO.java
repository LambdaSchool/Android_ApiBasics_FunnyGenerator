package com.lambdaschool.funnygenerator;

import org.json.JSONException;
import org.json.JSONObject;

public class AdviceDAO {

    public static final String GET_RANDOM_ADVICE_URL = "https://api.adviceslip.com/advice";

    // S03M02-4 write method to get data from specific
    public static String getRandomAdvice() {
        String result = NetworkAdapter.httpRequest(GET_RANDOM_ADVICE_URL);

        String advice = "";
        try {
            // S03M02-5 process data with specific structure
            JSONObject jsonObject = new JSONObject(result);
            JSONObject slipObject = jsonObject.getJSONObject("slip");
            advice = slipObject.getString("advice");
            String slipId = slipObject.getString("slip_id");

            advice = new JSONObject(result).getJSONObject("slip").getString("advice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return advice;
    }
}
