package com.lambdaschool.funnygenerator;

import org.json.JSONException;
import org.json.JSONObject;

public class AdviceDAO {
    public static String getAdvice() {
        String result = NetworkAdapter.httpRequest("https://api.adviceslip.com/advice");
      /*{
            "slip": {
                    "advice": "Never buy cheap cling film.",
                    "slip_id": "25"
            }
        }*/
      String advice = "";
        try {
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
