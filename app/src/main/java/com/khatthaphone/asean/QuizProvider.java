package com.khatthaphone.asean;

import android.content.ContentProvider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ACER on 5/18/2016.
 */
public abstract class QuizProvider extends ContentProvider {

    // Database Columns
    public static final String COLUMN_ROWID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_CHOICE1 = "choice1";
    public static final String COLUMN_CHOICE2 = "choice2";
    public static final String COLUMN_CHOICE3 = "choice3";
    public static final String COLUMN_CHOICE4 = "choice4";
    public static final String COLUMN_ANSWER = "answer";
    // Database's Constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "quiz_multi";
    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + " (" + COLUMN_ROWID + " integer primary key autoincrement, "
            + COLUMN_QUESTION + " text not null, "
            + COLUMN_ANSWER + " text not null, "
            + COLUMN_CHOICE1 + " text not null, "
            + COLUMN_CHOICE2 + " text not null, "
            + COLUMN_CHOICE3 + " text not null, "
            + COLUMN_CHOICE4 + " text not null);";

    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mDb = new DatabaseHelper(getContext()).getWritableDatabase();
        return true;
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            throw new UnsupportedOperationException();
        }
    }

}


