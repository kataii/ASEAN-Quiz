package com.khatthaphone.asean;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewSQLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_sqlite);


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
}
