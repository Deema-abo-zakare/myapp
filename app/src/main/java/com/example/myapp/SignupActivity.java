package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import activites.app_home; // الصفحة الرئيسية بعد تسجيل الدخول

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button btnSignUp;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // رابط XML للـ Sign Up

        // تهيئة Firebase
        mAuth = FirebaseAuth.getInstance();

        // ربط الـ Views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnRegister); // الزر الرئيسي للتسجيل
        tvLogin = findViewById(R.id.tvLogin); // رابط العودة للـ Login (لو حابة تضيفيه بالـ XML)

        // زر التسجيل
        btnSignUp.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else if(password.length() < 6){
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(email, password);
            }
        });

        // رابط العودة للـ Login
        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, EmailPasswordActivity.class));
            finish();
        });
    }

    // تسجيل مستخدم جديد عبر Firebase
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        // Toast عند النجاح
                        Toast.makeText(SignupActivity.this,
                                "Account created successfully! Welcome " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();

                        // الانتقال للصفحة الرئيسية بعد التسجيل
                        startActivity(new Intent(SignupActivity.this, app_home.class));
                        finish();
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignupActivity.this,
                                "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}