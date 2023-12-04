package com.online.examination;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;
    private String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile() {
        return profile;
    }

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}

class Quiz {
    private Map<String, String> questions;
    private Map<String, String> correctAnswers;

    public Quiz() {
        this.questions = new HashMap<String, String>();
        this.correctAnswers = new HashMap<String, String>();

        
        questions.put("Q1", "What is the capital of France?");
        correctAnswers.put("Q1", "Paris");

        questions.put("Q2", "Which programming language is used for Android app development?");
        correctAnswers.put("Q2", "Java");
        
     
        questions.put("Q3", "What is the largest planet in our solar system?");
        correctAnswers.put("Q3", "Jupiter");

        questions.put("Q4", "Who wrote 'Romeo and Juliet'?");
        correctAnswers.put("Q4", "William Shakespeare");

        questions.put("Q5", "What is the square root of 144?");
        correctAnswers.put("Q5", "12");

        questions.put("Q6", "Which element has the chemical symbol 'O'?");
        correctAnswers.put("Q6", "Oxygen");

        questions.put("Q7", "What is the powerhouse of the cell?");
        correctAnswers.put("Q7", "Mitochondria");

        questions.put("Q8", "In which year did World War II end?");
        correctAnswers.put("Q8", "1945");

        questions.put("Q9", "Who painted the Mona Lisa?");
        correctAnswers.put("Q9", "Leonardo da Vinci");

        questions.put("Q10", "What is the currency of Japan?");
        correctAnswers.put("Q10", "Yen");

        questions.put("Q11", "What is the boiling point of water in Celsius?");
        correctAnswers.put("Q11", "100");
    }

    public void startQuiz(User user) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (String questionId : questions.keySet()) {
            System.out.println("\nQuestion " + questionId + ": " + questions.get(questionId));
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(correctAnswers.get(questionId))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + correctAnswers.get(questionId));
            }
        }

        System.out.println("\nQuiz completed. Your score: " + score);
    }
}

public class Main {

	private static Map<String, User> users = new HashMap<String, User>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();

        while (true) {
            System.out.println("\nWelcome to the Quiz App!");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        //PRESENTATION FOR THE LOGIN FOR TEACHER AND STUDENT
    	//STUDENT- user1 /password1
    	//Teacher - user2/password2
        users.put("user1", new User("user1", "password1", "Student"));
        users.put("user2", new User("user2", "password2", "Teacher"));
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            User currentUser = users.get(username);
            System.out.println("Login successful! Welcome, " + currentUser.getUsername() + " (" + currentUser.getProfile() + ")");

            while (true) {
                System.out.println("\nUser Menu:");
                System.out.println("1. Update Profile");
                System.out.println("2. Update Password");
                System.out.println("3. Start Quiz");
                System.out.println("4. Logout");

                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (userChoice) {
                    case 1:
                        updateProfile(currentUser);
                        break;
                    case 2:
                        updatePassword(currentUser);
                        break;
                    case 3:
                        startQuiz(currentUser);
                        break;
                    case 4:
                        System.out.println("Logout successful.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void updateProfile(User user) {
        System.out.print("Enter new profile information: ");
        String newProfile = scanner.nextLine();
        user.updateProfile(newProfile);
        System.out.println("Profile updated successfully.");
    }

    private static void updatePassword(User user) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        user.updatePassword(newPassword);
        System.out.println("Password updated successfully.");
    }

    private static void startQuiz(User user) {
        Quiz quiz = new Quiz();
        quiz.startQuiz(user);
    }
}