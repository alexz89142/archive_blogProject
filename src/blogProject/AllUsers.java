package blogProject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AllUsers {
private ArrayList<User> users=new ArrayList<User>();

public AllUsers() throws IOException{
	loadAccounts();
}

public ArrayList<User> getUsers(){
	return users;
}
private void loadAccounts() throws IOException {
	Scanner reader=new Scanner(new File("accounts.txt"));;
	
	while(reader.hasNext()){
		String input=reader.nextLine();
		Scanner lineReader=new Scanner(input);
		lineReader.useDelimiter(",");
		User newAccount= new User();
		newAccount.setUserName(lineReader.next());
		newAccount.setPassword(lineReader.next());
		
		users.add(newAccount);
	}
}

public int indexOfUser(String user){
	for(int x=0;x<users.size();x++){
		if(user.equals(users.get(x).getUserName())){
			return x;
		}
	}
	return -1;
}
public boolean doesUserExist(String user){
	if(indexOfUser(user)==-1){
		return false;
	}
	else{
		return true;
	}
}

public void addUser(User newUser, String password) throws IOException{
	try(FileWriter fw = new FileWriter("savedAccounts.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw)){
		    out.println(newUser.getUserName()+","+newUser.encryptPassword(password));
		} 
		catch (IOException e) {
			
		}
	loadAccounts();
}
}
