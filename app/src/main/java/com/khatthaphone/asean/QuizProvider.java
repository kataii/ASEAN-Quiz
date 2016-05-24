package com.khatthaphone.asean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ACER on 5/18/2016.
 */
public class QuizProvider {

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
    private final Context context;
    private SQLiteDatabase mDb;
    private DatabaseHelper dbHelper;
    private String quiz = "";

    public QuizProvider(Context context) {
        this.context = context;
    }

    public QuizProvider open() {
        dbHelper = new DatabaseHelper(context);
        mDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public String getData() {
        String[] columns = new String[]{COLUMN_ROWID, COLUMN_QUESTION, COLUMN_ANSWER, COLUMN_CHOICE1, COLUMN_CHOICE2, COLUMN_CHOICE3, COLUMN_CHOICE4};
        Cursor c = mDb.query(DATABASE_TABLE, columns, null, null, null, null, null);

        int iRow = c.getColumnIndex(COLUMN_ROWID);
        int iQustion = c.getColumnIndex(COLUMN_QUESTION);
        int iAnswer = c.getColumnIndex(COLUMN_ANSWER);
        int iChoice1 = c.getColumnIndex(COLUMN_CHOICE1);
        int iChoice2 = c.getColumnIndex(COLUMN_CHOICE2);
        int iChoice3 = c.getColumnIndex(COLUMN_CHOICE3);
        int iChoice4 = c.getColumnIndex(COLUMN_CHOICE4);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            quiz = quiz + c.getString(iRow) + ". " + c.getString(iQustion) + "\n" + " - " + c.getString(iChoice1) + "\n" + " - " + c.getString(iChoice2) + "\n" + " - " + c.getString(iChoice3) + "\n" + " - " + c.getString(iChoice4) + "\n" + " (" + c.getString(iAnswer) + ")" + "\n";
        }

        return quiz;
    }

    public long createEntry(String question, String answer, String choice1, String choice2, String choice3, String choice4) {

        ContentValues values = new ContentValues();
        values.put(QuizProvider.COLUMN_QUESTION, question);
        values.put(QuizProvider.COLUMN_CHOICE1, answer);
        values.put(QuizProvider.COLUMN_CHOICE2, choice1);
        values.put(QuizProvider.COLUMN_CHOICE3, choice2);
        values.put(QuizProvider.COLUMN_CHOICE4, choice3);
        values.put(QuizProvider.COLUMN_ANSWER, choice4);

        return mDb.insert(DATABASE_TABLE, null, values);
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


