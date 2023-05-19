package blogProject;

public class Date {
private int year;
private int month;
private int day;

public Date(String date){
	//date = MM/DD/YY
	String monthString=date.substring(0,date.indexOf("/"));
	this.month=Integer.parseInt(monthString);
	date=date.substring(date.indexOf("/")+1); //DD/YYYY 
	
	String dayString=date.substring(0,date.indexOf("/"));
	this.day=Integer.parseInt(dayString);
	
	String yearString=date.substring(date.indexOf("/")+1); //YYYY
	this.year=Integer.parseInt(yearString);
}
public int getYear(){
	return this.year;
}
public int getMonth(){
	return this.month;
}
public int getDay(){
	return this.day;
}
public String toString(){
	return this.month+"/"+this.day+"/"+this.year;
}
}
