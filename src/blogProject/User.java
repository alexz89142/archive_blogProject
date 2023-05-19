package blogProject;

public class User {
private String user;
private String password;

public User(){
	this.user="none";
	this.password="none";
}
public User(String user, String password){
	setUserName(user); 
	setPassword(password);
}

public boolean setUserName(String userName){
	this.user=userName;
	return true;
}
public String getUserName(){
	return this.user;
}
public boolean setPassword(String password){
	this.password=password;
	return true;
}

public String encryptPassword(String password){
	String encryptedPassword="";
	for(int x=0;x<password.length();x++){
		int currentCharacter;
		currentCharacter=(int) password.charAt(x);
		encryptedPassword+=currentCharacter;
	}
	return encryptedPassword;
}

public boolean passwordEquals(String other){
	return encryptPassword(other).equals(this.password);
}

public String toString(){
	String newString=this.user+", "+this.password;
	return newString;
}
}
