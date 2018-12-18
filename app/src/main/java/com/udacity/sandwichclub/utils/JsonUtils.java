package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        final Sandwich sandwich = new Sandwich();
        try {
            final JSONObject jsonObject = new JSONObject(json);
            setElement(sandwich, jsonObject);
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        return sandwich;
    }

    private static void setElement(final Sandwich sandwich, final JSONObject jsonObject) throws JSONException {
        final Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            switch (keys.next()) {
                case NAME:
                    final JSONObject name = jsonObject.getJSONObject(NAME);
                    final String mainName = name.getString(MAIN_NAME);
                    sandwich.setMainName(mainName);
                    elligableArray(sandwich, name, ALSO_KNOWN_AS);
                    break;
                case PLACE_OF_ORIGIN:
                    final String placeOfOrigin = jsonObject.getString(PLACE_OF_ORIGIN);
                    sandwich.setPlaceOfOrigin(placeOfOrigin);
                    break;
                case DESCRIPTION:
                    final String description = jsonObject.getString(DESCRIPTION);
                    sandwich.setDescription(description);
                    break;
                case IMAGE:
                    final String image = jsonObject.getString(IMAGE);
                    sandwich.setImage(image);
                    break;
                case INGREDIENTS:
                    elligableArray(sandwich, jsonObject, INGREDIENTS);
                    break;
                default:
                    Log.i(TAG, "description processed, no additional keys present");
            }
        }
    }

    private static void elligableArray(final Sandwich sandwich,
                                       final JSONObject name,
                                       final String arrayKey) throws JSONException {
        final JSONArray jsonArray = name.getJSONArray(arrayKey);
        if (jsonArray.length() > 0) {
            populateElementAsArray(jsonArray, sandwich, arrayKey);
        }
    }

    private static void populateElementAsArray(final JSONArray jsonArray,
                                               final Sandwich sandwich,
                                               final String arrayKey) throws JSONException {
        String[] tempArray = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            tempArray[i] = jsonArray.getString(i);
        }
        switch (arrayKey) {
            case ALSO_KNOWN_AS:
                sandwich.setAlsoKnownAs(Arrays.asList(tempArray));
            case INGREDIENTS:
                sandwich.setIngredients(Arrays.asList(tempArray));
            default:
                Log.i(TAG, "on sandwich details all arrays set");
        }
    }
}
