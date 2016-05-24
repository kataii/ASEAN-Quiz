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

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnAdd = (Button) findViewById(R.id.btnAddQuestion);

        btnPlay.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                Intent play = new Intent(this, Quiz.class);
                startActivity(play);
                break;
            case R.id.btnAddQuestion:
                Intent add = new Intent(this, QuizAdder.class);
                startActivity(add);
                break;
        }
    }
}
