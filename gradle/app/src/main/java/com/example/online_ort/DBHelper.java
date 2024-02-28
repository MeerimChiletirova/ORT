package com.example.online_ort;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String QUESTIONS_TABLE = "QUESTIONS_TABLE";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_QUESTION = "COLUMN_QUESTION";
    private static final String COLUMN_CORRECT_OPTION = "COLUMN_CORRECT_OPTION";
    private static final String COLUMN_OPTION_1 = "COLUMN_OPTION1";
    private static final String COLUMN_OPTION_2 = "COLUMN_OPTION2";
    private static final String COLUMN_OPTION_3 = "COLUMN_OPTION3";
    private static final String COLUMN_OPTION_4 = "COLUMN_OPTION4";
    private static final String COLUMN_OPTION_5 = "COLUMN_OPTION5";

    public DBHelper(@Nullable Context context) {
        super(context, "online_ort.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + QUESTIONS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_CORRECT_OPTION + " TEXT, " +
                COLUMN_OPTION_1 + " TEXT, " +
                COLUMN_OPTION_2 + " TEXT, " +
                COLUMN_OPTION_3 + " TEXT, " +
                COLUMN_OPTION_4 + " TEXT, " +
                COLUMN_OPTION_5 + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        onCreate(db);
    }

    public void AddQuestion(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, data.question);
        cv.put(COLUMN_CORRECT_OPTION, data.correct_option);
        cv.put(COLUMN_OPTION_1, data.option_1);
        cv.put(COLUMN_OPTION_2, data.option_2);
        cv.put(COLUMN_OPTION_3, data.option_3);
        cv.put(COLUMN_OPTION_4, data.option_4);
        cv.put(COLUMN_OPTION_5, data.option_5);

        long result = db.insert(QUESTIONS_TABLE, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Ошибка при записи в БД", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Успешно добавлено", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE, null);

        }
        return cursor;
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + QUESTIONS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    Cursor readSearchData(String question) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE + " WHERE " + COLUMN_QUESTION + " LIKE '%" + question + "%'", null);

        }
        return cursor;
    }

    Cursor readOneData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery("SELECT * FROM " + QUESTIONS_TABLE + " WHERE " + COLUMN_ID + "=" + id, null);

        }
        return cursor;
    }

    void updateDate(String row_id, String name, String correct_option, String option_1, String option_2, String option_3, String option_4, String option_5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, name);
        cv.put(COLUMN_CORRECT_OPTION, correct_option);
        cv.put(COLUMN_OPTION_1, option_1);
        cv.put(COLUMN_OPTION_2, option_2);
        cv.put(COLUMN_OPTION_3, option_3);
        cv.put(COLUMN_OPTION_4, option_4);
        cv.put(COLUMN_OPTION_5, option_5);

        long result = db.update(QUESTIONS_TABLE, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка при обновлении!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Успешно обновлено!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(QUESTIONS_TABLE, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка при удалении!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Успешно удалено!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + QUESTIONS_TABLE);
    }
}
