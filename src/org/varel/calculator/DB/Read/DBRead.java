package org.varel.calculator.DB.Read;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.varel.calculator.DB.DB;
import org.varel.calculator.V;

public class DBRead extends DB{
	
	private SQLiteDatabase db;

	public DBRead(Context context) {
		super(context);
		db = this.getReadableDatabase();
	}
	
	public List<ContentValues> Query(String table, String[] columns, String[] TypeColumns, String selection
            , String[] selectionArgs, String groupBy, String having, String orderBy) {

		Cursor c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		List<ContentValues> Result = new ArrayList<ContentValues>();
		int count_columns = TypeColumns.length;
		c.moveToFirst();
	   do {
	    	ContentValues TMP = new ContentValues();
	      if (c.getCount() > 0) {
	        	for(int i = 0; i < count_columns; ++i){
	        		int column_index = c.getColumnIndex(columns[i]);
               if(TypeColumns[i] == V.TYPE_STRING) {
                  TMP.put(columns[i], c.getString(column_index));
               } else if(TypeColumns[i] == V.TYPE_BLOB) {
                  TMP.put(columns[i], c.getBlob(column_index));
               } else if(TypeColumns[i] == V.TYPE_DOUBLE) {
                  TMP.put(columns[i], c.getDouble(column_index));
               } else if(TypeColumns[i] == V.TYPE_FLOAT) {
                  TMP.put(columns[i], c.getFloat(column_index));
               } else if(TypeColumns[i] == V.TYPE_INT) {
                  TMP.put(columns[i], c.getInt(column_index));
               } else if(TypeColumns[i] == V.TYPE_LONG) {
                  TMP.put(columns[i], c.getLong(column_index));
               }
	        	}
	      }
		   Result.add(TMP);
	   } while (c.moveToNext());
      return Result;
	}

	public List<ContentValues> Query(String select, String[] columns, String[] TypeColumns) {
      List<ContentValues> Result = new ArrayList<ContentValues>();
      Cursor c = db.rawQuery(select, null);
		int count_columns = TypeColumns.length;
		c.moveToFirst();
	   do {
	    	ContentValues TMP = new ContentValues();
	      if (c.getCount() > 0) {
	        	for(int i = 0; i < count_columns; ++i){
	        		int column_index = c.getColumnIndex(columns[i]);
	        		if(TypeColumns[i] == V.TYPE_STRING) {
	        			TMP.put(columns[i], c.getString(column_index));
	        		} else if(TypeColumns[i] == V.TYPE_BLOB) {
	        			TMP.put(columns[i], c.getBlob(column_index));
	        		} else if(TypeColumns[i] == V.TYPE_DOUBLE) {
	        			TMP.put(columns[i], c.getDouble(column_index));
	        		} else if(TypeColumns[i] == V.TYPE_FLOAT) {
	        			TMP.put(columns[i], c.getFloat(column_index));
	        		} else if(TypeColumns[i] == V.TYPE_INT) {
	        			TMP.put(columns[i], c.getInt(column_index));
	        		} else if(TypeColumns[i] == V.TYPE_LONG) {
	        			TMP.put(columns[i], c.getLong(column_index));
	        		}
	        	}
	      }
		   Result.add(TMP);
	   } while (c.moveToNext());
      return Result;
	}
	
	public void Close(){
		db.close();
	}
	
}
