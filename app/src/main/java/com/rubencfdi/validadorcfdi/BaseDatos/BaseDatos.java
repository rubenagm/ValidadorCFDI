package com.rubencfdi.validadorcfdi.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rubencfdi.validadorcfdi.Modelos.Timbre;

import java.util.ArrayList;

/**
 * Created by Ruben on 20/05/2017
 */

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, "VALIDADORCFDI", null, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREATETABLE_TIMBRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertarTimbre(Timbre timbre)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("UUID", timbre.getUuid());
        contentValues.put("rfcEmisor", timbre.getRfcEmisor());
        contentValues.put("rfcReceptor", timbre.getRfcReceptor());
        contentValues.put("Monto", timbre.getMonto());
        contentValues.put("Estatus", timbre.getEstatus());
        contentValues.put("Mensaje", timbre.getMensaje());
        contentValues.put("FechaVerificacion", timbre.getFechaVerificacion());

        long id = sqLiteDatabase.insert("Timbre", null, contentValues);
        Log.i(Querys.TAG_INSERTAR, "Se insertado un timbre");

        return id;
    }

    public ArrayList<Timbre> consultarTimbres()
    {
        ArrayList<Timbre> timbres = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Querys.SELECT_TIMBRE, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            timbres.add(new Timbre(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("UUID")),
                    cursor.getString(cursor.getColumnIndex("rfcEmisor")),
                    cursor.getString(cursor.getColumnIndex("rfcReceptor")),
                    cursor.getString(cursor.getColumnIndex("Monto")),
                    cursor.getInt(cursor.getColumnIndex("Estatus")),
                    cursor.getString(cursor.getColumnIndex("Mensaje")),
                    cursor.getString(cursor.getColumnIndex("FechaVerificacion"))
            ));
        }

        return timbres;
    }
}
