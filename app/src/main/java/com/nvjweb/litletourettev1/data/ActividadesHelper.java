package com.nvjweb.litletourettev1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ActividadesHelper  extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "touretteDb2.db";


        public ActividadesHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + ActividadesContract.ActividadesEntry.TABLE_NAME + " ("
                    +  ActividadesContract.ActividadesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +  ActividadesContract.ActividadesEntry.IDUSUARIO + " TEXT NOT NULL,"
                    +  ActividadesContract.ActividadesEntry.ACIERTOS + " TEXT NOT NULL,"
                    +  ActividadesContract.ActividadesEntry.ERRORES + " TEXT NOT NULL" +
                    ","
                    + "UNIQUE (" +ActividadesContract.ActividadesEntry._ID + "))");

            //mockLawyer(sqLiteDatabase,producto);
        }


        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }


