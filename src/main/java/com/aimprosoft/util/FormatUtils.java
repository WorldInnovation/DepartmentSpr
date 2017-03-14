package com.aimprosoft.util;

import com.mysql.cj.core.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FormatUtils {
    private static String datePattern = "yyyy.MM.dd";
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


    public static Object stringToValue(String text) throws Exception {

        return dateFormatter.parseObject(text);
    }


    public String valueToString(Object value) throws Exception {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }
    //__________________________
    public static Integer getIntFromStr(String stringValue){
        Integer value = null;
        if(!StringUtils.isEmptyOrWhitespaceOnly(stringValue)){
            try {
                value = Integer.valueOf(stringValue);
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }

    public static Long getLongFromStr(String stringVal){
        Long value = null;
        if(!StringUtils.isEmptyOrWhitespaceOnly(stringVal)) {
            try {
                value = Long.valueOf(stringVal);
            } catch (NumberFormatException ignored) {
            }
        }
        return  value;

    }
/*
* */
    public static Date getDateFromString(String stringValue){

        String  FORMAT_DATE = "yyyy-MM-dd";
       // Date newDate = null;
        if (stringValue.equals("")) return null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        try {

         Date newDate =  simpleDateFormat.parse(stringValue);
            return newDate;
        } catch (ParseException ignored) {
            return null;
        }


    }
}

/*    public static String getIntFromStr(Integer intValue){
        String value = null;
        if(!StringUtils.isEmptyOrWhitespaceOnly(intValue)){
            try {
                value = String.valueOf(intValue);
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }*/