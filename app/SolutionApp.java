package app;

import SoP.ConnToDB;
import models.Solution;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static models.User.*;
import static models.Solution.*;
import static models.Exercise.*;

public class SolutionApp {

    private static Scanner scann;

    public static void main(String[] args) {

        scann = new Scanner(System.in);

        choose();

        String text = scann.nextLine();

        while (!text.equals("quit")) {
            if (text.equals("add")) {

//                wyświetl wszystkich użytkoników
                seeAllUsers();

//                wybór id użytkownika
                System.out.println("Select one user for who you want to solve the exercise");
                writeId();
                int userId = scann.nextInt();

//                wyświetl wszystkie zadania użytkownika
                try {
                    System.out.println(Arrays.toString(loadAllExercises(ConnToDB.getInstance().getConnection())));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                wybór id zadania do rozwiązania dla użytkownika
                writeId();
                int exerciseId = scann.nextInt();

//                dodanie rozwiązania
                Solution solution = new Solution();
                Date now = new Date(System.currentTimeMillis());
                solution.setCreated(now);
                solution.setExercise_id(exerciseId);
                solution.setUser_id(userId);

//                przydzielenie rozwiązania do zadania i użytkownika
                try {
                    solution.saveToDB(ConnToDB.getInstance().getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                wyświetlenie rezultatu
                seeAllByUserId(userId);

//                wyświetlenie menu
                text = "";
            } else if (text.equals("view")) {

//                wyświetla wszystkich uźytkowników
                seeAllUsers();

//                wybór użytkownika
                System.out.println("Select one user for who you want to see all solutions");
                writeId();
                int id = scann.nextInt();
                seeAllByUserId(id);

//                wyświetlenie menu
                text = "";
            } else if (text.equals("")) {
                choose();
            } else {
                System.out.println("That option is not exists. Try again.");
            }
            
            text = scann.nextLine();
        }

    }

    public static void choose() {

//        wyświetlanie opcji
        System.out.println("Choose one of the options:");
        System.out.println("- add");
        System.out.println("- view");
        System.out.println("- quit");

    }

    public static void seeAllUsers() {

//        wyświetla wszystkich użytkowników
        try {
            System.out.println(Arrays.toString(loadAllUsers(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeId() {

//        pobierz id od uźytkownika
        while (!scann.hasNextInt()) {
            System.out.println("This is not ID. Please try again.");
            scann.nextLine();
        }
    }

    public static void seeAllByUserId(int id) {
        try {
            System.out.println(Arrays.toString(loadAllByUserId(ConnToDB.getInstance().getConnection(), id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
