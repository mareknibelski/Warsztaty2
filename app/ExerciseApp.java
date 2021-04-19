package app;

import SoP.ConnToDB;
import models.Exercise;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static models.Exercise.*;

public class ExerciseApp{

    private static String title;
    private static String description;
    private static Scanner scann = new Scanner(System.in);
    private static Exercise exercise;
    private static int id;

    public static void main(String[] args) {

        seeAll();

        String text = scann.nextLine();

        while (!text.equals("quit")) {
            if (text.equals("add")) {
                System.out.println("In order to add exercise");
                details();
                add(title, description);
                save();
                seeAll();
            } else if (text.equals("edit")) {
                System.out.println("Enter the ID of the exercise you want to edit");
                writeId();
                giveId(id);
                edit();
                save();
                seeAll();
            } else if (text.equals("delete")) {
                System.out.println("Enter the ID of the exercise you want to delete");
                writeId();
                giveId(id);
                try {
                    exercise.delete(ConnToDB.getInstance().getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("")) {
                seeAll();
            } else {
                    System.out.println("That opions is not exists. Try again.");
            }
            text = scann.nextLine();
        }

    }

    public static void seeAll() {
//        wyświetli wszystkie zadania
        try {
            System.out.println(Arrays.toString(loadAllExercises(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choose one of the options:");
        System.out.println("- add");
        System.out.println("- edit");
        System.out.println("- delete");
        System.out.println("- quit");
    }

    public static void details() {
//        pobranie od użytkownika danych
        System.out.println("Title");
        title = scann.nextLine();
        System.out.println("Description");
        description = scann.nextLine();
    }

    public static Exercise add(String title, String description) {
//        stworzenie nowego zadania
        exercise = new Exercise(title, description);
        return exercise;
    }

    public static Exercise save() {
//        zapisanie nowego zadania
        try {
            exercise.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public static void writeId() {
//      pobieranie id zadania od użytkownika
        while (!scann.hasNextInt()) {
            System.out.println("Please provide the correct ID");
            scann.nextLine();
        }
        id = scann.nextInt();
    }

    public static Exercise giveId(int id) {
//        wybór zadania do edycji
        try {
            exercise = loadExerciseById(ConnToDB.getInstance().getConnection(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercise;
    }

    public static Exercise edit() {
//        pobieranie danych od użytkownika
        System.out.println("Title");
        scann.nextLine();
        title = scann.nextLine();
        System.out.println("Description");
        description = scann.nextLine();
//        edycja zadania
        exercise.setTitle(title);
        exercise.setDescription(description);
        return exercise;
    }

}
