package com.example.sriharsha.salesassistant;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SalesRepDatabaseHandler extends SQLiteOpenHelper {

    private static final int  DATABASE_VERSION=1;
    private static final String  DATABASE_NAME= "salesRepManager";

    //SalesRepDetails Table AND keys
    private static final String TABLE_SALESREP="salesrep";
    private static final String KEY_FIRSTNAME="firstname";
    private static final String KEY_SECONDNAME="secondname";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PHONE="phone";
    private static final String KEY_ADDRESS="address";

    //SalesRepWork Table AND keys
    private static final String TABLE_SALESREP_WORK="salesrepworkprofile";
    private static final String KEY_SALESREPID="salesrepid";//Column -1
    private static final String KEY_SALESTARGET="salestargetamount";//Column -2

    private static final String KEY_MONTHASSIGNED="targetassignedmonth";//Column -3
    private static final String KEY_YEARASSIGNED="targetassignedyear";//Column -4
    private static final String KEY_SALESACHIEVED="salesachievedamount";//Column -5

    public SalesRepDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE="CREATE TABLE " + TABLE_SALESREP + " ("
                + "_id" + " INTEGER PRIMARY KEY," + KEY_FIRSTNAME +" TEXT,"
                + KEY_SECONDNAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PHONE + " TEXT," + KEY_ADDRESS + " TEXT" + ")";

        String CREATE_SALESREPWORK_TABLE="CREATE TABLE " + TABLE_SALESREP_WORK + " ("
                + "_id" + " INTEGER PRIMARY KEY," + KEY_SALESREPID + " INTEGER,"
                + KEY_SALESTARGET + " REAL," + KEY_MONTHASSIGNED + " INTEGER," + KEY_YEARASSIGNED + " INTEGER," + KEY_SALESACHIEVED +" REAL" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_SALESREPWORK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SALESREP);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SALESREP_WORK);
        onCreate(db);
    }

    public void addSalesRep(SalesRepModel salesRep){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(KEY_FIRSTNAME,salesRep.get_firstname());
        values.put(KEY_SECONDNAME,salesRep.get_secondname());
        values.put(KEY_EMAIL,salesRep.get_email());
        values.put(KEY_PHONE,salesRep.get_phonenumber());
        values.put(KEY_ADDRESS,salesRep.get_address());

        db.insert(TABLE_SALESREP,null,values);
        db.close();
    }

    SalesRepModel getSalesRep(int _id){
        SQLiteDatabase db= this.getReadableDatabase();
        //Cursor cursor=db.query()

        return null;
    }

    public List<String> getAllRepsFirstName(){

        List<String> saleRepFirstName = new ArrayList<String>();

        String selectQuery="SELECT * FROM " + TABLE_SALESREP;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                String firstName=cursor.getString(1);
                saleRepFirstName.add(firstName);
            }while(cursor.moveToNext());

        }

        return saleRepFirstName;
    }

    public int getSalesRepCount(){

        String countQuery= "SELECT * FROM " + TABLE_SALESREP;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return  cursor.getCount();
    }

    public void addWorkSalesRep(SalesRepWorkModel salesRepWork){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv= new ContentValues();

        cv.put(KEY_SALESREPID,salesRepWork.getSalesRepId());
        cv.put(KEY_SALESTARGET,salesRepWork.getSalesTargetAmount());
        cv.put(KEY_MONTHASSIGNED,salesRepWork.getTargetMonth());
        cv.put(KEY_YEARASSIGNED,salesRepWork.getTargetYear());
        cv.put(KEY_SALESACHIEVED,salesRepWork.getsalesAchievedAmount());

        db.insert(TABLE_SALESREP_WORK,null,cv);
        db.close();

    }
}
