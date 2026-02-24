package com.example.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import activites.app_home;

public class EmailPasswordActivity extends AppCompatActivity {

    private static final String TAG = "EmailPasswordActivity";

    private FirebaseAuth mAuth;

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password);

        // تهيئة Firebase
        mAuth = FirebaseAuth.getInstance();

        // ربط العناصر
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        // زر تسجيل الدخول
        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){

                Toast.makeText(this,
                        "Please enter email and password",
                        Toast.LENGTH_SHORT).show();

            }
            else if(password.length() < 6){

                Toast.makeText(this,
                        "Password must be at least 6 characters",
                        Toast.LENGTH_SHORT).show();

            }
            else{

                loginUser(email, password);

            }

        });

        // الانتقال لصفحة إنشاء حساب
        tvSignUp.setOnClickListener(v -> {

            Intent intent = new Intent(
                    EmailPasswordActivity.this,
                    SignupActivity.class
            );

            startActivity(intent);

        });

    }


    // التحقق عند فتح التطبيق
    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs =
                getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        boolean isLoggedIn =
                prefs.getBoolean("isLoggedIn", false);

        if(isLoggedIn){

            startActivity(new Intent(this, app_home.class));
            finish();

        }
    }


    // تسجيل الدخول باستخدام Firebase
    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if(task.isSuccessful()){

                        Log.d(TAG, "Login success");

                        FirebaseUser user =
                                mAuth.getCurrentUser();

                        // حفظ الحالة باستخدام SharedPreferences
                        SharedPreferences prefs =
                                getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

                        SharedPreferences.Editor editor =
                                prefs.edit();

                        editor.putBoolean("isLoggedIn", true);
                        editor.putString("userEmail", user.getEmail());
                        editor.apply();


                        Toast.makeText(this,
                                "Welcome " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();


                        updateUI(user);

                    }
                    else{

                        Log.w(TAG, "Login failed", task.getException());

                        Toast.makeText(this,
                                "Login failed: "
                                        + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();

                    }

                });

    }


    // الانتقال للصفحة الرئيسية
    private void updateUI(FirebaseUser user) {

        if(user != null){

            Intent intent =
                    new Intent(this, app_home.class);

            startActivity(intent);

            finish();

        }

    }

}