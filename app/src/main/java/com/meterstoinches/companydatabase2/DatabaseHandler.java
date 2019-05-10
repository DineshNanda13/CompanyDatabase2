package com.meterstoinches.companydatabase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int dATABASE_VERSION = 1;
    public static final String database_name = "CompanyDatabase";
    public static final String table_name = "Companies";

    public static final String key_comapnyRef="comapnyRef";
    public static final String key_formalName="formalName";
    public static final String key_companyTypeCode="companyTypeCode";
    public static final String key_mainAdress="mainAdress";
    public static final String key_mainPostcode="mainPostcode";
    public static final String key_receptionNo="receptionNo";
    public static final String key_websiteURL="websiteURL";
    public static final String key_customer="customer";
    public static final String key_supplier="supplier";
    public static final String key_compaynotes="compaynotes";

    String create_table = "create table "+table_name+"("+key_comapnyRef+" Integer primary key,"+key_formalName+" text,"
            +key_companyTypeCode+" text,"+key_mainAdress+" text,"+key_mainPostcode+" text,"+key_receptionNo+" text,"
            +key_websiteURL+" text,"+key_customer+" text,"+key_supplier+" text,"+key_compaynotes+" text"+")";
    String drop_table = "drop table if exists "+table_name;

    public DatabaseHandler(Context context) {
        super(context, database_name, null, dATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_table);
        onCreate(db);
    }

    public void addCompany(String p1,String p2,String p3,String p4,String p5,String p6,String p7,
                           String p8,String p9){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_formalName,p1);
        values.put(key_companyTypeCode,p2);
        values.put(key_mainAdress,p3);
        values.put(key_mainPostcode,p4);
        values.put(key_receptionNo,p5);
        values.put(key_websiteURL,p6);
        values.put(key_customer,p7);
        values.put(key_supplier,p8);
        values.put(key_compaynotes,p9);
        db.insert(table_name,null,values);
        db.close();
    }
    public List<Company> getallCompany(){
        List<Company> companyList = new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        String selectQuery="select * from "+ table_name;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                Company company = new Company();
                company.setComapnyRef(Integer.parseInt(cursor.getString(0)));
                company.setFormalName(cursor.getString(1));
                company.setCompanyTypeCode(cursor.getString(2));
                company.setMainAdress(cursor.getString(3));
                company.setMainPostcode(cursor.getString(4));
                company.setReceptionNo(cursor.getString(5));
                company.setWebsiteURL(cursor.getString(6));
                company.setCustomer(cursor.getString(7));
                company.setSupplier(cursor.getString(8));
                company.setCompaynotes(cursor.getString(9));
                companyList.add(company);
            }while (cursor.moveToNext());
        }
        return companyList;
    }
    public void deleteLastRow(){
        Company company = new Company();
        String selectQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToLast()) {
            company.setComapnyRef(Integer.parseInt(cursor.getString(0)));
        }
        db.execSQL("DELETE FROM " + table_name+ " WHERE "+key_comapnyRef+"='"+company.getComapnyRef()+"'");
        db.close();

    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(drop_table);
        db.execSQL(create_table);
    }
}
