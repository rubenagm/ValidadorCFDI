package com.rubencfdi.validadorcfdi.Librerias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ruben on 17/10/2017
 */

public class Fecha {

    public static String fechaDiaSemana(String fecha) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(fecha));
        } catch (ParseException e) {
            return null;
        }

        String formatoFecha =
                Fecha.obtenerNombreDiaSemana(calendar.get(Calendar.DAY_OF_WEEK))
                + " "
                + calendar.get(Calendar.DAY_OF_MONTH)
                + " "
                + Fecha.obtenerNombreMes(calendar.get(Calendar.MONTH))
                ;

        if (Calendar.getInstance().get(Calendar.YEAR) > calendar.get(Calendar.YEAR))
            formatoFecha += " " + calendar.get(Calendar.YEAR);

        formatoFecha += " " + fecha.split(" ")[1].toString();

        return formatoFecha;
    }

    public static String obtenerNombreDiaSemana(int dia)
    {
        switch (dia)
        {
            case 1 :
                return "Domingo";
            case 2 :
                return "Lunes";
            case 3 :
                return "Martes";
            case 4 :
                return "Miércoles";
            case 5 :
                return "Jueves";
            case 6 :
                return "Viernes";
            case 7 :
                return "Sábado";
            default:
                return "";
        }
    }

    public static String obtenerNombreMes(int mes)
    {
        switch (mes)
        {
            case 0 :
                return "Enero";
            case 1 :
                return "Febrero";
            case 2 :
                return "Marzo";
            case 3 :
                return "Abril";
            case 4 :
                return "Mayo";
            case 5 :
                return "Junio";
            case 6 :
                return "Julio";
            case 7 :
                return "Agosto";
            case 8 :
                return "Septiembre";
            case 9 :
                return "Octubre";
            case 10 :
                return "Noviembre";
            case 11 :
                return "Diciembre";
            default:
                return "";
        }
    }
}
