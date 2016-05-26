package com.khatthaphone.asean;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
    int step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        setContentView(R.layout.activity_view_sqlite);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.NoMenuToolbar);
//        setSupportActionBar(myToolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);

        context = this.getApplicationContext();

        btnNext = (Button) findViewById(R.id.btnNext);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        question = (TextView) findViewById(R.id.tvQeustion);
        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);
        step = 1;

        getQuiz(step);

        btnNext.setOnClickListener(this);

    }

    private void getQuiz(int step) {
        final QuizProvider quizProvider = new QuizProvider(this);
        QuizObject quizObject = new QuizObject();
        quizProvider.open();

        quizObject = quizProvider.getData(quizObject, step);

        question.setText(quizObject.getQuestion());
        choice1.setText(quizObject.getChoice1());
        choice2.setText(quizObject.getChoice2());
        choice3.setText(quizObject.getChoice3());
        choice4.setText(quizObject.getChoice4());

        rightAnswer = quizObject.getAnswer();
        selectedRadioId = radioGroup.getCheckedRadioButtonId();
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
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:

                Dialog d = new Dialog(this);
                TextView tv = new TextView(this);
                d.setTitle("Great!");
                tv.setText("Right answer is " + rightAnswer);
                tv.setPadding(20, 20, 20, 20);
                d.setContentView(tv);
                d.show();
                d.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        step++;
                        unfocusTexts();
                        unCheck();
                        getQuiz(step);
                    }
                });

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


    public void unfocusTexts() {
        choice1.setTextSize(15);
        choice2.setTextSize(15);
        choice3.setTextSize(15);
        choice4.setTextSize(15);
    }

    public void unCheck() {
        choice1.setChecked(true);
        choice2.setChecked(true);
        choice3.setChecked(true);
        choice4.setChecked(true);
        choice1.setChecked(false);
        choice2.setChecked(false);
        choice3.setChecked(false);
        choice4.setChecked(false);
    }

    public void focusText(RadioButton radio) {
        radio.setTextSize((float) 20);
    }
}
