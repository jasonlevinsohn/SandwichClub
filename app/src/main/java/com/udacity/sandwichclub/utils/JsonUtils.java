package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich mySandwich = new Sandwich();
        JSONObject sandwich;
        JSONObject nameObject;
        String name;
        List<String> alsoKnownAs = null;
        JSONArray akaJSONList;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = null;


        try {
            sandwich = new JSONObject(json);
            nameObject = sandwich.getJSONObject("name");
            name = nameObject.getString("mainName");
            image = sandwich.getString("image");
            placeOfOrigin = sandwich.getString("placeOfOrigin");

            akaJSONList = nameObject.getJSONArray("alsoKnownAs");

            if (akaJSONList != null) {
                alsoKnownAs = new ArrayList<>();

                for(int i = 0; i < akaJSONList.length(); i++) {
                    String aka = akaJSONList.getString(i);
                    if (i > 0) {
                        alsoKnownAs.add("\n");
                    }
                    alsoKnownAs.add(aka);
                }
                mySandwich.setAlsoKnownAs(alsoKnownAs);
            }

            mySandwich.setMainName(name);
            mySandwich.setImage(image);
            mySandwich.setPlaceOfOrigin(placeOfOrigin);

        } catch(JSONException e) {
            e.printStackTrace();
        }

        return mySandwich;
    }
}
