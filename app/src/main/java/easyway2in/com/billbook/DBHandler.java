package easyway2in.com.billbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sameer1 on 09-08-2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "BillBook";

    private static final String TABLE_RECORDS = "Records";
    private static final String TABLE_CREDIT = "CreditDetails";
    private static final String TABLE_DEBIT = "DebitDetails";
    private static final String TABLE_Final = "FinalDetails";
    private static final String TABLE_MISC = "MiscDetails";

    private static final String KEY_NAME = "name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";
    private static final String KEY_PROFESSION = "profession";

    private static final String KEY_CREDIT = "Credit";
    private static final String KEY_DEBIT = "Debit";

    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_BALANCE = "balance";

    private static final String KEY_STARTDATE = "StartDate";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORDS_TABLE = "CREATE TABLE " + TABLE_RECORDS + "("
                + KEY_NAME + " TEXT UNIQUE PRIMARY KEY,"
                + KEY_DOB + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASS + " TEXT,"
                + KEY_PROFESSION + " TEXT" + ")";

        db.execSQL(CREATE_RECORDS_TABLE);

        String CREATE_CREDIT_TABLE = "CREATE TABLE " + TABLE_CREDIT + "("
                + KEY_CREDIT + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT" + ")";

        Log.d("Query",CREATE_CREDIT_TABLE);
        db.execSQL(CREATE_CREDIT_TABLE);

        String CREATE_DEBIT_TABLE = "CREATE TABLE " + TABLE_DEBIT + "("
                + KEY_DEBIT + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT" + ")";

        db.execSQL(CREATE_DEBIT_TABLE);

        String CREATE_FINAL_TABLE = "CREATE TABLE " + TABLE_Final + "("
                + KEY_AMOUNT + " TEXT,"
                + KEY_BALANCE + " TEXT" + ")";


        db.execSQL(CREATE_FINAL_TABLE);

        String CREATE_MISC_TABLE = "CREATE TABLE " + TABLE_MISC + "("
                + KEY_STARTDATE + " TEXT" + ")";
        db.execSQL(CREATE_MISC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEBIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Final);
        onCreate(db);
    }


    public void addStartDate(String StartDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STARTDATE, StartDate);

        db.insert(TABLE_MISC, null, values);
        db.close();
    }

    public void addCredit(String credit, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CREDIT, credit);
        values.put(KEY_DATE, date);
        values.put(KEY_TIME, time);

        db.insert(TABLE_CREDIT, null, values);
        db.close();
    }

    public void addAmount(String amount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_BALANCE, amount);
        db.insert(TABLE_Final, null, values);
        db.close();
    }


    public void addDebit(String debit, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DEBIT, debit);
        values.put(KEY_DATE, date);
        values.put(KEY_TIME, time);

        db.insert(TABLE_DEBIT, null, values);
        db.close();
    }

    public void addRecords(String name, String dob, String email, String pass,String profession) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_DOB, dob);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASS, pass);
        values.put(KEY_PROFESSION, profession);

        db.insert(TABLE_RECORDS, null, values);
        db.close();
    }



    public void updateBalance(String Amount,String Balance){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_AMOUNT, Amount);
        values.put(KEY_BALANCE, Balance);

        db.insert(TABLE_Final, null, values);
        db.close();
    }


    public int getRecordsRowCount() {

        String countQuery = "SELECT  * FROM " + TABLE_RECORDS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        cursor.close();
        db.close();

        return rowCount;
    }

    public int getCreditRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CREDIT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        cursor.close();
        db.close();

        return rowCount;
    }
    public int getDebitRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DEBIT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        cursor.close();
        db.close();

        return rowCount;
    }

    public String[][] getCredit() {
        String data [][] ;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_CREDIT;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        data = new String[3][count];
        int i = 0;
        while(count > 0) {
            data[0][i] = cursor.getString(0);
            data[1][i] = cursor.getString(1);
            data[2][i] = cursor.getString(2);
           i++;
            cursor.moveToNext();
            count--;
        }
        cursor.close();
        db.close();
        return data;
    }
    public String[][] getDebit() {
        String data [][] ;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_DEBIT;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        data = new String[3][count];
        int i = 0;
        while(count > 0) {
            data[0][i] = cursor.getString(0);
            data[1][i] = cursor.getString(1);
            data[2][i] = cursor.getString(2);
            i++;
            cursor.moveToNext();
            count--;
        }
        cursor.close();
        db.close();
        return data;
    }
    public String[][] getLoginDetails() {
        String data [][] ;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_RECORDS;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        data = new String[2][count];

        while(count > 0) {
            data[0][0] = cursor.getString(2);
            data[1][0] = cursor.getString(3);
            cursor.moveToNext();
            count--;
        }
        cursor.close();
        db.close();
        return data;
    }

    public String getBalance(){
        SQLiteDatabase db = this.getReadableDatabase();

        String Balance = new String();

        String query = "SELECT * FROM "+ TABLE_Final;
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        int count= cursor.getCount();
        while(count>0) {
            Balance = cursor.getString(1);
            count--;
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return Balance;
    }

    public String getAmount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String Amount = new String();

        String query = "SELECT * FROM "+ TABLE_Final;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count= cursor.getCount();
        while(count>0) {
            Amount = cursor.getString(0);
            count--;
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return Amount;
    }


    public String getCreditAmount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String c;
        Float temp = Float.valueOf(0),t;
        String query = "SELECT * FROM "+ TABLE_CREDIT;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        while(count>0){

            t = Float.valueOf(cursor.getString(0));
            temp = temp + t;
            count--;
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        c = String.valueOf(temp);

        return c;
    }
    public String getDebitAmount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String c;
        Float temp = Float.valueOf(0),t;
        String query = "SELECT * FROM "+ TABLE_DEBIT;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        while(count>0){
            t =  Float.valueOf(cursor.getString(0));
            temp = temp + t;
            count--;
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        c = String.valueOf(temp);

        return c;
    }

    public String getStartDate(){
        SQLiteDatabase db = this.getReadableDatabase();

        String date = new String();

        String query = "SELECT * FROM "+ TABLE_MISC;
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        int count= cursor.getCount();
        if (count>0) {
            date = cursor.getString(0);
            }
        cursor.close();
        db.close();
        return date;
    }

    public void deleteLastCreditRecord(String time){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CREDIT, KEY_TIME + " = '" + time + "'", null);
        db.close();

    }

    public String getLastCreditTime(){
        SQLiteDatabase db = this.getReadableDatabase();
        String c = null;

        String query = "SELECT * FROM "+ TABLE_CREDIT;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        while(count>0){
            c = cursor.getString(2);
            count--;
            cursor.moveToNext();
        }
        cursor.close();
        db.close();


        return c;
    }
    public void deleteLastDebitRecord(String time){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DEBIT, KEY_TIME + " = '" + time + "'", null);
        db.close();

    }

    public String getLastDebitTime(){
        SQLiteDatabase db = this.getReadableDatabase();
        String c = null;

        String query = "SELECT * FROM "+ TABLE_DEBIT;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getCount();
        while(count>0){
            c = cursor.getString(2);
            count--;
            cursor.moveToNext();
        }
        cursor.close();
        db.close();


        return c;
    }




    public void resetTable_Credit(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CREDIT, null, null);
        db.close();
    }

    public void resetTable_Debit() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DEBIT, null, null);
        db.close();
    }
    public void resetTable_final() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_Final, null, null);
        db.close();
    }
    public void resetTable_Records() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RECORDS, null, null);
        db.close();
    }
    public void resetTable_Misc() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MISC, null, null);
        db.close();
    }




}
