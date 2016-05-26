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
//        QuizObject quizObject = new QuizObject();
        quiz.open();
        String data = quiz.getData();
//        quizObject = quiz.getData(quizObject);

        TextView tv = new TextView(this);
/*        tv.setText(
                quizObject.getQuestion() + "\n" + " - " + quizObject.getChoice1() + "\n"  + " - " + quizObject.getChoice2() + "\n" + " - " +  quizObject.getChoice3() + "\n" + " - " +  quizObject.getChoice4()
                        + " - " +  quizObject.getAnswer() + "\n"
        );*/
        tv.setText(data);
        setContentView(tv);

        quiz.close();

    }
}
