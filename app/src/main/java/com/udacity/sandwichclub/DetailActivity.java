package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import static android.view.View.*;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(final Sandwich sandwich) {
        TextView alsoKnowAsTv, alsoKnowAsTvLabel, placeOfOriginTv, placeOfOriginTvLabel,
                descriptionTv, ingredientsTv;
        alsoKnowAsTv = findViewById(R.id.also_known_tv);
        if (null != sandwich.getAlsoKnownAs() && !sandwich.getAlsoKnownAs().isEmpty()) {
            for (String alias : sandwich.getAlsoKnownAs()) {
                alsoKnowAsTv.append(alias + "\n");
            }
        } else {
            alsoKnowAsTvLabel = findViewById(R.id.also_know_label);
            alsoKnowAsTvLabel.setVisibility(GONE);
        }
        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginTv = findViewById(R.id.origin_tv);
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeOfOriginTvLabel = findViewById(R.id.origin_label);
            placeOfOriginTvLabel.setVisibility(GONE);
        }
        ingredientsTv = findViewById(R.id.ingredients_tv);
        if (null != sandwich.getIngredients()) {
            for (String ingredient : sandwich.getIngredients()) {
                ingredientsTv.append(ingredient + "\n");
            }
        }
        descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());
    }
}
