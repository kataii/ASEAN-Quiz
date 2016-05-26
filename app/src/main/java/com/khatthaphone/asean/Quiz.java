package com.khatthaphone.asean;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Quiz extends ViewSQLite implements View.OnClickListener {

    RadioGroup radioGroup;
    TextView question;
    RadioButton choice1, choice2, choice3, choice4;
    Button btnNext;
    String rightAnswer;
    int selectedRadioId;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        context = this.getApplicationContext();

        btnNext = (Button) findViewById(R.id.btnNext);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        question = (TextView) findViewById(R.id.tvQeustion);
        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);

        RadioButtonStyle();

        final QuizProvider quizProvider = new QuizProvider(this);
        QuizObject quizObject = new QuizObject();
        quizProvider.open();
        quizObject = quizProvider.getData(quizObject);

        question.setText(quizObject.getQuestion());
        choice1.setText(quizObject.getChoice1());
        choice2.setText(quizObject.getChoice2());
        choice3.setText(quizObject.getChoice3());
        choice4.setText(quizObject.getChoice3());

        rightAnswer = quizObject.getAnswer();
        selectedRadioId = radioGroup.getCheckedRadioButtonId();

        btnNext.setOnClickListener(this);

    }

    private void RadioButtonStyle() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == choice1.getId()) {
                    unfocusTexts();
                    focusText(choice1);
                } else if (radioGroup.getCheckedRadioButtonId() == choice2.getId()) {
                    unfocusTexts();
                    focusText(choice2);
                } else if (radioGroup.getCheckedRadioButtonId() == choice3.getId()) {
                    unfocusTexts();
                    focusText(choice3);
                } else if (radioGroup.getCheckedRadioButtonId() == choice4.getId()) {
                    unfocusTexts();
                    focusText(choice4);
                }
            }

            private void unfocusTexts() {
                choice1.setTextSize(15);
                choice2.setTextSize(15);
                choice3.setTextSize(15);
                choice4.setTextSize(15);
            }

            private void focusText(RadioButton radio) {
                radio.setTextSize((float) 20);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:

                Dialog d = new Dialog(this);
                TextView tv = new TextView(this);
                d.setTitle("Great!");
                tv.setText("Right answer is " + rightAnswer + "\n" + "You selected " + selectedRadioId);
                d.setContentView(tv);
                d.show();

                break;
            default:
                Dialog D = new Dialog(this);
                TextView TV = new TextView(this);
                D.setTitle("Error!");
                TV.setText("Please make sure you selected the answer");
                D.setContentView(TV);
                D.show();
        }
    }
}
