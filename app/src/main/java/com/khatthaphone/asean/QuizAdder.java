package com.khatthaphone.asean;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class QuizAdder extends AppCompatActivity implements View.OnClickListener {

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

        btnAdd = (Button) findViewById(R.id.btnAdd2);
        eQuestion = (EditText) findViewById(R.id.eQuestion);
        eAnswer1 = (EditText) findViewById(R.id.eAnswer1);
        eAnswer2 = (EditText) findViewById(R.id.eAnswer2);
        eAnswer3 = (EditText) findViewById(R.id.eAnswer3);
        eAnswer4 = (EditText) findViewById(R.id.eAnswer4);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> rightAnswers = ArrayAdapter.createFromResource(this, R.array.rightAnswer, android.R.layout.simple_spinner_dropdown_item);
        rightAnswers.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(rightAnswers);

        btnAdd.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd2:

                boolean worked = true;

                try {
                    String question = eQuestion.getText().toString();
                    String answer = spinner.getSelectedItem().toString();
                    String choice1 = eAnswer1.getText().toString();
                    String choice2 = eAnswer2.getText().toString();
                    String choice3 = eAnswer3.getText().toString();
                    String choice4 = eAnswer4.getText().toString();

                    QuizProvider entry = new QuizProvider(this);
                    entry.open();
                    entry.createEntry(question, answer, choice1, choice2, choice3, choice4);
                    entry.close();
                } catch (Exception e) {
                    worked = false;
                } finally {
                    if (worked) {
                        Dialog d = new Dialog(this);
                        d.setTitle("SQLite  works!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        tv.setPadding(20, 20, 20, 20);
                        d.setContentView(tv);
                        d.show();
                    }
                }
        }
    }
}