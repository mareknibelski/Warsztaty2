package app;

import SoP.ConnToDB;
import models.Solution;
import models.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Date;
import java.util.Scanner;

import static models.User.*;
import static models.Solution.*;

public class MainMenuApp {

    public static void main(String[] args) {

        User user;
        Solution solution;
        int len = 0;

//        wyświetlenie wszystkich użytkowników
        try {
            System.out.println(Arrays.toString(loadAllUsers(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wybranie użytkownika któremy dodamy rozwiązanie
        System.out.println("Select a user, enter a user ID");
        Scanner scann = new Scanner(System.in);
        while (!scann.hasNextInt()) {
            System.out.println("This is not ID. Please try again.");
            scann.nextLine();
        }
        int id = scann.nextInt();
        try {
            user = loadUserById(ConnToDB.getInstance().getConnection(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie menu
        menu();

        String text = scann.nextLine();
        text = "";
        text = scann.nextLine();

        while (!text.equals("quit")) {

//            dodawanie rozwiązania
            if (text.equals("add")) {

//                brak edycji dodanych rozwiązań
                try {
                    len = loadAllByUserIdDescNull(ConnToDB.getInstance().getConnection(), id).length;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                    edycja rekordów
                if (len != 0) {

//                    wyświetlanie rekordów do edycji
                    try {
                        System.out.println(Arrays.toString(loadAllByUserIdDescNull(ConnToDB.getInstance().getConnection(), id)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

//                    wybieranie rozwiązania do edycji
                    System.out.println("Select the ID of the Solution to which the solution is to be added ");
                    while (!scann.hasNextInt()) {
                        System.out.println("This is not ID. Please try again.");
                        scann.nextLine();
                    }
                    int idSol = scann.nextInt();

//                    edytowanie rozwiązania
                    try {
                        solution = loadSolutionById(ConnToDB.getInstance().getConnection(), idSol);
                        System.out.println(solution);
                        Date now = new Date(System.currentTimeMillis());
                        solution.setUpdated(now);
                        System.out.println("Add solution");
                        scann.nextLine();
                        String textDesc = scann.nextLine();
                        solution.setDescription(textDesc);

//                        zapisywanie rozwiązania
                        try {
                            solution.saveToDB(ConnToDB.getInstance().getConnection());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

//                        wyświetlanie dodanego rozwiązania
                        try {
                            System.out.println(loadSolutionById(ConnToDB.getInstance().getConnection(), idSol));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {

//                    informacja o braku rekordów do edycji dla danego użytkownika
                    System.out.println("This user has no editing solutions.");
                }
//                wyświetlenie menu
                menu();
            } else if (text.equals("view")) {
//                wyświetlenie rozwiązań dla danego użytkownika
                try {
                    System.out.println(Arrays.toString(loadAllByUserId(ConnToDB.getInstance().getConnection(), id)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                wyświetlenie menu
                menu();
            }
            text = scann.nextLine();
        }

    }

//    menu
    public static void menu() {
        System.out.println("Choose once of options:");
        System.out.println("- add");
        System.out.println("- view");
        System.out.println("- quit");
    }

}
