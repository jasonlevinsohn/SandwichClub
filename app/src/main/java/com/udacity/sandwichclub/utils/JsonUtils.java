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
        JSONArray ingredientsJSONList;
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
            description = sandwich.getString("description");

            akaJSONList = nameObject.getJSONArray("alsoKnownAs");
            ingredientsJSONList = sandwich.getJSONArray("ingredients");

            // Get Also Known As List
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

            // Get Ingredients List
            if (ingredientsJSONList != null) {
                ingredients = new ArrayList<>();

                for (int j = 0; j < ingredientsJSONList.length(); j++) {
                    String ingredient = ingredientsJSONList.getString(j);
                    if (j > 0) {
                        ingredients.add("\n");
                    }
                    ingredients.add(ingredient);
                }

                mySandwich.setIngredients(ingredients);
            }



            mySandwich.setMainName(name);
            mySandwich.setImage(image);
            mySandwich.setPlaceOfOrigin(placeOfOrigin);
            mySandwich.setDescription(description);

        } catch(JSONException e) {
            e.printStackTrace();
        }

        return mySandwich;
    }
}
