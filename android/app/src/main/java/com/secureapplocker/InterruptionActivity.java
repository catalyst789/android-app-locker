package com.secureapplocker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InterruptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interruption);

        // Add your UI elements or content here
        TextView textView = findViewById(R.id.interruption_text);
        textView.setText("App opening has been interrupted. Showing this screen instead.");
    }
}

