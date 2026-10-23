package com.example.practicalfive;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/** Displays the selected municipality using the XML layout from section 7. */
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
        title.setText(name);
        image.setContentDescription(getString(R.string.municipality_image_description, name));

        // Matches section 4.5: Beijing, Shanghai, Chongqing, Tianjin.
        int index;
        int imageResource;
        switch (name) {
            case "Beijing":
                index = 0;
                imageResource = R.drawable.beijing;
                break;
            case "Shanghai":
                index = 1;
                imageResource = R.drawable.shanghai;
                break;
            case "Chongqing":
                index = 2;
                imageResource = R.drawable.chongqing;
                break;
            case "Tianjin":
                index = 3;
                imageResource = R.drawable.tianjin;
                break;
            default: finish(); return;
        }
        image.setImageResource(imageResource);
        information.setText(getResources().getStringArray(R.array.municipalities_array)[index]);
    }
}
