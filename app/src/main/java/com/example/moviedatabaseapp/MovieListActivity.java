package com.example.moviedatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id.recyclerViewMovies);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadMovies();

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadMovies(); // call your method to fetch from Firestore
    }
    private void loadMovies() {
        db.collection("movies").get().addOnSuccessListener(queryDocumentSnapshots -> {
            movieList.clear();
            for (DocumentSnapshot doc : queryDocumentSnapshots) {
                Movie movie = doc.toObject(Movie.class);
                movie.setId(doc.getId());
                movieList.add(movie);
            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(this, "Error loading movies", Toast.LENGTH_SHORT).show());
    }

    private void addInitialMovies() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        List<Movie> movies = Arrays.asList(
                new Movie("The Shawshank Redemption", "1994", "https://m.media-amazon.com/images/I/51NiGlapXlL._AC_.jpg"),
                new Movie("The Godfather", "1972", "https://m.media-amazon.com/images/I/51rOnIjLqzL._AC_.jpg"),
                new Movie("The Dark Knight", "2008", "https://m.media-amazon.com/images/I/51zUbui+gbL._AC_.jpg"),
                new Movie("Inception", "2010", "https://m.media-amazon.com/images/I/51s+WbmqvLL._AC_.jpg"),
                new Movie("Interstellar", "2014", "https://m.media-amazon.com/images/I/81kz05BpHLL._AC_SL1500_.jpg"),
                new Movie("Fight Club", "1999", "https://m.media-amazon.com/images/I/51v5ZpFyaFL._AC_.jpg"),
                new Movie("Pulp Fiction", "1994", "https://m.media-amazon.com/images/I/51V5ZpFyaFL._AC_.jpg"),
                new Movie("Forrest Gump", "1994", "https://m.media-amazon.com/images/I/41cXN3pVwZL._AC_.jpg"),
                new Movie("The Matrix", "1999", "https://m.media-amazon.com/images/I/51EG732BV3L.jpg"),
                new Movie("Gladiator", "2000", "https://m.media-amazon.com/images/I/71xBLRBYOiL._AC_SL1178_.jpg"),
                new Movie("The Lord of the Rings", "2001", "https://m.media-amazon.com/images/I/51Qvs9i5a+L._AC_.jpg"),
                new Movie("Titanic", "1997", "https://m.media-amazon.com/images/I/71rNJQ2g-EL._AC_SY679_.jpg"),
                new Movie("Avatar", "2009", "https://m.media-amazon.com/images/I/61OUGpUfAyL._AC_SL1024_.jpg"),
                new Movie("Joker", "2019", "https://m.media-amazon.com/images/I/71ZkE-MefML._AC_SY679_.jpg"),
                new Movie("The Avengers", "2012", "https://m.media-amazon.com/images/I/71NIpZ0yG8L._AC_SY679_.jpg"),
                new Movie("Avengers: Endgame", "2019", "https://m.media-amazon.com/images/I/81ExhpBEbHL._AC_SY679_.jpg"),
                new Movie("Up", "2009", "https://m.media-amazon.com/images/I/81WojUxbbFL._AC_SY679_.jpg"),
                new Movie("Toy Story", "1995", "https://m.media-amazon.com/images/I/51NpxX9XJXL._AC_SY445_.jpg"),
                new Movie("Frozen", "2013", "https://m.media-amazon.com/images/I/71qJ1VGR4rL._AC_SY679_.jpg"),
                new Movie("Shrek", "2001", "https://m.media-amazon.com/images/I/81OluFZnkeL._AC_SY679_.jpg")
        );

        for (Movie movie : movies) {
            db.collection("movies").add(movie);
        }
    }

}