package com.khatthaphone.asean;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewSQLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sqlite);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.NoMenuToolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        final QuizProvider quiz = new QuizProvider(this);
//        QuizObject quizObject = new QuizObject();
        quiz.open();
        String data = quiz.getData();
//        quizObject = quiz.getData(quizObject);
        TextView tv = (TextView) findViewById(R.id.tvViewSQLite);
/*        tv.setText(
                quizObject.getQuestion() + "\n" + " - " + quizObject.getChoice1() + "\n"  + " - " + quizObject.getChoice2() + "\n" + " - " +  quizObject.getChoice3() + "\n" + " - " +  quizObject.getChoice4()
                        + " - " +  quizObject.getAnswer() + "\n"
        );*/
        tv.setText(data);

        quiz.close();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
