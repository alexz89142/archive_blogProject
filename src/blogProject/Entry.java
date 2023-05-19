package blogProject;

public class Entry {
	private static int currentID=0;
	
	private String id;
	private String title;
	private String firstName;
	private String lastName;
	private DateTime time;
	private String content;
	
	public Entry(){
		this.id="-1";
		this.title="none";
		this.firstName="none";
		this.lastName="none";
		this.time=null;
		this.content="none";
	}
	public Entry(String id, String title, String authorFirst, String authorLast, DateTime time, String content){
		this.id=id;
		
		currentID+=1;
		
		this.title=title;
		
		this.firstName=authorFirst;
		
		this.lastName=authorLast;
		
		this.content=content;
		
		this.time=time;
	}
	public static String getCurrentID(){
		return currentID+"";
	}
	
	public boolean setID(String id){
		this.id=id;
		return true;
	}
	public boolean setTitle(String title){
		this.title=title;
		return true;
	}
	public String getTitle(){
		return this.title;
	}
	
	public boolean setFirstName(String fname){
		this.firstName=fname;
		return true;
	}
	public boolean setLastName(String lname){
		this.lastName=lname;
		return true;
	}
	public String getLastName(){
		return this.lastName;
	}
	
	public boolean setTime(DateTime time){
		this.time=time;
		return true;
	}
	public boolean setContent(String content){
		this.content=content;
		return true;
	}
	public DateTime getTime(){
		return this.time;
	}
	
	public String toString(){
		return(id+":::"+title+":::"+firstName+":::"+lastName+":::"+time+":::"+content);
	}
	public String toHTML(){
		return "<div><span style='font-weight:bold; text-decoration:underline; font-size:12pt'>"+title+"</span><span style='font-style:italic; font-size:11p'>, by "+firstName+" "+lastName+" </span><span style='font-size:9pt'>("+time.getFormattedString()+")</span></br>"+content+"</div><br>";
	}
	
	public boolean titleComesAfter(Entry other){
		return isHigherAscii(this.title,other.getTitle());
	}
	public boolean authorNameComesAfter(Entry other){
		return isHigherAscii(this.lastName,other.getLastName());
	}
	public boolean isHigherAscii(String firstString, String lastString){
		firstString=firstString.toLowerCase();
		lastString=lastString.toLowerCase();
		
		int shortestLength=firstString.length();
		
		if(firstString.length()>lastString.length()){
			shortestLength=lastString.length();
		}
		
		for(int x=0;x<shortestLength;x++){
			int fString=(int) firstString.charAt(x);
			int lString=(int) lastString.charAt(x);
			
			if(fString>lString){
				return true;
			}
			else if(fString<lString){
				return false;
			}
		}
		
		if(firstString.equals(lastString)){
			return false;
		}
		return true;
	}
}
