package com.example.moviedatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnRegister, btnCancel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d("Login123", "Register Success " + task.getException().getMessage());

                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        } else {
                            Log.d("Login123", "Failed Register " + task.getException().getMessage());

                            Toast.makeText(this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        btnCancel.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
//            if (!email.isEmpty() && !password.isEmpty()) {
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(this, task -> {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(this, MovieListActivity.class));
//                                finish();
//                            } else {
//                                Log.d("Login123","Failed Register "+task.getException().getMessage());
//
//                                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//
//        btnCancel.setOnClickListener(v -> {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        });
    }
}

