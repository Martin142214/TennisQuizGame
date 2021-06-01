package com.example.tennisquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    Button getBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Intent intent = getIntent();
        String text = intent.getStringExtra("initText");
        EditText editText = (EditText)findViewById(R.id.editTextNewWord);
        editText.setText(text);

        getBackBtn = (Button)findViewById(R.id.getBackToMainAct);
        getBackBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddWordActivity.this, MainActivity.class));
        });
    }

    public void addWord(View view){
        EditText newWordField = (EditText)findViewById(R.id.editTextNewWord);
        String newWord = newWordField.getText().toString();

        EditText newDefinitionField = (EditText)findViewById(R.id.editTextNewDefinition);
        String newDefinition = newDefinitionField.getText().toString();

        PrintStream output = null;
        try {
            output = new PrintStream(openFileOutput("questions.txt", MODE_APPEND));
            output.println(newWord + "\t" + newDefinition);
            output.close();

            Intent intent = new Intent();
            intent.putExtra("newWord", newWord);
            intent.putExtra("newDefinition", newDefinition);
            setResult(RESULT_OK, intent);

            finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}