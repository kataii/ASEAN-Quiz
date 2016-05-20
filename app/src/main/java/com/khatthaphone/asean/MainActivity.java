package com.khatthaphone.asean;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.play);
        btnAdd = (Button) findViewById(R.id.addQuestion);

        btnPlay.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnPlay) {
            Intent intent = new Intent(this, Quiz.class);
            startActivity(intent);
        }
        if (view == btnAdd) {
            Intent intent = new Intent(this, QuizAdder.class);
            startActivity(intent);
        }
    }
}
