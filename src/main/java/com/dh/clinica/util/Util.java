package com.dh.clinica.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

public class Util { // Métodos de soluciones rápidas, por ejemplo, de conversión de tipos de datos

    public static Timestamp dateToTimestamp(Date date) { // Casos en que vamos a usar: Ingresa un usuarie, agregamos odontologue, se registra un turno
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    // Convierte un Date en un SQL Date
    public static java.sql.Date utilDateToSqlDate(Date utilDate){
        long timeInMilliSeconds = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
        return sqlDate;
    }

    public static java.sql.Time utilLocalTimeToSqlTime(LocalTime utilTime) {
        //long lnMilisegundos = utilTime.getTime();
        java.sql.Time sqlTime = Time.valueOf(utilTime)/*new java.sql.Time(lnMilisegundos)*/;
        return sqlTime;
    }

    public static LocalTime utilTimeToLocalTime(Time utilTime) {
        // Get SQL Time instance
        java.sql.Time sqlTime = new Time(utilTime.getTime());
        // Get LocalTime from SQL time
        LocalTime localTime = sqlTime.toLocalTime();
        System.out.println( localTime ); // 14:00:33
        return localTime;
    }
}
