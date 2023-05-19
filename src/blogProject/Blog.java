package blogProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class Blog {
	private AllUsers users;
	private ArrayList<Entry> entries;
	private Scanner reader = new Scanner(System.in);
	private User currentUser;

	public Blog() throws IOException {
		this.users = new AllUsers();
		this.entries=loadEntries();
		logIn();
		choice();
	}

	public AllUsers getUserBase() {
		return this.users;
	}

	public ArrayList<Entry> getEntries() {
		return this.entries;
	}

	private User logIn() {
		String username;
		String password;
		int userIndex;

		System.out.print("Please enter your email: ");
		do {
			username = reader.nextLine();
			if (!users.doesUserExist(username)) {
				System.out.println("That user does not exist");
			}
		} while (!users.doesUserExist(username));
		userIndex = users.indexOfUser(username);

		System.out.print("Password: ");
		password = reader.nextLine();
		if (!checkPassword(password, userIndex)) {
			return null;
		}
		
		this.currentUser=users.getUsers().get(userIndex);
		System.out.println("You are logged in, "+this.currentUser.getUserName());
		return this.currentUser;
	}

	private boolean checkPassword(String passwordAttempt, int index) {
		User foundUser = users.getUsers().get(index);
		if (foundUser.passwordEquals(passwordAttempt)==false) {
			System.out.println("Wrong password");
			return false;
		}
		return true;
	}

	private void choice() throws IOException {
		int input;

		do {
			System.out.println("1 = Read blog");
			System.out.println("2 = Exit");
			input = reader.nextInt();

			if (input==1) {
				readBlog();
				System.out.println("Blog created: blog.html");
			} 
			else if (input == 2){
				return;
			}
		} while (input<1 || input>2);
	}

	public void readBlog() throws IOException {
		loadEntries();
		System.out.println("");
		int choice;

		if (this.entries.size() > 1) {
			do {
				System.out.println("Please choose blog entry order");
				System.out.println("1 = Default");
				System.out.println("2 = Sort by author");
				System.out.println("3 = Sort by title");
				System.out.println("4 = Sort by date");

				choice = reader.nextInt();
				if (choice < 1 || choice > 4) {
					System.out.println("invalid command");
				}
			} while (choice < 1 || choice > 4);
		}

		else{
			choice=1;
		}
		
		createBlog(choice);
	}

	private ArrayList<Entry> loadEntries() throws IOException {
		Scanner fileReader = new Scanner(new File("entries.txt"));
		ArrayList<Entry> loadedEntries = new ArrayList<Entry>();

		while (fileReader.hasNext()) {
			String input = fileReader.nextLine();
			Scanner lineReader = new Scanner(input);
			lineReader.useDelimiter(":::");
			
			Entry newEntry=new Entry();
			newEntry.setID(lineReader.next());
			newEntry.setTitle(lineReader.next());
			newEntry.setFirstName(lineReader.next());
			newEntry.setLastName(lineReader.next());
			
			DateTime newDate=new DateTime(lineReader.next(),lineReader.next());
			
			newEntry.setTime(newDate);
			newEntry.setContent(lineReader.next());
			loadedEntries.add(newEntry);
		}
		return loadedEntries;
	}

	private void createBlog(int choice) throws IOException {
		PrintWriter writer = new PrintWriter("blog.html");
		writer.close();

		try (FileWriter writer2 = new FileWriter("blog.html", true);
				BufferedWriter writer3 = new BufferedWriter(writer2);
				PrintWriter out = new PrintWriter(writer3))
		{
			String htmlStart="<html><head></head><body>";
			out.println(htmlStart);
			
			if(choice == 2){ // author
				for (int x = 0; x < this.entries.size() - 1; x++) {
					if (entries.get(x).authorNameComesAfter(entries.get(x + 1))) {
						entries.add(x + 1, entries.remove(x));
					}
				}
			}
			
			if(choice==3){ // title
				for (int x = this.entries.size() - 1; x>=1; x--) {
					if (entries.get(x).titleComesAfter(entries.get(x - 1))) {
						entries.add(x, entries.remove(x-1));
						x=this.entries.size()-1;
					}
				}
				choice=1; // reverse for correct alphabetical order
			}
			if(choice==1){ // date
				for (int x = 0; x < this.entries.size() - 1; x++) {
					entries.add(x,entries.remove(entries.size()-1));
				}
			}
			
			
			for (int x = 0; x < this.entries.size(); x++){
				out.println(entries.get(x).toHTML());
			}
			
			out.println("</body></html>");
		} 
		catch (IOException e) {
			
		}
	}
}
