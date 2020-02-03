package com.nvjweb.litletourettev1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioHelper  extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "touretteDb2.db";


    public UsuarioHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UsuarioContract.UsuariosEntry.TABLE_NAME + " ("
                +  UsuarioContract.UsuariosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                +  UsuarioContract.UsuariosEntry.USERNAME + " TEXT NOT NULL,"
                +  UsuarioContract.UsuariosEntry.PASSWORD + " TEXT NOT NULL,"
                +  UsuarioContract.UsuariosEntry.FECHA_NACIMIENTO + " TEXT NOT NULL,"
                +  UsuarioContract.UsuariosEntry.AVATAR + " TEXT NOT NULL" +
                ","

                + "UNIQUE (" +UsuarioContract.UsuariosEntry._ID + "))");

        //mockLawyer(sqLiteDatabase,producto);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
