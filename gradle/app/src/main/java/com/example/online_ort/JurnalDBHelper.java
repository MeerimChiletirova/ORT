package com.example.online_ort;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class JurnalDBHelper extends SQLiteOpenHelper {
    private Context context;
    private  static final String JURNAL_TABLE = "JURNAL_TABLE";
    private  static final String JURNAL_ID = "_id";
    private  static final String JURNAL_CORRECT = "JURNAL_CORRECT";
    private  static final String JURNAL_COUNT = "JURNAL_COUNT";
    private  static final String JURNAL_COUNT_MAX = "JURNAL_COUNT_MAX";
    private  static final String JURNAL_COUNT_QUESTION = "JURNAL_COUNT_QUESTION";
    private  static final String JURNAL_TIME = "JURNAL_TIME";

    public JurnalDBHelper(@Nullable Context context) {
        super(context, "jurnal.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + JURNAL_TABLE + " (" + JURNAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JURNAL_CORRECT + " TEXT, " +
                JURNAL_COUNT + " TEXT, " +
                JURNAL_COUNT_MAX + " TEXT, " +
                JURNAL_COUNT_QUESTION + " TEXT, " +
                JURNAL_TIME + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void AddListInJurnal(String correct, int count, int count_max, int count_question, String times) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(JURNAL_CORRECT, correct);
        cv.put(JURNAL_COUNT, count);
        cv.put(JURNAL_COUNT_MAX, count_max);
        cv.put(JURNAL_COUNT_QUESTION, count_question);
        cv.put(JURNAL_TIME, times);

        long result = db.insert(JURNAL_TABLE, null, cv);
        if (result == -1) {
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
            cursor = db.rawQuery("SELECT * FROM " + JURNAL_TABLE, null);

        }
        return cursor;
    }
}
