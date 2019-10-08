package com.example.split_it;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_PRODUCTCOST = "productcost";



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT " + "," +
                COLUMN_PRODUCTCOST + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }


    //Add a new row to the database
    public void addProduct(com.example.split_it.Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        values.put(COLUMN_PRODUCTCOST, product.get_productcost());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }


    //  TODO: figure ot a more user friendly way to delete products
    //
    //
    //Delete a product from the database

    public void deleteProduct(String productName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }


    //get the total sum of the Product_Cost column
    //
    //  TODO: fix this method to display total
    //
    //
    // Goes into the database and sums the total of all the products

    public int GetTotal() {
        int temp;
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT SUM(" + COLUMN_PRODUCTCOST + ") FROM " + TABLE_PRODUCTS + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            temp = cursor.getInt(0);
        } else return 0;

        cursor.close();


        return temp;
    }

    // NOTE: this is the code ive gotten from stack overflow.
    //
    //  reference:
    //              https://stackoverflow.com/questions/2810615/how-to-retrieve-data-from-cursor-class
    //
    //
    //
    // Converts the elements in the database to a string so we can print the database out.

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor will point to a columns location
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("productname")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("productname"));
                dbString += "\t $";
                dbString += recordSet.getString(recordSet.getColumnIndex("productcost"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}

