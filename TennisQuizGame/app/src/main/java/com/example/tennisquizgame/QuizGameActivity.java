package com.example.tennisquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuizGameActivity extends AppCompatActivity {

    HashMap<String, String> collection;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        context = this;

        collection = new HashMap<String, String>();
        readFileData();
        chooseWords();

        ((ListView) findViewById(R.id.listOfDefinitions)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemClicked = adapterView.getItemAtPosition(i).toString();
                String word = ((TextView)findViewById(R.id.textViewWord)).getText().toString();
                String correctAnswer = collection.get(word);
                if (itemClicked.equals(correctAnswer)) {
                    Toast.makeText(context, "Correct answer!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Wrong answer!",
                            Toast.LENGTH_SHORT).show();
                }
                chooseWords();
            }
        });
    }

    public void readFileData(){
        Scanner scan = new Scanner(
                getResources().openRawResource(R.raw.questions));
        readFileHelper(scan);

        try {
            String input = getResources().openRawResource(R.raw.added_words).toString();
            Scanner scan2 = new Scanner(openFileInput(input));

            readFileHelper(scan2);
        } catch (Exception e) {
        }
    }

    public void readFileHelper(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length < 2) continue;
            collection.put(parts[0], parts[1]);
        }
    }

    public void chooseWords(){
        List<String> words = new ArrayList<>(collection.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String word = words.get(randomIndex);
        String definition = collection.get(word);


        List<String> definitions = new ArrayList<>(collection.values());
        definitions.remove(definition);

        Collections.shuffle(definitions);
        definitions = definitions.subList(0, 3);
        definitions.add(definition);

        Collections.shuffle(definitions);


        ((TextView) findViewById(R.id.textViewWord)).setText(word);
        ListView list = findViewById(R.id.listOfDefinitions);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(definitions)
        );
        list.setAdapter(adapter);
    }
}