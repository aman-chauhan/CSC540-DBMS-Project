package com.gradience.user;

import java.util.HashMap;
import java.util.Scanner;

import com.gradience.login.Login;

public class Student {

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
		for (int i = 1; i < Math.floor(80 - message.length()) / 2; ++i) {
			sb3.append(" ");
		}
		sb3.append(message);
		for (int i = 1; i < Math.ceil(80 - message.length()) / 2; ++i) {
			sb3.append(" ");
		}
		sb3.append("*");

		System.out.println(sb1.toString());
		System.out.println(sb2.toString());
		System.out.println(sb3.toString());
		System.out.println(sb2.toString());
		System.out.println(sb1.toString());
	}

	public void execute(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		header("Welcome to Gradience, " + session.get("username"));
		int choice = choice();

		switch (choice) {
		case 1:
			System.out.println("\n\n");
			view_edit_profile(session);
			break;
		case 2:
			System.out.println("\n\n");
			view_courses(session);
			break;
		case 3:
			System.out.println("\n\n");
			logout(session);
			break;
		default:
			break;
		}
	}

	
	private void view_courses(HashMap<String, String> session) {
		// TODO Auto-generated method stub

	}

	private void logout(HashMap<String, String> session) {
		for (String key : session.keySet()) {
			session.remove(key);
		}
		Login.main(null);
	}

	private void view_edit_profile(HashMap<String, String> session) {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. View Profile");
			System.out.println("2. Edit Profile");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice >= 0 || choice < 3) {
				check = false;
			} else {
				check = true;
				System.out.println("Please enter a valid choice.\n");
			}
		} while (check);

		switch (choice) {
		case 0:
			System.out.println("\n\n");
			execute(session);
			break;
		case 1:
			System.out.println("\n\n");
			view_profile(session);
			break;
		case 2:
			System.out.println("\n\n");
			edit_profile(session);
			break;
		}
	}

	private void edit_profile(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void view_profile(HashMap<String, String> session) {
		header("View Profile, " + session.get("username"));
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("0. Go Back");
		System.out.println("1. First Name: "+session.get("fname"));
		System.out.println("2. Last Name: "+session.get("lname"));
		System.out.println("3. Employee ID: "+session.get("username"));
		System.out.println("Enter Your Choice -> ");
		int choice = sc.nextInt();
		
		if(choice==0){
			view_edit_profile(session);
		}
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
}
