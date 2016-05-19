package com.khatthaphone.asean;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by ACER on 5/18/2016.
 */
public class QuizProvider extends ContentProvider {


    // Database Columns
    public static final String COLUMN_ROWID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_CHOICE1 = "choice1";
    public static final String COLUMN_CHOICE2 = "choice2";
    public static final String COLUMN_CHOICE3 = "choice3";
    public static final String COLUMN_CHOICE4 = "choice4";
    public static final String COLUMN_ANSWER = "answer";
    //MIME types used for searching words or looking u a single definition
    public static final String QUIZS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.khatthaphone.asean.quiz_multi";
    public static final String QUIZ_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.com.khatthaphone.asean.quiz_multi";
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
    // UriMatcher stuff
    private static final int LIST_QUIZ = 0;
    private static final int ITEM_QUIZ = 1;
    //Content Provider Uri and Authority
    public static String AUTHORITY = "com.khatthaphone.asean,QuizProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/quiz_multi");
    private static final UriMatcher sURIMatcher = buildUriMathcer();
    private SQLiteDatabase mDb;

    /*
        Build up a UriMatcher for search suggesstion and shortcut refresh queries.
        */
    private static UriMatcher buildUriMathcer() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "quiz_multi", LIST_QUIZ);
        matcher.addURI(AUTHORITY, "quiz_multi/#", ITEM_QUIZ);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDb = new DatabaseHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    /*    This method is required in order to query the supported types. It's also useful in the query() method to determine the type of Uri recieved.
        */
    @Override
    public String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case LIST_QUIZ:
                return QUIZS_MIME_TYPE;
            case ITEM_QUIZ:
                return QUIZ_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
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


