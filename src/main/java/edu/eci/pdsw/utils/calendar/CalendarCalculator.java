/*
 * Copyright (C) 2015 Juanito
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.utils.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Juanito, el programador bisoño.
 */
public class CalendarCalculator {
    
    
    /**
     * Obj: Calcular la fecha correspondiente a los N dias posteriores
     * a la fecha dada
     * @param year anyo de la fecha de inicio
     * @param month mes de la fecha de inicio
     * @param day dia de la fecha de inicio
     * @param n numero de dias a sumar
     * @return la fecha resultante en formato Date
     * @throws InvalidDateException si los valores ingresados no corresponden a
     *        una fecha válida.
     * 
     */
    public SimpleDate add(int year, int month, int day,int n) throws InvalidDateException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");        
        sdf.setLenient(false);
	
        boolean fechaInvalida=false;
        Throwable excepcionBase=null; 
        String dateStr=year + "/" + month + "/" + day;
        
        //revisar casos adicionales al bisiesto
        if (month==2 && year%2!=0 && day==29){
                try {
                //if not valid, it will throw ParseException
                Date date = sdf.parse(dateStr);
            } catch (ParseException e) {
                fechaInvalida=true;
                excepcionBase=e;
            }            
        }
        
        if (fechaInvalida){
            throw new InvalidDateException("La fecha "+dateStr+" es inválida.",excepcionBase);
        }
        
        Calendar c=new GregorianCalendar(year, month, day);
        
        //si hay salto de un anyo a otro
        if (365-c.get(Calendar.DAY_OF_YEAR)>n){
            year++;
            c.set(Calendar.YEAR, year);
            c.add(Calendar.DAY_OF_MONTH, n);
        }
        else{
            c.add(Calendar.DAY_OF_MONTH, n);         
        }
        
        return new SimpleDate(c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.YEAR));
        
    }
    
}
