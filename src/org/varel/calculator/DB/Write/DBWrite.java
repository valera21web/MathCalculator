package org.varel.calculator.DB.Write;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.varel.calculator.DB.DB;

public class DBWrite extends DB {
	private SQLiteDatabase db;

	public DBWrite(Context context) {
		super(context);
		db = this.getWritableDatabase();
	}
	
	/**
	 * 
	 * @param Table -> type String, name table for INSERT
	 * @param ContValues -> type ContentValues, Object with all values for insert Database
	 */
	public long Insert(String Table, ContentValues ContValues) {
        return db.insert(Table, null, ContValues);
	}

	/**
	 * 
	 * @param Table -> type String, name table for INSERT
	 * @param NameCells -> type String[], array name cells of table
	 * @param ValueCells -> type String[], array value for insert in table
	 */
	public long Insert(String Table, String[] NameCells, String[] ValueCells) {
		ContentValues ContValues = new ContentValues();
		int count = NameCells.length;
		for(int i = 0; i < count; ++i) {
			ContValues.put(NameCells[i], ValueCells[i]);
		}
			
        return db.insert(Table, null, ContValues);
	}

	/**
	 * 
	 * @param Table -> type String, name table for INSERT
	 * @param NameCells -> type String[], array name cells of table
	 * @param ValueCells -> type String[], array value for insert in table
	 */
	public long Insert(String Table, String[] NameCells, int[] ValueCells){
		ContentValues ContValues = new ContentValues();
		int count = NameCells.length;
		for(int i = 0; i < count; ++i) {
			ContValues.put(NameCells[i], ValueCells[i]);
		}
		return db.insert(Table, null, ContValues);
	}

	/**
	 * 
	 * @param Table -> type String, name table for INSERT
	 * @param NameCells -> type String, name cell of table
	 * @param ValueCells -> type String, value for insert in table
	 */
	public long Insert(String Table, String NameCells, String ValueCells){
		ContentValues ContValues = new ContentValues();
		ContValues.put(NameCells, ValueCells);
        return db.insert(Table, null, ContValues);
	}
	/**
	 * 
	 * @param Table -> type String, name table for INSERT
	 * @param NameCells -> type String, name cell of table
	 * @param ValueCells -> type INT, value for insert in table
	 */
	public long Insert(String Table, String NameCells, int ValueCells){
		ContentValues ContValues = new ContentValues();
		ContValues.put(NameCells, ValueCells);
        return db.insert(Table, null, ContValues);
	}
	
	
	/**
	 * 
	 * @param Table -> type String, name table for UPDATE
	 * @param NameCell -> type String, array name cell of table
	 * @param oldCellValue -> type String, old value of cell
	 * @param WhereOperand -> type String, example['==','>=','<=','!=']
	 * @param ContValues -> type ContentValues, Object value for Update
	 */
	public boolean Update(String Table, String NameCell, String oldCellValue, String WhereOperand, ContentValues ContValues) {
		return db.update(Table, ContValues, NameCell + " " + WhereOperand + " " + oldCellValue, null) > 0;
	}

	/**
	 * 
	 * @param Table -> type String, name table for UPDATE
	 * @param NameCell -> type String, array name cell of table
	 * @param newValueCell -> type String, new value of cell
	 * @param oldCellValue -> type String, old value of cell
	 * @param WhereOperand -> type String, example['==','>=','<=','!=']
	 */
	public boolean Update(String Table, String NameCell, String newValueCell, String oldCellValue, String WhereOperand) {
		ContentValues ContValues = new ContentValues();
		ContValues.put(NameCell, newValueCell);
		return db.update(Table, ContValues, NameCell + " " + WhereOperand + " " + oldCellValue, null) > 0;
	}

	/**
	 * 
	 * @param Table -> type String, name table for UPDATE
	 * @param NameCell -> type String, array name cell of table
	 * @param newValueCell -> type INT, new value of cell
	 * @param oldCellValue -> type INT, old value of cell
	 * @param WhereOperand -> type String, example['==','>=','<=','!=']
	 */
	public boolean Update(String Table, String NameCell, int newValueCell, int oldCellValue, String WhereOperand) {
		ContentValues ContValues = new ContentValues();
		ContValues.put(NameCell, newValueCell);
		return db.update(Table, ContValues, NameCell + " " + WhereOperand + " " + oldCellValue, null) > 0;
	}
	
	/**
	 * 
	 * @param Table -> type String, name table for DELETE
	 */
	public boolean delete(String Table) {
        return db.delete(Table, null, null) > 0;
   }


   /**
    *
    * @param Table -> type String, name table for DELETE
    * @param WhereCellName -> type String, name cell where search [CellName=?]
    * @param WhatSearch -> type int, value cell where search
    */
   public boolean delete(String Table, String WhereCellName, int WhatSearch) {
      String[] newWhatSearch = new String[]{Integer.toString(WhatSearch)};
      return this.delete(Table, WhereCellName, newWhatSearch);
   }

   /**
    *
    * @param Table -> type String, name table for DELETE
    * @param WhereCellName -> type String, name cell where search [CellName=?]
    * @param WhatSearch -> type String, value cell where search
    */
   public boolean delete(String Table, String WhereCellName, String WhatSearch) {
      return db.delete(Table, WhereCellName, new String[]{WhatSearch}) > 0;
   }

   /**
    *
    * @param Table -> type String, name table for DELETE
    * @param WhereCellName -> type String, name cell where search [CellName=?]
    * @param WhatSearch -> type String[], values cell where search
    */
   public boolean delete(String Table, String WhereCellName, String[] WhatSearch) {
      return db.delete(Table, WhereCellName, WhatSearch) > 0;
   }

	public void Query(String query) {
		db.execSQL(query);
	}
	
	/**
	 * Close connect DB for write
	 */
	public void Close() {
		db.close();
	}
}
