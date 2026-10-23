package com.example.practicalfive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {
    private EditText municipalityInput;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyWindowInsets(findViewById(R.id.main));

        municipalityInput = findViewById(R.id.municipality_input);
        municipalityInput.setOnEditorActionListener((view, actionId, event) -> {
            // Consume both key events, but submit only on key-down.
            if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    || event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_ENTER)) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                    openMunicipality();
                }
                return true;
            }
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                openMunicipality();
                return true;
            }
            return false;
        });
    }

    private void openMunicipality() {
        String input = municipalityInput.getText().toString();
        String municipality = textProcessor.processMunicipality(input);
        if (municipality == null) {
            municipalityInput.setError(getString(input.trim().isEmpty()
                    ? R.string.error_empty_municipality : R.string.error_invalid_municipality));
            municipalityInput.requestFocus();
            return;
        }
        municipalityInput.setError(null);
        Intent intent = new Intent(this, Municipality.class);
        intent.putExtra(Municipality.EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }

    // Keep content clear of system bars and the on-screen keyboard.
    static void applyWindowInsets(View root) {
        int left = root.getPaddingLeft();
        int top = root.getPaddingTop();
        int right = root.getPaddingRight();
        int bottom = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            view.setPadding(left + insets.left, top + insets.top,
                    right + insets.right, bottom + insets.bottom);
            return windowInsets;
        });
        ViewCompat.requestApplyInsets(root);
    }
}
