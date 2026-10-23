package com.example.practicalfive;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends Activity {
    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.EXTRA_MUNICIPALITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = new TextProcessor().processMunicipality(
                getIntent().getStringExtra(EXTRA_MUNICIPALITY));
        if (name == null) {
            Toast.makeText(this, R.string.error_invalid_municipality, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setContentView(R.layout.activity_municipality);
        MainActivity.applyWindowInsets(findViewById(R.id.municipality_root));
        TextView title = findViewById(R.id.municipality_name);
        TextView information = findViewById(R.id.municipality_information);
        ImageView image = findViewById(R.id.municipality_image);
        Button visitSite = findViewById(R.id.visit_site_button);
        title.setText(name);
        image.setContentDescription(getString(R.string.municipality_image_description, name));

        // Choose the information, image and colour for this city.
        int index;
        int imageResource;
        int colorResource;
        int websiteResource;
        switch (name) {
            case "Beijing":
                index = 0;
                imageResource = R.drawable.beijing;
                colorResource = R.color.municipality_beijing;
                websiteResource = R.string.website_beijing;
                break;
            case "Shanghai":
                index = 1;
                imageResource = R.drawable.shanghai;
                colorResource = R.color.municipality_shanghai;
                websiteResource = R.string.website_shanghai;
                break;
            case "Chongqing":
                index = 2;
                imageResource = R.drawable.chongqing;
                colorResource = R.color.municipality_chongqing;
                websiteResource = R.string.website_chongqing;
                break;
            case "Tianjin":
                index = 3;
                imageResource = R.drawable.tianjin;
                colorResource = R.color.municipality_tianjin;
                websiteResource = R.string.website_tianjin;
                break;
            default: finish(); return;
        }
        image.setImageResource(imageResource);
        information.setText(getResources().getStringArray(R.array.municipalities_array)[index]);

        int cityColor = getColor(colorResource);
        title.setBackgroundColor(cityColor);
        information.setTextColor(cityColor);
        visitSite.setBackgroundTintList(ColorStateList.valueOf(cityColor));

        // Dark text is easier to read on the orange background.
        int textColor = getColor(name.equals("Chongqing")
                ? R.color.color_text_dark : R.color.color_text_light);
        title.setTextColor(textColor);
        visitSite.setTextColor(textColor);

        // Open the official website for the selected city.
        visitSite.setOnClickListener(view -> {
            String website = getString(websiteResource);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Show a message if no app can open the link.
                Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
