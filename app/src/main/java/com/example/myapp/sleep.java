package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class sleep extends AppCompatActivity {
    int count0 = 3;
    int count1 = 3;
    int count2 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sleep);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button myButton = findViewById(R.id.myButton);
        Button myButton1 = findViewById(R.id.myButton1);
        Button myButton2 = findViewById(R.id.myButton2);

        setupButton(myButton, 0);
        setupButton(myButton1, 1);
        setupButton(myButton2, 2);
    }
    private void setupButton(Button button, int index) {

        button.setOnClickListener(v -> {

            int count;

            switch (index) {
                case 0: count = count0; break;
                case 1: count = count1; break;
                case 2: count = count2; break;
                default: count = 0;
            }

            if (count > 0) {
                count--;

                // تحديث النص
                button.setText(String.valueOf(count));

                // إذا وصل صفر يصير أحمر
                if (count == 0) {
                    button.setBackgroundColor(
                            getResources().getColor(android.R.color.holo_red_dark)
                    );
                }

                // حفظ القيمة الجديدة
                switch (index) {
                    case 0: count0 = count; break;
                    case 1: count1 = count; break;
                    case 2: count2 = count; break;
                }
            }
        });
    }
}