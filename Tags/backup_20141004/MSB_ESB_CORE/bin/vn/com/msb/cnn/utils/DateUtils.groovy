package vn.com.msb.cnn.utils

import grails.orm.PagedResultList;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

class DateUtils {
	
	
	/**
	 * convert from 30/06/1999 to 300699
	 * @return
	 */
	public static String convertDate1(String date) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateDraftFormat=new SimpleDateFormat("ddMMyy");
		Date temp=dateFormat.parse(date);
		return dateDraftFormat.format(temp);
	}
	
	/**
	 * convert from 300699 to 30/06/1999 
	 * @return
	 */
	public static String convertDate2(String date) {        
		SimpleDateFormat dateDraftFormat=new SimpleDateFormat("ddMMyy");
		Date temp=dateDraftFormat.parse(date);
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(temp);
	}
	/**
	 * convert from 30/06/1999 to 30061999
	 * @return
	 */
	public static String convertDate1_Full(String date) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateFullFormat=new SimpleDateFormat("ddMMyyyy");
		Date temp=dateFormat.parse(date);
		return dateFullFormat.format(temp);
	}
	public static subString(str){
		if (str == null) return ""
		int len = str.length()
		if (len > 20) return str.substring(0,20)+"..."
		else return str
	}
	public static subString(length,str){
		if (str == null) return ""
		int len = str.length()
		if (len > length) return str.substring(0,length)+"..."
		else return str
	}
	
	
	public static getDateOfMonth(){
		return Calendar.instance.get(Calendar.DAY_OF_MONTH)
	}
	
	public static getYear(){
		return Calendar.instance.get(Calendar.YEAR)
	}
	
	public static getMonth(){
		return Calendar.instance.get(Calendar.MONTH) + 1
	}
	public static Date parseDate(String time) throws ParseException {
		return (new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss")).parse(time);
	}
	
	public static String dateToYYMMDD(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMdd");
		return dateFormat.format(date);
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
    }

    public static String timeToString(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
		return dateFormat.format(date);
    }
		
	public static String dateTimeToString(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public static String seqDateTime(Date date){
		SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyyyyHHmmssSSS");
		return dateFormat.format(date);
	}

}
