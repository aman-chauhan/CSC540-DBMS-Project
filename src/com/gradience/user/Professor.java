package com.gradience.user;

import java.util.HashMap;
import java.util.Scanner;

import com.gradience.login.Login;

public class Professor {

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

	public void execute(HashMap<String, String> session) {
		header("Welcome to Gradience, " + session.get("username"));
		int choice = choice();
		switch (choice) {
		case 1:
			System.out.println("\n\n");
			view_edit_profile(session);
			break;
		case 2:
			System.out.println("\n\n");
			view_add_course(session);
			break;
		case 3:
			System.out.println("\n\n");
			enroll_drop_student(session);
			break;
		case 4:
			System.out.println("\n\n");
			search_add_question(session);
			break;
		case 5:
			System.out.println("\n\n");
			logout(session);
			break;
		}
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

	private void view_profile(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void edit_profile(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void view_add_course(HashMap<String, String> session) {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. View Course");
			System.out.println("2. Edit Course");
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
			view_course(session);
			break;
		case 2:
			System.out.println("\n\n");
			add_course(session);
			break;
		}
	}

	private void view_course(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void add_course(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void enroll_drop_student(HashMap<String, String> session) {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Enroll Student");
			System.out.println("2. Drop Student");
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
			enroll_student(session);
			break;
		case 2:
			System.out.println("\n\n");
			drop_student(session);
			break;
		}
	}

	private void drop_student(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void enroll_student(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void search_add_question(HashMap<String, String> session) {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Search Question");
			System.out.println("2. Add Question");
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
			search_question(session);
			break;
		case 2:
			System.out.println("\n\n");
			add_question(session);
			break;
		}
	}

	private void add_question(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void search_question(HashMap<String, String> session) {
		// TODO Auto-generated method stub
		
	}

	private void logout(HashMap<String, String> session) {
		for (String key : session.keySet()) {
			session.remove(key);
		}
		Login.main(null);
	}

	private int choice() {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. View/Edit Profile");
			System.out.println("2. View/Add Courses");
			System.out.println("3. Enroll/Drop Student");
			System.out.println("4. Search/Add Question to Bank");
			System.out.println("5. Logout");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice > 0 || choice < 6) {
				check = false;
			} else {
				check = true;
				System.out.println("Please enter a valid choice.\n");
			}
		} while (check);
		return choice;
	}

}