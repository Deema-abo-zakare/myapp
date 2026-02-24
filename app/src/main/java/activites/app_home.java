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

import com.example.myapp.ApiActivity;
import com.example.myapp.R;
import com.example.myapp.ayah;
import com.example.myapp.d;
import com.example.myapp.hadeth;
import com.example.myapp.message;
import com.example.myapp.notification;
import com.example.myapp.EmailPasswordActivity; // شاشة تسجيل الدخول
import com.google.firebase.auth.FirebaseAuth; // ✅ تسجيل الخروج من Firebase

public class app_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_home);

        // تعويض الـ system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ربط العناصر من XML
        TextView d = findViewById(R.id.d);
        TextView h = findViewById(R.id.h);
        TextView a = findViewById(R.id.a);
        Button start = findViewById(R.id.btn_start);
        ImageView settings = findViewById(R.id.settingsIcon);
        ImageView notification = findViewById(R.id.noteficationIcon);
        ImageView logout = findViewById(R.id.logoutIcon);
        TextView message = findViewById(R.id.r);
        RadioButton radio = findViewById(R.id.radio2);

        // الانتقالات بين الصفحات
        settings.setOnClickListener(v -> startActivity(new Intent(app_home.this, setting.class)));
        notification.setOnClickListener(v -> startActivity(new Intent(app_home.this, notification.class)));
        start.setOnClickListener(v -> startActivity(new Intent(app_home.this, start.class)));
        d.setOnClickListener(v -> startActivity(new Intent(app_home.this, d.class)));
        message.setOnClickListener(v -> startActivity(new Intent(app_home.this, message.class)));
        radio.setOnClickListener(v -> Toast.makeText(app_home.this, "أجابة صحيحة", Toast.LENGTH_LONG).show());

        // تسجيل الخروج
        logout.setOnClickListener(v -> {
            // تسجيل الخروج من Firebase
            FirebaseAuth.getInstance().signOut();

            // الانتقال إلى شاشة تسجيل الدخول ومنع الرجوع
            Intent intent = new Intent(app_home.this, EmailPasswordActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, hadeth.class);
                startActivity(i);
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(app_home.this, ayah.class);
                startActivity(i);
            }
        });



    }
}