package com.gradience.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.gradience.database.AttemptObject;
import com.gradience.database.DropObject;
import com.gradience.database.EditUserObject;
import com.gradience.database.EnrolObject;
import com.gradience.database.OpenHomeworksList;
import com.gradience.database.StudentCourseList;
import com.gradience.database.TACourseList;
import com.gradience.login.Login;
import com.gradience.model.AHistory1;
import com.gradience.model.Course;
import com.gradience.model.Exercise;
import com.gradience.model.Options;

public class Assistant {

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
			ta_view_courses(session);
			break;

		case 3:
			System.out.println("\n\n");
			view_courses(session);
			break;
		case 4:
			System.out.println("\n\n");
			logout(session);
			break;

		default:
			break;

		}

	}

	private void ta_view_courses(HashMap<String, String> session) {
		header("TA: View Courses, " + session.get("username"));
		int choice = 1;
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		TACourseList obj = new TACourseList();
		ArrayList<Course> courses = obj.execute(session.get("username"));

		do {
			System.out.println("0. Go Back");
			for (int i = 0; i < courses.size(); ++i) {
				System.out.println(Integer.toString(i + 1) + ". " + courses.get(i).getCourse_id() + "-"
						+ courses.get(i).getCourse_name());
			}
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice < 0 || choice > courses.size()) {
				System.out.println("Please enter a valid choice.\n");
				check = true;
			} else {
				check = false;
			}
		} while (check);

		if (choice == 0) {
			System.out.println("\n\n");
			execute(session);
		} else {
			view_course_specific_ta(session, courses.get(choice - 1));
		}

	}

	private void view_course_specific_ta(HashMap<String, String> session, Course course) {
		header(course.getCourse_id() + " - " + course.getCourse_name() + " , " + session.get("username"));
		int choice = 1;
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Course Code : " + course.getCourse_id());
		System.out.println("Course Name : " + course.getCourse_name());
		System.out.println("Start Date : " + course.getStart_date().toString());
		System.out.println("End Date : " + course.getEnd_date().toString());
		System.out.println();
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Enrol/Drop Student");
			System.out.println("2. View Report");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();
			if (choice < 0 || choice > 4) {
				System.out.println("Please enter a valid choice.\n");
				check = true;
			} else {
				check = false;
			}
		} while (check);

		switch (choice) {
		case 0:
			System.out.println("\n\n");
			ta_view_courses(session);
			break;
		case 1:
			enrol_drop_student(session, course);
			break;
		case 2:
			view_course_report(session, course);
			break;
		}
	}

	private void view_course_report(HashMap<String, String> session, Course course) {
		// TODO Auto-generated method stub
		
	}

	private void enrol_drop_student(HashMap<String, String> session, Course course) {
		header("Enrol/Drop Student for " + course.getCourse_id() + ", " + session.get("username"));
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Enrol Student");
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
			view_course_specific_ta(session, course);
			break;
		case 1:
			System.out.println("\n\n");
			enrol_student(session, course);
			break;
		case 2:
			System.out.println("\n\n");
			drop_student(session, course);
			break;
		}
	}
	
	private void drop_student(HashMap<String, String> session, Course course) {
		header("Drop student for " + course.getCourse_id() + ", " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String temp;
		DropObject obj = new DropObject();
		HashMap<String, String> response = null;
		System.out.println("Enter username of students to drop from the course as a comma separated list -> ");
		temp = sc.nextLine();
		String[] l = temp.split(",");
		for (int i = 0; i < l.length; ++i) {
			response = obj.execute(l[i], course.getCourse_id());
			if (response.get("MSG").equals("success")) {
				System.out.println(response.get("TEXT"));
			} else {
				System.out.println(response.get("TEXT"));
			}
		}
		System.out.println("Press 0 to go back -> ");
		sc.nextInt();
		System.out.println("\n\n");
		enrol_drop_student(session, course);
	}
	
	private void enrol_student(HashMap<String, String> session, Course course) {
		header("Enrol student for " + course.getCourse_id() + ", " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String temp;
		EnrolObject obj = new EnrolObject();
		HashMap<String, String> response = null;
		System.out.println("Enter username of students to enrol for the course as a comma separated list -> ");
		temp = sc.nextLine();
		String[] l = temp.split(",");
		for (int i = 0; i < l.length; ++i) {
			response = obj.execute(l[i], course.getCourse_id());
			if (response.get("MSG").equals("success")) {
				System.out.println(response.get("TEXT"));
			} else {
				System.out.println(response.get("TEXT"));
			}
		}
		System.out.println("Press 0 to go back -> ");
		sc.nextInt();
		System.out.println("\n\n");
		enrol_drop_student(session, course);
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
		for (int i = 1; i < Math.floor((80 - message.length())) / 2; ++i) {
			sb3.append(" ");
		}
		sb3.append(message);
		for (int i = 1; i < Math.ceil((80 - message.length())) / 2; ++i) {
			sb3.append(" ");
		}
		sb3.append("*");

		System.out.println(sb1.toString());
		System.out.println(sb2.toString());
		System.out.println(sb3.toString());
		System.out.println(sb2.toString());
		System.out.println(sb1.toString());
	}

	private int choice() {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. View/Edit Profile");
			System.out.println("2. TA: View Courses");
			System.out.println("3. View Courses");
			System.out.println("4. Log Out");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
				check = false;
			} else {
				check = true;
				System.out.println("Please enter a valid choice.\n");
			}
		} while (check);
		return choice;
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
		header("Edit Profile, " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("0. Go Back");
		System.out.println("1. First Name: " + session.get("fname"));
		System.out.println("2. Last Name: " + session.get("lname"));
		System.out.print("Enter Your Choice -> ");
		int choice = sc.nextInt();
		EditUserObject obj = new EditUserObject();
		HashMap<String, String> response = null;

		switch (choice) {
		case 0:
			System.out.println("\n\n");
			view_edit_profile(session);
			break;
		case 1:
			System.out.print("Enter New First Name");
			String nfname = sc.next();
			response = obj.execute(session.get("username"), "fname", nfname);
			if (response.get("MSG").equals("success")) {
				session.replace("fname", nfname);
			}
			System.out.println(response.get("TEXT"));
			break;
		case 2:
			System.out.print("Enter New Last Name");
			String nlname = sc.next();
			response = obj.execute(session.get("username"), "lname", nlname);
			if (response.get("MSG").equals("success")) {
				session.replace("lname", nlname);
			}
			System.out.println(response.get("TEXT"));
			break;
		}

		System.out.print("Press 0 to go back -> ");
		choice = sc.nextInt();

		if (choice == 0) {
			System.out.println("\n\n");
			view_edit_profile(session);
		}
	}

	private void view_profile(HashMap<String, String> session) {
		header("View Profile, " + session.get("username"));

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("0. Go Back");
		System.out.println("1. First Name: " + session.get("fname"));
		System.out.println("2. Last Name: " + session.get("lname"));
		System.out.println("3. Employee ID: " + session.get("username"));
		System.out.println("Enter Your Choice -> ");
		int choice = sc.nextInt();

		if (choice == 0) {
			view_edit_profile(session);
		}
	}

	private void view_courses(HashMap<String, String> session) {
		header("View Courses, " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		StudentCourseList obj = new StudentCourseList();
		ArrayList<Course> courses = obj.execute(session.get("username"));
		if (courses.size() != 0) {
			System.out.println("Current Courses -> ");
			for (int i = 0; i < courses.size(); ++i) {
				System.out.println(
						(i + 1) + ". " + courses.get(i).getCourse_id() + " - " + courses.get(i).getCourse_name());
				System.out.println("Enter your choice (0 to go back) -> ");
				int temp = sc.nextInt();
				if (temp == 0) {
					System.out.println("\n\n");
					execute(session);
				} else {
					System.out.println("\n\n");
					view_course_specific(session, courses.get(temp - 1));
				}
			}
		} else {
			System.out.print("Press 0 to go back -> ");
			int choice = sc.nextInt();

			if (choice == 0) {
				System.out.println("\n\n");
				view_edit_profile(session);
			}
		}
	}

	private void view_course_specific(HashMap<String, String> session, Course course) {
		header(course.getCourse_id() + " - " + course.getCourse_name() + " , " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Course Code : " + course.getCourse_id());
		System.out.println("Course Name : " + course.getCourse_name());
		System.out.println("Start Date : " + course.getStart_date().toString());
		System.out.println("End Date : " + course.getEnd_date().toString());
		System.out.println();
		int choice = 0;
		boolean check = false;
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Current HWs");
			System.out.println("2. Past HWs");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();
			if (choice < 0 || choice > 2) {
				System.out.println("Please enter a valid choice.\n");
				check = true;
			} else {
				check = false;
			}
		} while (check);

		System.out.println("\n\n");
		switch (choice) {
		case 0:
			view_courses(session);
			break;
		case 1:
			current_hws(session, course);
			break;
		case 2:
			past_hws(session, course);
			break;
		}
	}

	private void past_hws(HashMap<String, String> session, Course course) {

	}

	private void current_hws(HashMap<String, String> session, Course course) {
		header("Current Open Homeworks " + course.getCourse_id() + " , " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		OpenHomeworksList obj = new OpenHomeworksList();
		ArrayList<Exercise> list = obj.execute(course.getCourse_id());
		for (int i = 0; i < list.size(); ++i) {
			int rtleft = obj.execute2(list.get(i).getExercise_id(), session.get("username"));
			System.out.println((i + 1) + ". " + list.get(i).getExercise_name() + "\t" + list.get(i).getStart() + "\t"
					+ list.get(i).getEnd() + "\t" + rtleft + " out of " + (list.get(i).getTotal_rt() + 1)
					+ " attempts left.");
		}
		System.out.println("Enter your choice to see attempt history -> ");
		int cnt = sc.nextInt();
		ArrayList<AHistory1> alist = obj.execute3(list.get(cnt - 1).getExercise_id(), session.get("username"));
		for (int i = 0; i < alist.size(); ++i) {
			System.out.println(
					(i + 1) + ". Score - " + alist.get(i).getScore() + "\tEnd Time - " + alist.get(i).getEndTime());
		}
		if (alist.size() < list.get(cnt - 1).getTotal_rt() + 1) {
			System.out.println("Do you want to make an attempt? (y or n)-> ");
			String temp = sc.next();
			if (temp.equals("n")) {
				System.out.println("\n\n");
				view_course_specific(session, course);
			} else {
				attempt(list.get(cnt - 1), session, course);
			}
		} else {
			System.out.println("\n\n");
			view_course_specific(session, course);
		}

	}

	private void attempt(Exercise exercise, HashMap<String, String> session, Course course) {
		header("Exercise " + exercise.getExercise_name() + " attempt by " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		AttemptObject obj = new AttemptObject();
		if (exercise.getType().equals("static")) {
			ArrayList<String> questions = obj.fetchQList(exercise.getExercise_id());
			ArrayList<ArrayList<Options>> history = new ArrayList<ArrayList<Options>>();
			for (int i = 0; i < questions.size(); ++i) {
				ArrayList<Options> options = obj.fetchOptions(Integer.parseInt(questions.get(i)));
				history.add(options);
			}
			for (int i = 0; i < questions.size(); ++i) {
				int choice = display(i, history.get(i));
				System.out.println("\n");
				if (choice == 0) {
					continue;
				} else {
					history.get(i).get(choice - 1).setSelected(true);
				}
			}
			boolean check = true;
			String choice = null;
			do {
				System.out.print("Do you wan't to change any answer (y or n) -> ");
				choice = sc.next();
				if (choice.equals("y")) {
					System.out.print("Provide Question number -> ");
					int ques = sc.nextInt();
					int ch = display(ques - 1, history.get(ques - 1));
					if (ch != 0) {
						for (int m = 0; m < 4; ++m) {
							if (m == ques - 1) {
								history.get(ques - 1).get(ch - 1).setSelected(true);
							} else {
								history.get(ques - 1).get(ch - 1).setSelected(false);
							}
						}
					}
				} else {
					check = false;
				}
			} while (check);
			int attempt_id = obj.fetchAttempt(exercise.getExercise_id(), session.get("username"));
			for (int i = 0; i < history.size(); ++i) {
				obj.submitQuiz(attempt_id, history.get(i));
			}
			System.out.println("You have submitted the Quiz.");
			System.out.println("\n\n");
			current_hws(session, course);
		} else {
			int diff = obj.fetchDifficulty(exercise.getExercise_id());
			ArrayList<ArrayList<Options>> history = new ArrayList<ArrayList<Options>>();
			int check = exercise.getTotal_qs();
			int i = 0;
			ArrayList<Options> opts = null;
			while (i < check) {
				opts = obj.fetchDOptions(exercise.getExercise_id(), diff);
				int choice = display(i, opts);
				System.out.println("\n");
				if (choice == 0) {
					continue;
				} else {
					opts.get(choice - 1).setSelected(true);
				}
				diff = obj.submitQuestion(diff, exercise, opts);
				history.add(opts);
				i += 1;
			}
			int attempt_id = obj.fetchAttempt(exercise.getExercise_id(), session.get("username"));
			for (int j = 0; j < history.size(); ++j) {
				obj.submitQuiz(attempt_id, history.get(j));
			}
			System.out.println("You have submitted the Quiz.");
			System.out.println("\n\n");
			current_hws(session, course);
		}
	}

	private int display(int i, ArrayList<Options> q) {
		System.out.println((i + 1) + ". " + q.get(0).getQ_text());
		System.out.println("Options - ");
		for (int k = 0; k < q.size(); ++k) {
			System.out.println((k + 1) + ". " + q.get(k).getOpt_text());
		}
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean check = false;
		int choice = -1;
		do {
			System.out.print("Enter your choice (0 to skip) ->");
			choice = sc.nextInt();
			if (choice >= 0 && choice <= q.size()) {
				check = false;
			} else {
				System.out.println("Please enter valid choice. ");
				check = true;
			}
		} while (check);
		return choice;
	}

	private void logout(HashMap<String, String> session) {
		for (String key : session.keySet()) {
			session.remove(key);
		}
		Login.main(null);
	}

}
