package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class first_act extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tv =findViewById(R.id.tvtv);
        EditText et_email =findViewById(R.id.et_email3);
        EditText et_pass = findViewById(R.id.et_pass);
        Button ok_c = findViewById(R.id.ok_c);
        Button cancle_c = findViewById(R.id.cancel_c);
        ImageView imageView = findViewById(R.id.imageView2);

         tv.setText("login");
         String s = (String) tv.getText();
         tv.setTextColor(R.color.blue);
         tv.setTextSize(18f);
         tv.setPadding(15 ,20,15,20 );
         et_email.setBackgroundColor(R.color.red);
         et_email.setActivated(true);
       //  imageView.setImageResource(R.drawable.tl);
         ok_c.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 tv.setText("Hellow user");
                 imageView.setImageResource(R.drawable.tl);
                 String r = String.valueOf(et_email.getText());  // خزن القيمة المدخلة من المستخدم
                 tv.setText(r);// show r
                 String a = String.valueOf(et_pass.getText());
             }
         });
cancle_c.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(first_act.this,"sorry you must enter",Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();

    }
});
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(first_act.this, MainActivity.class);
        startActivity(i);
    }
});
    }
}