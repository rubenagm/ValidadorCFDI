package com.rubencfdi.validadorcfdi.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rubencfdi.validadorcfdi.Modelos.Timbre;

import java.util.ArrayList;

/**
 * Created by Ruben on 20/05/2017
 */

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, "VALIDADORCFDI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREATETABLE_TIMBRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertarTimbre(Timbre timbre) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues   = new ContentValues();

        contentValues.put("UUID", timbre.getUuid());
        contentValues.put("rfcEmisor", timbre.getRfcEmisor());
        contentValues.put("rfcReceptor", timbre.getRfcReceptor());
        contentValues.put("Monto", timbre.getMonto());
        contentValues.put("Estatus", timbre.getEstatus());
        contentValues.put("Mensaje", timbre.getMensaje());
        contentValues.put("FechaVerificacion", timbre.getFechaVerificacion());
        contentValues.put("Estado", timbre.getEstado());
        contentValues.put("CadenaQR", timbre.getCadenaQR());

        long id = sqLiteDatabase.insert("Timbre", null, contentValues);

        return id;
    }

    public ArrayList<Timbre> consultarTimbres() {
        ArrayList<Timbre> timbres     = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor                 = sqLiteDatabase.rawQuery(Querys.SELECT_TIMBRE_ORDER, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            timbres.add(new Timbre(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("UUID")),
                    cursor.getString(cursor.getColumnIndex("rfcEmisor")),
                    cursor.getString(cursor.getColumnIndex("rfcReceptor")),
                    cursor.getString(cursor.getColumnIndex("Monto")),
                    cursor.getInt(cursor.getColumnIndex("Estatus")),
                    cursor.getString(cursor.getColumnIndex("Mensaje")),
                    cursor.getString(cursor.getColumnIndex("FechaVerificacion")),
                    cursor.getString(cursor.getColumnIndex("Estado")),
                    cursor.getString(cursor.getColumnIndex("CadenaQR"))));

            cursor.moveToNext();
        }
        cursor.close();

        return timbres;
    }

    public Timbre consultarTimbre(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Querys.SELECT_TIMBRE + " WHERE Id = " + id, null);
        cursor.moveToFirst();
        Timbre timbre = new Timbre(
                cursor.getInt(cursor.getColumnIndex("Id")),
                cursor.getString(cursor.getColumnIndex("UUID")),
                cursor.getString(cursor.getColumnIndex("rfcEmisor")),
                cursor.getString(cursor.getColumnIndex("rfcReceptor")),
                cursor.getString(cursor.getColumnIndex("Monto")),
                cursor.getInt(cursor.getColumnIndex("Estatus")),
                cursor.getString(cursor.getColumnIndex("Mensaje")),
                cursor.getString(cursor.getColumnIndex("FechaVerificacion")),
                cursor.getString(cursor.getColumnIndex("Estado")),
                cursor.getString(cursor.getColumnIndex("CadenaQR")));

        cursor.close();

        return timbre;
    }

    public void borrarTimbre(Timbre timbre) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Timbre", "Id = " + timbre.getId(), null);
        sqLiteDatabase.close();
    }

    public boolean existeTimbre(Timbre timbre) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        boolean bandera = false;

        Cursor cursor = sqLiteDatabase.rawQuery(Querys.SELECT_TIMBRE + " WHERE CadenaQr = '" + timbre.getCadenaQR() + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0)
            bandera = true;

        cursor.close();

        return bandera;
    }

    public void actualizarTimbre(Timbre timbre) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues   = new ContentValues();

        contentValues.put("UUID", timbre.getUuid());
        contentValues.put("rfcEmisor", timbre.getRfcEmisor());
        contentValues.put("rfcReceptor", timbre.getRfcReceptor());
        contentValues.put("Monto", timbre.getMonto());
        contentValues.put("Estatus", timbre.getEstatus());
        contentValues.put("Mensaje", timbre.getMensaje());
        contentValues.put("FechaVerificacion", timbre.getFechaVerificacion());
        contentValues.put("Estado", timbre.getEstado());
        contentValues.put("CadenaQR", timbre.getCadenaQR());

        sqLiteDatabase.update("Timbre", contentValues, "Id = " + timbre.getId(), null);
    }
}
