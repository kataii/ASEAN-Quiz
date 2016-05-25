package com.khatthaphone.asean;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Quiz extends ViewSQLite {

    RadioGroup radioGroup;
    TextView question;
    RadioButton answer1, answer2, answer3, answer4;
    Button btnNext;
    String rightAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        question = (TextView) findViewById(R.id.tvQeustion);
        answer1 = (RadioButton) findViewById(R.id.choice1);
        answer2 = (RadioButton) findViewById(R.id.choice2);
        answer3 = (RadioButton) findViewById(R.id.choice3);
        answer4 = (RadioButton) findViewById(R.id.choice4);

        RadioButtonStyle();

        final QuizProvider quiz = new QuizProvider(this);
        quiz.open();
        String data = quiz.getData();
        quiz.open();

        TextView tv = new TextView(this);
//        tv.setText(data[1]+data[2]+data[3]+data[4]+data[5]+data[6]);
        tv.setText(data);
        setContentView(tv);

        quiz.close();
    }

    private void RadioButtonStyle() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == answer1.getId()) {
                    unfocusTexts();
                    focusText(answer1);
                } else if (radioGroup.getCheckedRadioButtonId() == answer2.getId()) {
                    unfocusTexts();
                    focusText(answer2);
                } else if (radioGroup.getCheckedRadioButtonId() == answer3.getId()) {
                    unfocusTexts();
                    focusText(answer3);
                } else if (radioGroup.getCheckedRadioButtonId() == answer4.getId()) {
                    unfocusTexts();
                    focusText(answer4);
                }
            }

            private void unfocusTexts() {
                answer1.setTextSize(15);
                answer2.setTextSize(15);
                answer3.setTextSize(15);
                answer4.setTextSize(15);
            }

            private void focusText(RadioButton radio) {
                radio.setTextSize((float) 20);
            }
        });
    }
}
