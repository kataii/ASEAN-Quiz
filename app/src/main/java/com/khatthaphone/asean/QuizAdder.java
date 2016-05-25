package com.khatthaphone.asean;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class QuizAdder extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    EditText eQuestion;
    EditText eChoice1;
    EditText eChoice2;
    EditText eChoice3;
    EditText eChoice4;
    Spinner sAnswer;

    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mQuiz = rootRef.child("quiz");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_adder);

        btnAdd = (Button) findViewById(R.id.btnAdd2);
        eQuestion = (EditText) findViewById(R.id.eQuestion);
        eChoice1 = (EditText) findViewById(R.id.eAnswer1);
        eChoice2 = (EditText) findViewById(R.id.eAnswer2);
        eChoice3 = (EditText) findViewById(R.id.eAnswer3);
        eChoice4 = (EditText) findViewById(R.id.eAnswer4);
        sAnswer = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> rightAnswers = ArrayAdapter.createFromResource(this, R.array.rightAnswer, android.R.layout.simple_spinner_dropdown_item);
        rightAnswers.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sAnswer.setAdapter(rightAnswers);

        btnAdd.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd2:

                boolean worked = true;

                saveToSQLiteDatabase(worked);

                ContentValues values = new ContentValues();
                values.put("question", eQuestion.getText().toString());
                values.put("answer", sAnswer.getSelectedItem().toString());
                values.put("choice1", eChoice1.getText().toString());
                values.put("choice2", eChoice2.getText().toString());
                values.put("choice3", eChoice3.getText().toString());
                values.put("choice4", eChoice4.getText().toString());

                Map<String, String> newQuiz = new HashMap<String, String>();
                newQuiz.put("question", eQuestion.getText().toString());
                newQuiz.put("answer", sAnswer.getSelectedItem().toString());
                newQuiz.put("choice1", eChoice1.getText().toString());
                newQuiz.put("choice2", eChoice2.getText().toString());
                newQuiz.put("choice3", eChoice3.getText().toString());
                newQuiz.put("choice4", eChoice4.getText().toString());

                mQuiz.push().setValue(newQuiz);

                break;
        }
    }

    private void saveToSQLiteDatabase(boolean worked) {
        try {
            String question = eQuestion.getText().toString();
            String answer = sAnswer.getSelectedItem().toString();
            String choice1 = eChoice1.getText().toString();
            String choice2 = eChoice2.getText().toString();
            String choice3 = eChoice3.getText().toString();
            String choice4 = eChoice4.getText().toString();

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
                d.closeOptionsMenu();
                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        restartAcitvity();
                    }
                });
            }
        }
    }

    private void restartAcitvity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}