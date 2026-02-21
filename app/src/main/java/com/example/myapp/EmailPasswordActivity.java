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

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // ربط الـ Views
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        // زر تسجيل الدخول
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else if(password.length() < 6){
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });

        // زر إنشاء حساب (يفتح صفحة تسجيل جديدة)
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(EmailPasswordActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            updateUI(currentUser);
        }
    }

    // تسجيل الدخول
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();

                        Toast.makeText(EmailPasswordActivity.this,
                                "Welcome back " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();

                        updateUI(user);
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(EmailPasswordActivity.this,
                                "Login failed: " + task.getException().getMessage(),
                                Toast.LENGTH_LONG).show();
                        updateUI(null);
                    }
                });
    }

    // تحديث واجهة المستخدم بعد تسجيل الدخول
    private void updateUI(FirebaseUser user) {
        if(user != null){
            startActivity(new Intent(this, app_home.class));
            finish();
        }
    }
}