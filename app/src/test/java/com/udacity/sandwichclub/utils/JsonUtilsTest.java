package com.udacity.sandwichclub.utils;

import android.support.test.filters.SmallTest;

import com.udacity.sandwichclub.model.Sandwich;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SmallTest
public class JsonUtilsTest {
    private static final String SAMPLE = "{\\\"name\\\":{\\\"mainName\\\":\\\"Club sandwich\\\",\\\"alsoKnownAs\\\":" +
            "[\\\"Clubhousesandwich\\\"]},\\\"placeOfOrigin\\\":\\\"United States\\\",\\\"description\\\":" +
            "\\\"A club sandwich, alsocalled a clubhouse sandwich, is a sandwich of bread (occasionally toasted), slicedcooked poultry, fried bacon, lettuce, tomato, and mayonnaise. It is often cut intoquarters or halves and held together by cocktail sticks. Modern versions frequently havetwo layers which are separated by an additional slice ofbread.\\\"," +
            "\\\"image\\\":\\\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png\\\"," +
            "\\\"ingredients\\\":[\\\"Toastedbread\\\",\\\"Turkey or chicken\\\",\\\"Bacon\\\",\\\"Lettuce\\\",\\\"Tomato\\\",\\\"Mayonnaise\\\"]}";

    @Test
    public void jsonElement_MainNameIsPresent() {
        final Sandwich sandwich = JsonUtils.parseSandwichJson(SAMPLE);

        assertEquals("Club sandwich", sandwich.getMainName());
    }
}