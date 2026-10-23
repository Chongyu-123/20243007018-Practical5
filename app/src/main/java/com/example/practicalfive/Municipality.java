package com.example.practicalfive;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/** Basic destination for testing the explicit Intent from the main screen. */
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
        title.setText(name);

        // Matches section 4.5: Beijing, Shanghai, Chongqing, Tianjin.
        int index;
        switch (name) {
            case "Beijing": index = 0; break;
            case "Shanghai": index = 1; break;
            case "Chongqing": index = 2; break;
            case "Tianjin": index = 3; break;
            default: finish(); return;
        }
        information.setText(getResources().getStringArray(R.array.municipalities_array)[index]);
    }
}
