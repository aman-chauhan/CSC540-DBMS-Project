/**
 * 
 */
package com.gradience.login;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

import com.gradience.database.CheckUserObject;
import com.gradience.database.LoginObject;
import com.gradience.database.SignUpObject;
import com.gradience.user.Assistant;
import com.gradience.user.Professor;
import com.gradience.user.Student;

/**
 * @author achauhan
 *
 */
public class Login {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Login newlogin = new Login();
		newlogin.header("Welcome to Gradience");
		int choice = newlogin.choice();
		switch (choice) {
		case 1:
			HashMap<String, String> session = newlogin.askCredentials();
			System.out.println("\n\n");
			if(session.get("type").equals("professor")) {
				Professor user=new Professor();
				user.execute(session);
			} else if(session.get("type").equals("assistant")) {
				Assistant user=new Assistant();
				user.execute(session);
			} else if(session.get("type").equals("student")) {
				Student user=new Student();
				user.execute(session);
			}
			break;
		case 2:
			boolean success = newlogin.registerUser();
			if (!success) {
				System.out.println("Sorry! Cannot create user.");
			}
			System.out.println("\n\n");
			main(null);
			break;
		case 3:
			System.out.println("Thank You for using Gradience.");
			System.exit(0);
			break;
		}
	}

	private boolean registerUser() {
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String fname = null;
		String lname = null;
		String userid = null;
		String password = null;
		String college = null;
		String utype = null;
		Date extra = null;
		System.out.println("\n\n");
		header("SignUp for Gradience");
		do {
			System.out.print("Enter First Name -> ");
			fname = sc.next();
			System.out.print("Enter Last Name -> ");
			lname = sc.next();
			boolean ucheck = false;
			do {
				System.out.print("Enter username -> ");
				userid = sc.next();
				CheckUserObject obj = new CheckUserObject();
				HashMap<String, String> response = obj.execute(userid);
				if (response.get("MSG").equals("success") && response.get("IFEXIST").equals("true")) {
					ucheck = false;
				} else if (response.get("MSG").equals("success")) {
					System.out.println("ALERT! : username already exists in the system. Choose another.");
					ucheck = true;
				} else {
					System.out.println("Error on server. Returning to Login Page.");
					return false;
				}
			} while (ucheck);
			System.out.print("Enter password -> ");
			password = sc.next();
			System.out.print("Enter college -> ");
			college = sc.next();
			do {
				System.out.print("Enter 1 for Professor, 2 for Student -> ");
				int tmp = sc.nextInt();
				if (tmp == 1) {
					utype = "professor";
					System.out.print("Enter the date since you have joined as yyyy-mm-dd-> ");
					String temp = sc.next();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						extra = new Date((sdf.parse(temp)).getTime());
						break;
					} catch (Exception e) {
						System.out.println("in catch");
						e.printStackTrace();
						break;
					}
				} else if (tmp == 2) {
					System.out.print("Select 1 for Bachelors, 2 for Masters -> ");
					int temp = sc.nextInt();
					if (temp == 1) {
						utype = "bachelor";
					} else {
						utype = "masters";
					}
					break;
				} else {
					System.out.println("Please enter a valid choice.");
				}
			} while (true);

			SignUpObject obj = new SignUpObject();
			HashMap<String, String> response = obj.execute(fname, lname, userid, password, college, utype, extra);
			if (response.get("MSG").equals("success")) {
				check = false;
				System.out.println(response.get("TEXT"));
			} else {
				check = true;
				System.out.println(response.get("TEXT"));
				System.out.println("Error on Server. Returning to Login Page.");
				return false;
			}
		} while (check);

		return true;
	}

	private HashMap<String, String> askCredentials() {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String user = null;
		String password = null;
		System.out.println("\n\n");
		header("Login to Gradience");
		do {
			System.out.print("Enter username -> ");
			user = sc.next();
			System.out.print("Enter password -> ");
			password = sc.next();
			LoginObject obj = new LoginObject();
			HashMap<String, String> response = obj.execute(user, password);
			if (response.get("MSG").equals("success")) {
				check = false;
				String temp = response.get("TEXT");
				map.put("type", temp.split(":")[0]);
				map.put("username", temp.split(":")[1]);
				map.put("fname", temp.split(":")[2]);
				map.put("lname", temp.split(":")[3]);
			} else {
				check = true;
				System.out.println(response.get("TEXT"));
			}
		} while (check);
		return map;
	}

	private int choice() {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Login");
			System.out.println("2. Sign Up");
			System.out.println("3. Exit");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice == 1 || choice == 2 || choice == 3) {
				check = false;
			} else {
				check = true;
				System.out.println("Please enter a valid choice.\n");
			}
		} while (check);
		return choice;
	}

	private void header(String message) {
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < 80; ++i) {
			sb1.append("*");
		}
		StringBuffer sb2 = new StringBuffer();
		for (int i = 0; i < 80; ++i) {
			if (i == 0 || i == 79) {
				sb2.append("*");
			} else {
				sb2.append(" ");
			}
		}
		StringBuffer sb3 = new StringBuffer();
		sb3.append("*");
		for (int i = 1; i < Math.floor((80 - message.length()) / 2.0); ++i) {
			sb3.append(" ");
		}
		sb3.append(message);
		for (int i = 1; i < Math.ceil((80 - message.length()) / 2.0); ++i) {
			sb3.append(" ");
		}
		sb3.append("*");

		System.out.println(sb1.toString());
		System.out.println(sb2.toString());
		System.out.println(sb3.toString());
		System.out.println(sb2.toString());
		System.out.println(sb1.toString());
	}

}
