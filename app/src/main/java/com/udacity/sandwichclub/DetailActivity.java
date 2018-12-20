package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.screen.DetailActivityUi;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.jetbrains.anko.AnkoContextKt;

import static android.view.View.*;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnkoContextKt.setContentView(new DetailActivityUi(), this);

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
                .placeholder(R.drawable.ic_food_bank_icon)
                .error(R.drawable.ic_beghilos_error)
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
        final String empty = getString(R.string.emptyData);
        if (null != sandwich.getAlsoKnownAs() && !sandwich.getAlsoKnownAs().isEmpty()) {
            alsoKnowAsTv.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));
        } else {
            alsoKnowAsTv.setText(empty);
        }
        placeOfOriginTv = findViewById(R.id.origin_tv);
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOriginTv.setText(empty);
        } else {
            placeOfOriginTv.setText(sandwich.getPlaceOfOrigin());
        }
        ingredientsTv = findViewById(R.id.ingredients_tv);
        if (null != sandwich.getIngredients()) {
            ingredientsTv.setText(TextUtils.join("\n", sandwich.getIngredients()));
        }
        descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());
    }
}
