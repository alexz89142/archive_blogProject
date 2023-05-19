package blogProject;

public class Time {
private int hours;
private int mins;

public Time(String time){
	String hour=time.substring(0,time.indexOf(":")); // Gets before :
	this.hours=Integer.parseInt(hour);
	
	String min=time.substring(time.indexOf(":")+1); // Gets after :
	this.mins=Integer.parseInt(min);
}
public int getHours(){
	return this.hours;
}
public int getMins(){
	return this.mins;
}

public String toString(){
	return this.hours+":"+this.mins;
}
public String formatString(){
	int hour=this.hours;
	String min=this.mins+"";
	if(min.length()==1){
		min="0"+min;
	}
	boolean pm=false;
	if(hour>12){
		hour-=12;
		pm=true;
	}
	if(hour==0){
		hour=1;
	}
	
	if(pm==true){
		return hour+":"+min+"pm";
	}
	else{
		return hour+":"+min+"am";
	}
}
}
