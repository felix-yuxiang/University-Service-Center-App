package ui;

import exceptions.NegativeValueException;
import exceptions.OutGradeBoundException;
import exceptions.OutWeightBoundException;
import model.*;
import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Writer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Represents a method-chain of user interface system.
public class UserApp {
    //Original database of all CPSC 200-level-or-less courses in UBC.
    public static final Course CPSC100 = new Course("Computational Thinking",
            "Meaning and impact of computational thinking. "
            + "Solving problems "
            + "using computational thinking, testing, debugging. How computers work. No prior computing experience "
            + "required. Not for students with existing credit for or exemption from CPSC 107, CPSC 110 or APSC 160.",
            "CPSC", "CPSC100", "No prerequisites required");

    public static final Course CPSC103 = new Course("Introduction to Systematic Program Design",
            "Computation as a tool"
            + " for systematic problem solving in non-computer-science disciplines. Introductory programming skills."
            + " Not for credit for students who have credit for, or exemption from, or are concurrently taking CPSC 110"
            + "or APSC 160. No programming experience expected.","CPSC","CPSC103",
            "No prerequisites required");

    public static final Course CPSC107 = new Course("Systematic Program Design",
            "Fundamental computation and program "
            + "structures. Continuing systematic program design from CPSC 103.","CPSC","CPSC107",
            "CPSC 103");
    public static final Course CPSC110 = new Course("Computation, Programs, and Programming",
            "Fundamental program and computation structures. Introductory programming skills. Computation as"
                    + " a tool for information processing, simulation and modelling, and interacting with the world.",
            "CPSC","CPSC110","No prerequisites required");
    public static final Course CPSC121 = new Course("Models of Computation",
            "Physical and mathematical structures of computation. Boolean algebra and combinations"
                    + " logic circuits; proof techniques; functions and sequential circuits; sets and relations;"
                    + " finite state machines; sequential instruction execution.",
            "CPSC","CPSC121","(Principles of Mathematics 12 or Pre-calculus 12.)\n"
            + "Co-reqs: One of CPSC 107, CPSC 110.");
    public static final Course CPSC203 = new Course("Programming, Problem Solving, and Algorithms",
            "Analysis of increasingly complex algorithmic problems, using a modern programming language and"
                   + " a variety of approaches. Problem decomposition and abstraction guide explorations of topics from"
                   + " applied algorithms, for example Voronoi Diagrams, Markov Chains, Bin Packing, and Graph Search. "
                   + "Not for students with credit for CPSC 210 or CPEN 221.","CPSC","CPSC203",
            "One of CPSC 103, CPSC 110, EOSC 211, MATH 210, PHYS 210, COMM 337");
    public static final Course CPSC210 = new Course("Software Construction",
            "Design, development, and analysis of robust software components."
                    + " Topics such as software design, computational models, data structures, debugging, and testing.",
            "CPSC","CPSC210","One of CPSC 107, CPSC 110.");
    public static final Course CPSC213 = new Course("Introduction to Computer Systems",
            "Software architecture, operating systems, and I/O architectures. Relationships between"
                    + " application software, operating systems, and computing hardware; critical sections,"
                    + " deadlock avoidance, and performance; principles and operation of disks and networks.",
            "CPSC","CPSC213","All of CPSC 121, CPSC 210.");
    public static final Course CPSC221 = new Course("Basic Algorithms and Data Structures",
            "Design and analysis of basic algorithms and data structures; algorithm analysis methods,"
                   + " searching and sorting algorithms, basic data structures, graphs and concurrency.",
            "CPSC","CPSC221","One of CPSC 210, EECE 210, CPEN 221 and one of CPSC 121, MATH 220.");
    public static final Course CPSC259 = new Course("Data Structures and Algorithms for Electrical Engineers",
            "Advanced procedural programming. Fundamental algorithms for sorting and searching."
                   + " Data structures including lists, trees, and hash tables. Introduction to scripting languages"
                   + " and file input/output.",
            "CPSC","CPSC259","APSC 160.");
    public static final Course CPSC261 = new Course("Basics of Computer Systems",
            "Software architecture, operating systems, and I/O architectures. Relationships between"
                    + " application software, operating systems, and computing hardware; critical sections,"
                    + " deadlock avoidance, and performance; principles and operation of disks and networks.",
            "CPSC","CPSC261","One of EECE 259, CPEN 211 and one of EECE 210, CPEN 221.");
    public static final Course CPSC298 = new Course("Co-operative Work Placement I",
            "Approved and supervised technical work experience in the computing industry for a"
                   + " minimum of 3.5 months. Normally taken during Winter Session of second year."
                   + " Technical report required. Restricted to students admitted to the Co-operative"
                   + " Education Program in Computer Science.",
            "CPSC","CPSC298","One of CPSC 107, CPSC 110.");

