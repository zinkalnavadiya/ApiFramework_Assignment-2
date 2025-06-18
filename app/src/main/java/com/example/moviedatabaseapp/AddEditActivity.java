package com.example.moviedatabaseapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEditActivity extends AppCompatActivity {
    private EditText etTitle, etYear, etImageUrl;
    private Button btnSave, btnCancel;
    private FirebaseFirestore db;
    private String movieId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit);


        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etImageUrl = findViewById(R.id.etImageUrl);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        db = FirebaseFirestore.getInstance();

        if (getIntent().hasExtra("movieId")) {
            movieId = getIntent().getStringExtra("movieId");
            etTitle.setText(getIntent().getStringExtra("title"));
            etYear.setText(getIntent().getStringExtra("year"));
            etImageUrl.setText(getIntent().getStringExtra("imageUrl"));
        }

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String year = etYear.getText().toString();
            String imageUrl = etImageUrl.getText().toString();

            Map<String, Object> movie = new HashMap<>();
            movie.put("title", title);
            movie.put("year", year);
            movie.put("imageUrl", imageUrl);

            if (movieId != null) {
                db.collection("movies").document(movieId).set(movie);
            } else {
                db.collection("movies").add(movie);
            }
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}