package com.gradience.user;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.gradience.database.AddCourseObject;
import com.gradience.database.AddQuestionObject;
import com.gradience.database.AddTopicObject;
import com.gradience.database.EditUserObject;
import com.gradience.database.SearchQuestionList;
import com.gradience.database.SearchTopicList;
import com.gradience.database.TeacherCourseList;
import com.gradience.login.Login;
import com.gradience.model.Course;
import com.gradience.model.Question;
import com.gradience.model.Topic;

public class Professor {

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
			view_add_topic(session);
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

	private int choice() {
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. View/Edit Profile");
			System.out.println("2. View/Add Courses");
			System.out.println("3. View/Add Topics");
			System.out.println("4. Search/Add Question");
			System.out.println("5. Logout");
			System.out.print("Enter your choice -> ");
			choice = sc.nextInt();

			if (choice > 0 || choice < 7) {
				check = false;
			} else {
				check = true;
				System.out.println("Please enter a valid choice.\n");
			}
		} while (check);
		return choice;
	}

	private void view_edit_profile(HashMap<String, String> session) {
		header("View/Edit Profile, " + session.get("username"));
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
		header("View Profile, " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("0. Go Back");
		System.out.println("1. First Name: " + session.get("fname"));
		System.out.println("2. Last Name: " + session.get("lname"));
		System.out.println("3. Employee ID: " + session.get("username"));
		System.out.print("Enter Your Choice -> ");
		int choice = sc.nextInt();

		if (choice == 0) {

			System.out.println("\n\n");
			view_edit_profile(session);
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

	private void view_add_course(HashMap<String, String> session) {
		header("View/Add Courses, " + session.get("username"));

		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. View Courses");
			System.out.println("2. Add Course");
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
		header("View Courses, " + session.get("username"));
		int choice = 1;
		boolean check = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		TeacherCourseList obj = new TeacherCourseList();
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
			view_add_course(session);
		} else {
			view_course_specific(session, courses.get(choice - 1));
		}

	}

	private void view_course_specific(HashMap<String, String> session, Course course) {
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
			System.out.println("1. View/Add Exercises");
			System.out.println("2. View/Add TA");
			System.out.println("3. Enrol/Drop Student");
			System.out.println("4. View Report");
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
			view_course(session);
			break;
		case 1:
			view_add_exercise_to_course(session, course);
			break;
		case 2:
			view_add_ta_to_course(session, course);
			break;
		case 3:
			enrol_drop_student(session, course);
			break;
		case 4:
			view_course_report(session, course);
			break;
		}
	}

	private void view_add_exercise_to_course(HashMap<String, String> session, Course course) {
		header("View/Add Exercises for " + course.getCourse_id() + ", " + session.get("username"));

		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. View Exercises");
			System.out.println("2. Add Exercise");
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
			view_course_specific(session, course);
			break;
		case 1:
			System.out.println("\n\n");
			view_exercises(session, course);
			break;
		case 2:
			System.out.println("\n\n");
			add_exercise(session, course);
			break;
		}
	}

	private void view_exercises(HashMap<String, String> session, Course course) {
		header("View Exercises, " + session.get("username"));
		System.out.println("\n\n");
		view_add_exercise_to_course(session, course);
	}

	private void add_exercise(HashMap<String, String> session, Course course) {
		header("View Exercises, " + session.get("username"));

	}

	private void view_add_ta_to_course(HashMap<String, String> session, Course course) {

	}

	private void enrol_drop_student(HashMap<String, String> session, Course course) {

	}

	private void view_course_report(HashMap<String, String> session, Course course) {

	}

	private void add_course(HashMap<String, String> session) {
		header("Add Course, " + session.get("username"));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Course c = new Course();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.print("1. Enter Course ID : ");
		c.setCourse_id(sc.next());
		System.out.print("2. Enter Course Name : ");
		sc.nextLine();
		c.setCourse_name(sc.nextLine());
		System.out.print("3. Enter Program (bachelor,masters) : ");
		c.setStream(sc.next());
		System.out.print("4. Enter Start Date (yyyy-mm-dd) : ");
		try {
			String temp = sc.next();
			c.setStart_date(new Date((sdf.parse(temp)).getTime()));
		} catch (ParseException e) {
			c.setStart_date(new Date(System.currentTimeMillis()));
		}
		System.out.print("5. Enter End Date (yyyy-mm-dd) : ");
		try {
			String temp1 = sc.next();
			c.setEnd_date(new Date((sdf.parse(temp1)).getTime()));
		} catch (ParseException e) {
			c.setEnd_date(new Date(System.currentTimeMillis() + Long.getLong("66096000000")));
		}
		System.out.print("6. Enter Maximum Students : ");
		c.setMax_students(sc.nextInt());
		AddCourseObject obj = new AddCourseObject();
		HashMap<String, String> response = obj.execute(session, c);
		System.out.println(response.get("TEXT"));
		System.out.println("\n\n");
		view_add_course(session);
	}

	private void view_add_topic(HashMap<String, String> session) {
		header("View/Add Topic, " + session.get("username"));
		boolean check = false;
		int choice = 1;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("0. Go Back");
			System.out.println("1. Search Topic");
			System.out.println("2. Add Topic");
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
			search_topic(session);
			break;
		case 2:
			System.out.println("\n\n");
			add_topic(session);
			break;
		}

	}

	private void add_topic(HashMap<String, String> session) {
		header("Add Topic, " + session.get("username"));
		boolean check = false;
		String temp = null;
		AddTopicObject obj = new AddTopicObject();
		HashMap<String, String> response = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Enter New Topic Name (0 to go back)-> ");
			temp = sc.nextLine();
			if (temp.equals("0")) {
				System.out.println("\n\n");
				view_add_topic(session);
			} else {
				response = obj.execute(session.get("username"), temp);
				if (response.get("MSG").equals("success")) {
					System.out.println(response.get("TEXT"));
					check = false;
				} else {
					System.out.println(response.get("TEXT"));
					check = true;
				}
			}
		} while (check);

		System.out.print("Press 0 to go back -> ");
		int x = sc.nextInt();
		if (x == 0) {
			System.out.println("\n\n");
			view_add_topic(session);
		}

	}

	private void search_topic(HashMap<String, String> session) {
		header("Search Topics, " + session.get("username"));
		boolean check = false;
		String temp = null;
		SearchTopicList obj = new SearchTopicList();
		ArrayList<Topic> lst = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("Enter keyword to Search for topic (0 to go back)-> ");
			temp = sc.nextLine();
			if (temp.equals("0")) {
				System.out.println("\n\n");
				view_add_topic(session);
			} else {
				lst = obj.execute(temp);
				if (lst.size() == 0) {
					check = true;
				} else {
					check = false;
					for (Topic x : lst) {
						System.out.println(x.getTopic_id() + " - " + x.getTopic_name());
					}
				}
			}

		} while (check);

		System.out.print("Press 0 to go back -> ");
		int x = sc.nextInt();
		if (x == 0) {
			System.out.println("\n\n");
			view_add_topic(session);
		}
	}

	private void search_add_question(HashMap<String, String> session) {
		header("Search/Add Questions, " + session.get("username"));
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
		header("Add Question, " + session.get("username"));
		String temp = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		AddQuestionObject obj = new AddQuestionObject();
		HashMap<String, String> response = null;
		int topic;
		String text = null;
		String hint = null;
		String explain = null;
		int difficulty;
		System.out.print(
				"Note: Please keep the Topic ID handy.\nPress 0 to go back and find Topic ID, any other to continue -> ");
		temp = sc.next();
		if (temp.equals("0")) {
			System.out.println("\n\n");
			search_add_question(session);
		} else {
			System.out.print("Enter question type (s for static or p for parameterised)-> ");
			temp = sc.next();
			if (temp.equals("s")) {
				System.out.print("Enter Topic ID -> ");
				topic = sc.nextInt();
				System.out.print("Enter Question Text -> ");
				sc.nextLine();
				text = sc.nextLine();
				System.out.print("Enter Hint -> ");
				hint = sc.nextLine();
				System.out.print("Enter Detailed Explanation -> ");
				explain = sc.nextLine();
				System.out.print("Enter Difficulty (1 to 5)-> ");
				difficulty = sc.nextInt();
				response = obj.execute1(text, hint, explain, difficulty, topic, session.get("username"));
				int qid = 0;
				int pid = 0;
				if (response.get("MSG").equals("failure")) {
					System.out.println(response.get("TEXT"));
					System.out.println("\n\n");
					search_add_question(session);
				} else {
					qid = Integer.parseInt(response.get("TEXT"));
					pid = Integer.parseInt((obj.execute2(qid, "default", "default")).get("TEXT"));
				}
				System.out.print("Enter number of options for this question -> ");
				int cnt = sc.nextInt();
				String otext = null;
				String otype = null;
				String oxplain = null;
				int i = 0;
				while (i < cnt) {
					System.out.println("Details for Option " + (i + 1));
					System.out.print("Enter option text -> ");
					sc.nextLine();
					otext = sc.nextLine();
					System.out.print("Enter c for correct, i for incorrect -> ");
					otype = sc.next();
					if (otype.equals("c"))
						otype = "correct";
					else
						otype = "incorrect";
					System.out.print("Enter explanation for this option -> ");
					sc.nextLine();
					oxplain = sc.nextLine();
					response = obj.execute3(qid, pid, otext, otype, oxplain);
					if (response.get("MSG").equals("failure")) {
						System.out.println(response.get("TEXT"));
						System.out.println("Option not added. Try again.");
					} else {
						i += 1;
						System.out.println("Option added.");
					}
				}
				System.out.println("Question added to Bank.");
			} else {
				int cnt;
				System.out.print("Enter Topic ID -> ");
				topic = sc.nextInt();
				System.out.print("Enter number of Parameters -> ");
				cnt = sc.nextInt();
				System.out.print("Enter Question Text -> ");
				sc.nextLine();
				text = sc.nextLine();
				System.out.print("Enter Hint -> ");
				hint = sc.nextLine();
				System.out.print("Enter Detailed Explanation -> ");
				explain = sc.nextLine();
				System.out.print("Enter Difficulty (1 to 5)-> ");
				difficulty = sc.nextInt();
				response = obj.execute1(text, hint, explain, difficulty, topic, session.get("username"));
				int qid = 0;
				if (response.get("MSG").equals("failure")) {
					System.out.println(response.get("TEXT"));
					System.out.println("\n\n");
					search_add_question(session);
				} else {
					qid = Integer.parseInt(response.get("TEXT"));
					System.out.println("Please ensure the parameter name you enter matches in the question text.");
					int i = 0;
					int pid = 0;
					ArrayList<String> pname = new ArrayList<String>();
					String x = null;
					ArrayList<String[]> pvalue = new ArrayList<String[]>();
					String y = null;
					while (i < cnt) {
						System.out.println("Details for Parameter " + (i + 1));
						System.out.print("Parameter Name -> ");
						x = sc.next();
						pname.add(x);
						System.out.print("Parameter Values (comma separated)-> ");
						y = sc.next();
						pvalue.add(y.split(","));
						i += 1;
					}

					for (int j = 0; j < pvalue.get(0).length; ++j) {
						x = "";
						y = "";
						for (int k = 0; k < pname.size(); ++k) {
							x = x + ":" + pname.get(k);
							y = y + ":" + pvalue.get(k)[j];
						}
						x = x.substring(1);
						y = y.substring(1);
						response = obj.execute2(qid, x, y);
						if (response.get("MSG").equals("success")) {
							pid = Integer.parseInt(response.get("TEXT"));
							System.out.print("Enter number of options for this set (" + x + ")=(" + y + ") -> ");
							int cnt2 = sc.nextInt();
							String otext = null;
							String otype = null;
							String oxplain = null;
							int a = 0;
							while (a < cnt2) {
								System.out.println("Details for Option " + (a + 1));
								System.out.print("Enter option text -> ");
								sc.nextLine();
								otext = sc.nextLine();
								System.out.print("Enter c for correct, i for incorrect -> ");
								otype = sc.next();
								if (otype.equals("c"))
									otype = "correct";
								else
									otype = "incorrect";
								System.out.print("Enter explanation for this option -> ");
								sc.nextLine();
								oxplain = sc.nextLine();
								response = obj.execute3(qid, pid, otext, otype, oxplain);
								if (response.get("MSG").equals("failure")) {
									System.out.println("Option not added. Try again.");
								} else {
									a += 1;
									System.out.println("Option added successfully.");
								}
							}
						} else {
							System.out.println("Cannot register this parameter combination (" + x + ")=(" + y + ")");
						}
					}
					System.out.println("Question added to Bank.");
				}
			}
		}

		System.out.print("Press 0 to go back -> ");
		int x = sc.nextInt();
		if (x == 0) {
			System.out.println("\n\n");
			search_add_question(session);
		}
	}

	private void search_question(HashMap<String, String> session) {
		header("Search Question, " + session.get("username"));
		String temp = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(
				"Note: Please keep the Topic ID handy.\nPress 0 to go back and find Topic ID, any other to continue -> ");
		temp = sc.next();
		if (temp.equals("0")) {
			System.out.println("\n\n");
			search_add_question(session);
		} else {
			System.out.println("1. Search by Topic ID");
			System.out.println("2. Search by Question ID");
			System.out.print("Enter your choice -> ");
			temp = sc.next();
			SearchQuestionList obj = new SearchQuestionList();
			ArrayList<Question> list = new ArrayList<Question>();
			if (temp.equals("1")) {
				System.out.print("Enter Topic_ID -> ");
				temp = sc.next();
				list = obj.execute_topic(Integer.parseInt(temp));
			} else {
				System.out.print("Enter Question_ID -> ");
				temp = sc.next();
				list = obj.execute_question(Integer.parseInt(temp));
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); ++i) {
					System.out.println(list.get(i).getQuestion_id() + " || " + list.get(i).getQuestion_text() + " || "
							+ list.get(i).getDifficulty() + " || " + list.get(i).getHint() + " || "
							+ list.get(i).getExplanation());
				}
			}
		}
		System.out.print("Press 0 to go back -> ");
		int x = sc.nextInt();
		if (x == 0) {
			System.out.println("\n\n");
			search_add_question(session);
		}
	}

	private void logout(HashMap<String, String> session) {
		session = null;
		Login.main(null);
	}

}