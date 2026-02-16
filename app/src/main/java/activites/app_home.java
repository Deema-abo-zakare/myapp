package activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.R;
import com.example.myapp.d;
import com.example.myapp.message;
import com.example.myapp.notification;

public class app_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView d =findViewById(R.id.d);
        Button start = findViewById(R.id.btn_start);
        ImageView img = findViewById(R.id.settingsIcon);
        ImageView img1 = findViewById(R.id.noteficationIcon);
        TextView message =findViewById(R.id.r);
        RadioButton radio = findViewById(R.id.radio2);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, setting.class);
                startActivity(i);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, notification.class);
                startActivity(i);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, start.class);
                startActivity(i);
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, d.class);
                startActivity(i);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, message.class);
                startActivity(i);
            }
        });
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(app_home.this," أجابة صحيحة",Toast.LENGTH_LONG).show();

            }
        });


    }
}