    public static final String USER_FILE = "./data/user_data.json";

    List<Course> compsciCourses;
    List<User> users;


    //EFFECTS: Welcome panel
    //         add all the preset computer science courses to a list
    //         Automatically load all the user-data in the application.
    public void startApp() {
        compsciCourses = new LinkedList<>();
        compsciCourses.add(CPSC100);
        compsciCourses.add(CPSC103);
        compsciCourses.add(CPSC107);
        compsciCourses.add(CPSC110);
        compsciCourses.add(CPSC121);
        compsciCourses.add(CPSC203);
        compsciCourses.add(CPSC210);
        compsciCourses.add(CPSC213);
        compsciCourses.add(CPSC221);
        compsciCourses.add(CPSC259);
        compsciCourses.add(CPSC261);
        compsciCourses.add(CPSC298);

        System.out.println("-------------------------------------------\n-------------------------------------------");
        System.out.println("Welcome to the University Service Center!!!");
        System.out.println("We are dedicated to offering you an effective and comfortable environment. :-)");
        System.out.println("You can browse courses as a student and grade student as a TA.");
        loadUsers();
    }

    //EFFECTS: Identity confirmation, a login panel, you can choose to sign up or login.
    public void loginOrRegister() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter '1' to sign up, '2' to login, '3' to save all users info, "
                + "'4' to clear all the existing user data, '5' to exit and auto-save.");
        System.out.println("\t 1> Sign up              2> Login              3> Save              "
                + "4> Clear Existing Data              5> End program");
        System.out.print(">>>");
        int num = input.nextInt();
        if (num == 1) {
            signUpPanel();
        } else if (num == 2) {
            loginPanel();
        } else if (num == 3) {
            save();
        } else if (num == 4) {
            clear();
        } else if (num == 5) {
            end();
        } else {
            System.out.println("You have to enter the correct number. Please read the instruction carefully.");
            loginOrRegister();
        }

    }

    // MODIFIES: this
    // EFFECTS: load the existing users list from data, if not data exists, instantiate new user list
    private void loadUsers() {
        try {
            Reader reader = new Reader(USER_FILE);
            users = reader.read();
        } catch (FileNotFoundException e) {
            users = new LinkedList<>();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: save users data to local file
    public void save() {
        try {
            Writer writer = new Writer(USER_FILE);
            writer.write(users);
            writer.close();
            loginOrRegister();
        } catch (IOException e) {
            // save failure
            e.printStackTrace();
        }
    }

    // EFFECTS: clear users data in local file
    public void clear() {
        try {
            Writer writer = new Writer(USER_FILE);
            users = new LinkedList<>();
            writer.write(users);
            writer.close();
            loginOrRegister();
        } catch (IOException e) {
            // save failure
            e.printStackTrace();
        }
    }

    // EFFECTS: end the program and auto-save the existing users
    public void end() {
        try {
            Writer writer = new Writer(USER_FILE);
            writer.write(users);
            writer.close();
            System.exit(0);
        } catch (IOException e) {
            // save failure
            e.printStackTrace();
        }
    }


    //EFFECTS: sign up main panel.
    public void signUpPanel() {
        Scanner input = new Scanner(System.in);
        System.out.println("Thank you for choosing my application for the first time!");
        System.out.println("Are you a TA or a student?");
        System.out.println("Enter the item number.");
        System.out.println("\t 1> Teaching Assistant             2> Student");
        System.out.print(">>>");
        int option = input.nextInt();
        if (option == 1) {
            signUpTA();
        } else if (option == 2) {
            signUpStudent();
        } else {
            System.out.println("You have to enter the correct number. Please read the instruction carefully.");
            signUpPanel();
        }

    }

    //EFFECTS: sign up as a TA, type in the name and ID.
    public void signUpTA() {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = input1.nextLine();
        System.out.println("Continue or Restart the signing up process?");
        String str = input2.nextLine();
        if (str.equals("continue") || str.equals("Continue") || str.equals("CONTINUE")) {
            keepSignUpTA(name);
        } else {
            signUpTA();
        }

    }

    //MODIFIES: this
    //EFFECTS: registered as a TA and add to the User list.
    public void keepSignUpTA(String name) {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        User user;
        System.out.println("What is your teaching assistant number? (3 digits ONLY!)");
        String taID = input1.nextLine();
        System.out.println("Continue or Restart the signing up process?");
        String str = input2.nextLine();
        if (!(str.equals("continue") | str.equals("Continue") | str.equals("CONTINUE"))) {
            signUpTA();
        }
        haveSignedUp(taID);
        user = new TeachingAssistant(name, taID);
        users.add(user);
        System.out.println("You have registered as a TA successfully!");
        teachingAssistantPanel(user);
    }

    //Helper method
    //To check whether you have signed up before.
    //EFFECTS: go to login panel if you have previously signed up.
    public void haveSignedUp(String id) {
        for (User member : users) {
            if (member.getID().equals(id)) {
                System.out.println("Oops~~ It seems that you have signed up an account before.");
                loginPanel();
            }
        }
    }


    //EFFECTS: sign up as a student, type in the name and ID.
    public void signUpStudent() {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = input1.nextLine();
        System.out.println("Continue or Restart the signing up process?");
        String str = input2.nextLine();
        if (str.equals("continue") | str.equals("Continue")) {
            keepSignUpStudent(name);
        } else {
            signUpStudent();
        }

    }

    //MODIFIES: this
    //EFFECTS: registered as a student and add to the User list.
    public void keepSignUpStudent(String name) {
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        User user;
        System.out.println("What is your student number?");
        String studentID = input1.nextLine();
        System.out.println("Continue or Restart the signing up process?");
        String str = input2.nextLine();
        if (!(str.equals("continue") | str.equals("Continue"))) {
            signUpStudent();
        }
        haveSignedUp(studentID);
        user = new Student(name, studentID);
        users.add(user);
        System.out.println("You have registered as a student successfully!");
        studentPanel(user);
    }

    //EFFECTS: Using previously registered ID to login.
    public void loginPanel() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your TA/student number?");
        String id = input.nextLine();
        for (User member : users) {
            if (member.getID().equals(id)) {
                if (member.getID().length() == 3) {
                    teachingAssistantPanel(member);
                }
                studentPanel(member);
            }
        }
        System.out.println("Please sign up an account before using it. Thank you for your patience.");
        signUpPanel();
    }

    // EFFECTS: View all the students' ID who have registered this application.
    public void teachingAssistantPanel(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("Login time: " + showLocalTime());
        System.out.println("Hello, " + user.getName() + "!");
        System.out.println("You are assigned to create and grade assignments in CPSC210.");
        System.out.println("Find this course in students' schedule.");
        System.out.println("Enter the item number.");
        System.out.println("1> View students                 2> Quit");
        System.out.print(">>>");
        int num = input.nextInt();
        if (num == 1) {
            printAllStudent(user);
        } else if (num == 2) {
            quit(user);
        } else {
            System.out.println("Please type in the correct number!");
            teachingAssistantPanel(user);
        }
    }


    //EFFECTS: student starting panel with some options
    public void studentPanel(User user) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("Login time: " + showLocalTime());
        System.out.println("Hello, student " + user.getName() + "!");
        System.out.println("\t1> Browse Course");
        System.out.println("\t2> View Schedule");
        System.out.println("\t3> Analyze Schedule");
        System.out.println("\t4> Check your grade");
        System.out.println("\t5> Quit");
        System.out.println("Enter the number of each item.");
        System.out.print(">>>");
        int num = input.nextInt();
        distributeFunction(num, user);
    }

    //EFFECTS: use different number to call different methods  (after student panel)
    public void distributeFunction(int num, User user) {
        switch (num) {
            case 1:
                browseCourseStudent(user);
                break;
            case 2:
                viewScheduleStudent(user);
                break;
            case 3:
                analyzeScheduleStudent(user);
                break;
            case 4:
                checkCourseGrade(user);
            case 5:
                quit(user);
                break;
            default:
                System.out.println("Enter the exact number please!");
                studentPanel(user);
        }
    }

    //EFFECTS: QUIT helper method
    public void quit(User user) {
        System.out.println("Log out time: " + showLocalTime());
        System.out.println("Goodbye, " + user.getName() + "!");
        loginOrRegister();
    }

    //EFFECTS: browse all courses in the database,
    //         you call add course to the schedule.
    public void browseCourseStudent(User user) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        for (Course course : compsciCourses) {
            System.out.println(course.getCourseID() + " - " + course.getCourseName());
            System.out.println(course.getCourseDescription() + "\n");
        }
        System.out.println("Enter the item number please.");
        System.out.println("\t1>Add course to your schedule.          2>Back.");
        System.out.print(">>>");
        int num = input.nextInt();
        if (num == 1) {
            System.out.println("Which course do you want to add to your schedule? Key in the course ID.");
            String str = input1.nextLine();
            addCourseToUser(user, str);
        } else if (num == 2) {
            ((Student) user).getSchedule().setFormattedRegisterTime(showLocalTime());
            studentPanel(user);
        } else {
            System.out.println("Key in the numbers that have been provided as options!");
        }
    }

    //MODIFIES: Course
    //EFFECTS: Add the course to the student's schedule
    public void addCourseToUser(User user, String str) {
        checkCourseInSystem(str, user);
        for (Course course : ((Student) user).getSchedule().getCourses()) {
            if (course.getCourseIDForGrade().equals(str)) {
                System.out.println(((Student) user).addCourseToSchedule(course));
                browseCourseStudent(user);
            }
        }
        Course course = new Course(str);
        System.out.println(((Student) user).addCourseToSchedule(course));
        browseCourseStudent(user);
    }

    // EFFECTS: check whether the given course is in the system or not.
    public void checkCourseInSystem(String str, User user) {
        boolean flag = false;
        for (Course compsciCourses : compsciCourses) {
            if (compsciCourses.getCourseID().equals(str)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("This is not a valid course in the system.");
            System.out.println("Type in the exact same course ID shown above.");
            browseCourseStudent(user);
        }
    }

    //EFFECTS: to view student's schedule
    public void viewScheduleStudent(User user) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        if (((Student) user).getSchedule().getCourses().isEmpty()) {
            System.out.println("There is no course that has been added to the schedule yet!");
            studentPanel(user);
        } else {
            for (int i = 0; i < ((Student) user).getSchedule().getCourses().size(); i++) {
                System.out.println(((Student) user).getSchedule().getCourses().get(i).getCourseIDForGrade());
            }
            System.out.println("Last edited: " + ((Student)user).getSchedule().getFormattedRegisterTime());
            System.out.println("Enter the item number please.");
            System.out.println("1>Remove the course from your schedule           2>Back");
            System.out.print(">>>");
            int num = input.nextInt();
            if (num == 1) {
                System.out.println("Which course do you want to remove from your schedule? Key in the course ID.");
                String str = input1.nextLine();
                removeCourseFromUser(user, str);
            } else {
                studentPanel(user);
            }
        }
    }

    //EFFECTS: print out all the pre-reqs of the courses in the schedule.
    public void analyzeScheduleStudent(User user) {
        if (((Student) user).getSchedule().getCourses().isEmpty()) {
            System.out.println("There is no course that has been added to the schedule yet!");
        } else {
            for (Course course : ((Student) user).getSchedule().getCourses()) {
                for (Course course1 : compsciCourses) {
                    if (course.getCourseIDForGrade().equals(course1.getCourseID())) {
                        System.out.println(course1.getCourseID() + "\nPre-reqs: " + course1.getPreReq());
                    }
                }
            }
        }
        studentPanel(user);
    }

    //EFFECTS: remove course from the schedule
    public void removeCourseFromUser(User user, String str) {
        for (Course course : ((Student) user).getSchedule().getCourses()) {
            if (course.getCourseIDForGrade().equals(str)) {
                System.out.println(((Student) user).removeCourseFromSchedule(course));
                viewScheduleStudent(user);
            }
        }
        Course course = new Course("");
        System.out.println(((Student) user).removeCourseFromSchedule(course));
        viewScheduleStudent(user);
    }

    //EFFECTS: check the course grade
    public void checkCourseGrade(User user) {
        Scanner input = new Scanner(System.in);
        if (((Student) user).getSchedule().getCourses().isEmpty()) {
            System.out.println("There is no course that has been added to the schedule yet!");
            studentPanel(user);
        } else {
            for (int i = 0; i < ((Student) user).getSchedule().getCourses().size(); i++) {
                System.out.println(((Student) user).getSchedule().getCourses().get(i).getCourseIDForGrade());
            }
            System.out.println("Enter the item number please.");
            System.out.println("1>View Final Grade           2>View Assignments detail           3>Back");
            System.out.print(">>>");
            int num = input.nextInt();
            distributeFunction1(num, user);
        }
    }

    //EFFECTS: use different number to call different methods  (after checking grade)
    public void distributeFunction1(int num, User user) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        if (num == 1) {
            System.out.println("Which course do you want to see the final grade? Key in the course ID.");
            String str1 = input.nextLine();
            viewFinalGrade(user, str1);
        } else if (num == 2) {
            System.out.println("Which course do you want to see the assignments detail? Key in the course ID.");
            String str2 = input1.nextLine();
            viewAssignment(user, str2);
        } else {
            studentPanel(user);
        }
    }

    //EFFECTS: view the final grade of a course.
    public void viewFinalGrade(User user, String str) {
        for (Course course : ((Student) user).getSchedule().getCourses()) {
            if (course.getCourseIDForGrade().equals(str)) {
                course.calculateFinalGrade();
                System.out.println(course.printGradeStatus());
            }
        }
        checkCourseGrade(user);
    }

    //EFFECTS: view the assignment of the course
    public void viewAssignment(User user, String str) {
        for (Course course : ((Student) user).getSchedule().getCourses()) {
            if (course.getCourseIDForGrade().equals(str)) {
                if (course.getAssignments().isEmpty()) {
                    System.out.println("Assignments have not been posted.");
                } else {
                    for (Assignment assignment : course.getAssignments()) {
                        System.out.println(assignment.getComponent());
                        System.out.println("Weight: " + assignment.getWeight());
                        System.out.println(assignment.printGradingStatus());
                    }
                }
            }
        }
        checkCourseGrade(user);
    }

    //EFFECTS: print all students' ID in this application (registered students)
    public void printAllStudent(User user) {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        for (User member : users) {
            if (!(member.getID().length() == 3)) {
                System.out.println(member.getID());
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("NO students have registered on this application!");
            teachingAssistantPanel(user);
        } else {
            System.out.println("Type in the ID of the student.");
            String str = input.nextLine();
            viewScheduleTA(user, str);
        }
    }

    //EFFECTS: view the schedule of a student
    public void viewScheduleTA(User user, String str) {
        Scanner input = new Scanner(System.in);
        User member;
        for (User user1 : users) {
            if (user1.getID().equals(str)) {
                member = user1;
                if (((Student) user1).getSchedule().getCourses().isEmpty()) {
                    System.out.println("This student has not registered any course.");
                    teachingAssistantPanel(user);
                } else {
                    for (Course course : ((Student) user1).getSchedule().getCourses()) {
                        System.out.println(course.getCourseIDForGrade());
                    }
                    System.out.println("Enter the item number please.");
                    System.out.println("1> Select course                     2> Back");
                    System.out.print(">>>");
                    int num = input.nextInt();
                    distributeFunction2(num, member, user, str);
                }
            }
        }
        System.out.println("Please type in the correct ID above.");
        teachingAssistantPanel(user);
    }

    //EFFECTS: use different number to call different methods  (after viewing student's schedule)
    public void distributeFunction2(int num, User student, User user, String str) {
        Scanner input = new Scanner(System.in);
        if (num == 1) {
            System.out.println("Which course do you want to select? Key in courseID.");
            String courseID = input.nextLine();
            for (Course course1 : ((Student) student).getSchedule().getCourses()) {
                if (course1.getCourseIDForGrade().equals(courseID)) {
                    createAssignmentPanel(course1, user);
                }
            }
            System.out.println("Please key in the course within the list.");
        }
        viewScheduleTA(user, str);
    }

    //EFFECTS: assignment managing panel.
    public void createAssignmentPanel(Course course, User user) {
        Scanner input = new Scanner(System.in);
        if (course.getAssignments().isEmpty()) {
            System.out.println("You have to create an assignment first!");
        } else {
            for (Assignment assignment : course.getAssignments()) {
                System.out.println("Component: " + assignment.getComponent());
                System.out.println("Weight: " + assignment.getWeight());
                System.out.println("Grade: " + assignment.getGrade());
            }
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("1> Create assignment");
        System.out.println("2> Remove assignment");
        System.out.println("3> Grade assignment");
        System.out.println("4> Back to panel");
        System.out.println("Enter the item number please.");
        System.out.print(">>>");
        int num = input.nextInt();
        distributeFunction3(course, user, num);
    }


    //EFFECTS: use different number to call different methods  (after creating assignments)
    public void distributeFunction3(Course course, User user, int num) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        if (num == 1) {
            createAssignment(course, user);
        } else if (num == 2) {
            System.out.println("Which assignment do you want to remove? Key in component.");
            String str = input.nextLine();
            removeAssignment(course, user, str);
        } else if (num == 3) {
            System.out.println("Which assignment do you want to grade? Key in component.");
            String str1 = input1.nextLine();
            gradeAssignment(course, user, str1);
        } else {
            teachingAssistantPanel(user);
        }
    }


    //MODIFIES: Assignment
    //EFFECTS: create an assignment with component and weight for a student
    public void createAssignment(Course course, User user) {
        Assignment assignment = new Assignment("",0,"");
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        System.out.println("Key in the elements carefully in the order of 'component', 'weight'.");
        String component = input.nextLine();
        double weight = input1.nextDouble();
        try {
            assignment.createAssignment(component,weight,user.getName());
        } catch (NegativeValueException e) {
            System.err.println("Negative value is not acceptable for the weight of the assignment.");
            createAssignment(course,user);
        } catch (OutWeightBoundException e) {
            System.err.println("An assignment should not weigh more than 1.");
            createAssignment(course,user);
        }
        course.getAssignments().add(assignment);
        System.out.println("You have create an assignment successfully!");
        createAssignmentPanel(course, user);
    }

    //MODIFIES: Assignments
    //EFFECTS: remove the assignment from the course.
    //References: How to solve the concurrent modification exception in java?
    // https://stackoverflow.com/questions/1921104/loop-on-list-with-remove
    public void removeAssignment(Course course, User user, String str) {
        boolean flag = false;
//        for (int i = 0; i < course.getAssignments().size(); i++) {
//            if (course.getAssignments().get(i).getComponent().equals(str)) {
//                course.getAssignments().remove(i);
//                flag = true;
//            }
//        }
        for (Iterator<Assignment> iterator = course.getAssignments().iterator(); iterator.hasNext();) {
            Assignment assignment = iterator.next();
            if (assignment.getComponent().equals(str)) {
                iterator.remove();
                flag = true;
            }
        }
        if (flag) {
            System.out.println("You remove the assignment successfully!");
        } else {
            System.out.println("There is no assignment with this name. Try to remove again.");
        }
        createAssignmentPanel(course, user);
    }



    //REQUIRES: grade is a double type data (between 0 and 100).
    //MODIFIES: Assignment
    //EFFECTS: grade an assignment, you can put grade and feedback in it.
    public void gradeAssignment(Course course, User user, String str) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        for (Assignment assignment : course.getAssignments()) {
            if (assignment.getComponent().equals(str)) {
                System.out.println("Please type in the 'grade' and 'feedback' only in this specified order.");
                double grade = input.nextDouble();
                String feedback = input1.nextLine();
                try {
                    assignment.gradeAssignment(grade,feedback);
                } catch (NegativeValueException e) {
                    System.err.println("Negative value is not acceptable for the grade of the assignment.");
                    gradeAssignment(course,user,str);
                } catch (OutGradeBoundException e) {
                    System.err.println("The grade should not be more than 100.0.");
                    gradeAssignment(course,user,str);
                }
                System.out.println("You have finished grading this student");
                createAssignmentPanel(course, user);
            }
        }
        System.out.println("There is no assignment with this name. Try to remove again.");
        createAssignmentPanel(course, user);

    }


    //Using java library about local time.
    //EFFECTS: get the local time.
    public String showLocalTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }


}
