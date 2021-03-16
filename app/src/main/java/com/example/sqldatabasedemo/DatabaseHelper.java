package com.example.sqldatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating Table
        String CreateTable= "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + ACTIVE_CUSTOMER + " BOOL) ";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(CustomerModel customerModel)
    {
        SQLiteDatabase db=this.getWritableDatabase(); //getting Database
        //Putting values
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE,customerModel.getAge());
        cv.put(ACTIVE_CUSTOMER,customerModel.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        db.close();
        if(insert==-1)
            return false;
        return true;
    }
    public List<CustomerModel> getAll()
    {
        //getting data from database
        List<CustomerModel> datalist=new ArrayList<>();
        String query="SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                int customer_id=cursor.getInt(0);
                String customer_name=cursor.getString(1);
                int age=cursor.getInt(2);
                boolean is_active=cursor.getInt(3)==1?true:false;
                CustomerModel new_customer=new CustomerModel(customer_id,customer_name,is_active,age);
                datalist.add(new_customer);
            }
            while(cursor.moveToNext());
        }
        else
        {
            Log.i("Error","No Data Found");
        }
        return datalist;
    }
    public boolean deleteOne(CustomerModel customerModel)
    {
        String query="DELETE FROM "+CUSTOMER_TABLE+" WHERE "+COLUMN_ID+" = "+customerModel.getId();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
