package com.rubencfdi.validadorcfdi.BaseDatos;

/**
 * Created by Ruben on 20/05/2017
 */

public class Querys {

    public static final String TAG_INSERTAR = "BD/INSERT";

    public static final String CREATETABLE_TIMBRE =
            "CREATE TABLE Timbre (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, UUID TEXT, rfcEmisor TEXT,  rfcReceptor TEXT, Monto String, Estatus INTEGER, Mensaje TEXT, FechaVerificacion DATETIME, Estado TEXT, CadenaQR TEXT)";

    public static final String SELECT_TIMBRE =
            "SELECT * FROM Timbre ORDER BY FechaVerificacion DESC";
}
