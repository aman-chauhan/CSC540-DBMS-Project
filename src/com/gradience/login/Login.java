/**
 * 
 */
package com.gradience.login;

import java.util.HashMap;
import java.util.Scanner;

import com.gradience.database.LoginObject;

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
			HashMap<String, String> user = newlogin.askCredentials();
			System.out.println(user.toString());
			break;
		case 2:
			break;
		case 3:
			System.out.println("Thank You for using Gradience.");
			System.exit(0);
			break;
		}
	}

	private HashMap<String, String> askCredentials() {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String user=null;
		String password=null;
		System.out.println("\n\n");
		header("Login to Gradience");
		do {
			System.out.print("Enter username -> ");
			user=sc.next();
			System.out.print("Enter password -> ");
			password=sc.next();
			LoginObject obj=new LoginObject();
			HashMap<String,String> response=obj.execute(user,password);
			if(response.get("MSG").equals("success")) {
				check=false;
				String temp=response.get("TEXT");
				map.put("type", temp.split(":")[0]);
				map.put("username", temp.split(":")[1]);
				map.put("fname",temp.split(":")[2]);
				map.put("lname", temp.split(":")[3]);
			} else {
				check=true;
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
		for (int i = 1; i < (80 - message.length()) / 2; ++i) {
			sb3.append(" ");
		}
		sb3.append(message);
		for (int i = 1; i < (80 - message.length()) / 2; ++i) {
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
