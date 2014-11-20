package org.varel.calculator.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.varel.calculator.DB.Write.DBWrite;

public abstract class DB extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "VarelaCalculator";
	
	public static final String ID = "_id";
	public static final String MATH_STR = "math_str";
	public static final String MATH_ANS = "ans_str";
	public static final String MATH_CREATE = "created";

	public static final String TABLE_NAME_MATH_HISTORY = "ans_history";
	public static final String TABLE_NAME_LAST_MATH_STRING = "last_math_string";
	private String query;
	
	
	public DB(Context context) {
      super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
   public void onCreate(SQLiteDatabase sqLiteDatabase) {
	   query = "CREATE TABLE " + TABLE_NAME_MATH_HISTORY + "( " +
          ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
          "" + MATH_STR + " TEXT, " +
          "" + MATH_ANS + " TEXT," +
          "" + MATH_CREATE + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
          ")";
	   sqLiteDatabase.execSQL(query);

      query = "CREATE TABLE " + TABLE_NAME_LAST_MATH_STRING + "( " +
              ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
              "" + MATH_STR + " TEXT, " +
              "" + MATH_CREATE + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
              ")";
      sqLiteDatabase.execSQL(query);
   }

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Тут можно организовать миграцию данных из старой базы в новую
		// или просто "выбросить" таблицу и создать заново
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MATH_HISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LAST_MATH_STRING);
		onCreate(db);
	}
}
