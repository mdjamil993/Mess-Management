package com.example.juber.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Juber on 1/12/2018.
 */

public class MessDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mess_database.db";
    public static final String TABLE_NAME = "mess_table";
    // public static final String ID = "ID";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "MOBILE";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURENAME";
    public static final String COL_4 = "DEPOSITE";
    public static final String COL_5 = "MEAL";
    public static final String COL_6 = "TotalMEAL";
    public static final String COL_7 = "TotalEXPENSE";


    public MessDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, MOBILE TEXT, NAME TEXT,SURENAME TEXT ,DEPOSITE INTEGER,MEAL INTEGER,TotalMEAL INTEGER,TotalEXPENSE DOUBLE)");
         // db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        // onCreate(db);
    }

    public int insertData(String mobile,String name, String surname, String deposite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,mobile);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,deposite);
        contentValues.put(COL_5,0);
        contentValues.put(COL_6,0);
        contentValues.put(COL_7,0.00);
        db.insert(TABLE_NAME,null,contentValues);

        return 5;

    }

    public int getDeposite(String surname){

        int amount=0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor res = db.query(TABLE_NAME, new String[] { COL_4 }, COL_3 + "=?",
                    new String[] { surname }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        amount = res.getInt(res.getColumnIndex("DEPOSITE"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  amount;
    }

    public int getMeal(String surname){

        int amount=0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor res = db.query(TABLE_NAME, new String[] { COL_5}, COL_3 + "=?",
                    new String[] { surname }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        amount = res.getInt(res.getColumnIndex("MEAL"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  amount;
    }

    public String getName(String surname){

        String amount = "" ;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor res = db.query(TABLE_NAME, new String[] { COL_2}, COL_3 + "=?",
                    new String[] { surname }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        amount = res.getString(res.getColumnIndex("NAME"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  amount;
    }
    //--------------------get number ------------------------------------------------------
    public String getNumber(String surname){

        String Mobile = "" ;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            Cursor res = db.query(TABLE_NAME, new String[] { COL_1}, COL_3 + "=?",
                    new String[] { surname }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        Mobile = res.getString(res.getColumnIndex("MOBILE"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  Mobile;
    }

    public Cursor getAllData(String surname){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_2+" like '%" +surname+ "%' ",null);
        return res;
    }


    public int getAllMeal(int id){

        int amount=0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            String ID = String.valueOf(id);

            Cursor res = db.query(TABLE_NAME, new String[] { COL_6}, COL_0 + "=?",
                    new String[] { ID }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        amount = res.getInt(res.getColumnIndex("TotalMEAL"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  amount;
    }

    public double getExpense(int id){

        double amount=0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();

            String ID = String.valueOf(id);

            Cursor res = db.query(TABLE_NAME, new String[] { COL_7}, COL_0 + "=?",
                    new String[] { ID }, null, null, null, null);

            if (res != null ) {
                if (res.moveToFirst()) {
                    do {
                        amount = res.getInt(res.getColumnIndex("TotalEXPENSE"));
                    }while (res.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

        return  amount;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String surname, String ammount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int a=getDeposite(surname);
        int res=Integer.parseInt(ammount);
       int fin=res+a;
     //   ammount=String.valueOf(res);
       // contentValues.put(COL_1,surname);
        //contentValues.put(COL_2,name);
        //contentValues.put(COL_3,surname);
       // contentValues.put(COL_4,ammount);
        contentValues.put(COL_4,fin);
        db.update(TABLE_NAME,contentValues,"SURENAME = ?",new String[]{surname});
        return true;
    }

    public void updateMeal(String surname, int meal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int a=getMeal(surname);
        int fin=meal+a;
     //   ammount=String.valueOf(res);
       // contentValues.put(COL_1,surname);
        //contentValues.put(COL_2,name);
        //contentValues.put(COL_3,surname);
       // contentValues.put(COL_4,ammount);
        contentValues.put(COL_5,fin);
        db.update(TABLE_NAME,contentValues,"SURENAME = ?",new String[]{surname});
    }
    //----------------send message ---------------------------------------------------------
    public void sendMessage(String surname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String a=getNumber(surname);
        //int fin=meal+a;
        //   ammount=String.valueOf(res);
        // contentValues.put(COL_1,surname);
        //contentValues.put(COL_2,name);
        //contentValues.put(COL_3,surname);
        // contentValues.put(COL_4,ammount);
        //contentValues.put(COL_5,fin);
        db.update(TABLE_NAME,contentValues,"SURENAME = ?",new String[]{surname});
    }

    public void updateAllMeal(int meal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int a=getAllMeal(1);
        int fin=meal+a;
     //   ammount=String.valueOf(res);
       // contentValues.put(COL_1,surname);
        //contentValues.put(COL_2,name);
        //contentValues.put(COL_3,surname);
       // contentValues.put(COL_4,ammount);
        contentValues.put(COL_6,fin);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    public void updateExpense(double expense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        double a=getExpense(1);
        double fin=expense+a;
     //   ammount=String.valueOf(res);
       // contentValues.put(COL_1,surname);
        //contentValues.put(COL_2,name);
        //contentValues.put(COL_3,surname);
       // contentValues.put(COL_4,ammount);
        contentValues.put(COL_7,fin);
        db.update(TABLE_NAME,contentValues,null,null);
    }

    //-----------------------serachi data query---------------------------------------


    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public long getCount() {
      //  String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
       /* Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();*/

        long cnt  = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return cnt;
    }
}

