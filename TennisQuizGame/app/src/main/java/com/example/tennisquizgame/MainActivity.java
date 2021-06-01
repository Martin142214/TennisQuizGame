package com.example.tennisquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button playBtn;
    Button addWordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (Button)findViewById(R.id.playButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTheGame(v);
            }
        });
        addWordBtn = (Button)findViewById(R.id.addNewWordBtn);
        addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewWord(v);
            }
        });

    }

    public void playTheGame(View view){
        Intent intent = new Intent(this, QuizGameActivity.class);
        startActivity(intent);
    }

    public void addNewWord(View view){
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);
    }
}