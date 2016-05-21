package com.khatthaphone.asean;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class QuizAdder extends AppCompatActivity {

    Button btnAdd;
    EditText eQuestion;
    EditText eAnswer1;
    EditText eAnswer2;
    EditText eAnswer3;
    EditText eAnswer4;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_adder);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        eQuestion = (EditText) findViewById(R.id.eQuestion);
        eAnswer1 = (EditText) findViewById(R.id.eAnswer1);
        eAnswer2 = (EditText) findViewById(R.id.eAnswer2);
        eAnswer3 = (EditText) findViewById(R.id.eAnswer3);
        eAnswer4 = (EditText) findViewById(R.id.eAnswer4);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> rightAnswers = ArrayAdapter.createFromResource(this, R.array.rightAnswer, android.R.layout.simple_spinner_dropdown_item);
        rightAnswers.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(rightAnswers);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
//                values.put(QuizProvider.COLUMN_ROWID, mRowId);
                values.put(QuizProvider.COLUMN_QUESTION, eQuestion.getText().toString());
                values.put(QuizProvider.COLUMN_CHOICE1, eQuestion.getText().toString());
            }
        });

    }
}
