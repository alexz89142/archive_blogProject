package blogProject;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTime {
private Time time;
private Date date;

public DateTime(){
	String localTime=LocalTime.now().toString();
	this.time=new Time(localTime.substring(0,localTime.indexOf(".")-3));
	this.date=new Date(currentDateWithFormat());
}
public DateTime(String date, String time){
	this.date=new Date(date);
	this.time=new Time(time);
}

private String currentDateWithFormat(){
	String localDate=LocalDate.now()+"";
	String date="";
	
	String year=localDate.substring(0,localDate.indexOf("-"));
	String month=localDate.substring(year.length()+1, year.length()+3)+"/";
	String day=localDate.substring(localDate.length()-2)+"/";
	
	
	date=month+day+year;
	
	return date; 
}
private Time getTime(){
	return this.time;
}
public Date getDate(){
	return this.date;
}

public boolean isOlderThan(DateTime other){
	if(this.date.getDay()>other.getDate().getDay()){
		 if(this.date.getMonth()>other.getDate().getMonth()){
			if(this.date.getYear()>other.getDate().getYear()){
				return true;
			}
		 }
	}
	if(this.date.getDay()==other.getDate().getDay()){
		if(this.date.getMonth()==other.getDate().getMonth()){
			if(this.date.getYear()==other.getDate().getYear()){
				if(this.time.getHours()>other.getTime().getHours()){
					if(this.time.getMins()>other.getTime().getMins()){
						return true;
					}
				}
			}
		}
	}
	return false;
}

public String toString(){
	return this.date+":::"+this.time;
}

public String getFormattedString(){
	String dateTime=date+", "+time.formatString();
	return dateTime;
}
}
