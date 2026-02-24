package com.example.myapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapter.DuaaAdapter;
import models.Duaa;

public class doaa_nbaoy extends AppCompatActivity {

    RecyclerView recyclerView;
    DuaaAdapter adapter;
    ArrayList<Duaa> duaaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doaa_nbaoy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            recyclerView = findViewById(R.id.recyclerView);

            duaaList = new ArrayList<>();
            duaaList.add(new Duaa("دعاء الهداية" ,"اللهم إني أسألك الهدى والتقى والعفاف والغنى"));
            duaaList.add(new Duaa("دعاء الدنيا والآخرة","ربنا آتنا في الدنيا حسنة وفي الآخرة حسنة وقنا عذاب النار"));
            duaaList.add(new Duaa("دعاء المغفرة","اللهم اغفر لي وارحمني واهدني وعافني وارزقني"));
        duaaList.add(new Duaa("سيد الاستغفار","اللَّهُمَّ أَنْتَ رَبِّي لا إِلَهَ إِلا أَنْتَ، خَلَقْتَنِي وَأَنَا عَبْدُكَ، وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ، وَأَبُوءُ لَكَ بِذَنْبِي فَاغْفِرْ لِي، فَإِنَّهُ لا يَغْفِرُ الذُّنُوبَ إِلا أَنْتَ"));


        adapter = new DuaaAdapter(duaaList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }
