package app;

import SoP.ConnToDB;
import models.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static models.User.loadAllUsers;
import static models.User.loadUserById;

public class UserApp {

    private static String username;
    private static String password;
    private static String email;
    private static int user_group_id;
    private static Scanner scann = new Scanner(System.in);
    private static User user;
    private static int id;

    public static void main(String[] args) {

        seeAll();

        String text = scann.nextLine();

        while (!text.equals("quit")) {
            if (text.equals("add")) {
                System.out.println("In order to add user");
                System.out.println("Name");
                username = scann.nextLine();
                details();
                add(username, password, email, user_group_id);
                save();
            } else if (text.equals("edit")) {
                System.out.println("Enter the ID of the user you want to edit");
                writeId();
                giveId(id);
                edit();
                save();
            } else if (text.equals("delete")) {
                System.out.println("Enter the ID of the user you want to delete");
                writeId();
                giveId(id);
                try {
                    user.delete(ConnToDB.getInstance().getConnection());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (text.equals("")) {
                seeAll();
            } else {
                System.out.println("That option is not exists. Try again.");
            }
            text = scann.nextLine();
        }

    }

    public static void seeAll() {
//        wyświetlenie listy wszystkich uzytkowników
        try {
            System.out.println(Arrays.toString(loadAllUsers(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Choose one of the options:");
        System.out.println("- add");
        System.out.println("- edit");
        System.out.println("- delete");
        System.out.println("- quit");
    }

//    pobieranie od użytkownika danych
    public static void details() {
//        System.out.println("Name");
//        username = scann.nextLine();
        System.out.println("Password");
        password = scann.nextLine();
        System.out.println("e-mail");
        email = scann.nextLine();
        System.out.println("Group of users");
        while (!scann.hasNextInt()) {
            System.out.println("This is not a number");
            scann.next();
        }
        user_group_id = scann.nextInt();
    }

    public static User add(String username, String password, String email, int user_group_id) {

//        pobieranie od użytkownika danych
//        System.out.println("Name");
//        username = scann.nextLine();
//        details();

//        utworzenie nowego użytkownika
        user = new User(username, password, email, user_group_id);
        return user;
    }

    public static User save() {

//        dodanie użytkownika do bazy
        try {
            user.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void writeId() {
//        pobierz ID użytkownika od użytkownika
        while (!scann.hasNextInt()) {
            System.out.println("Please provide the correct ID");
            scann.nextLine();
        }
        id = scann.nextInt();
    }

    public static User giveId(int id) {
        //        wybór użytkownika do edycji
        try {
            user = loadUserById(ConnToDB.getInstance().getConnection(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User edit() {

//        pobieranie od użytkownika danych
        System.out.println("Name");
        scann.nextLine();
        username = scann.nextLine();
        details();

//        edycja danych na nowe
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setUser_Group_Id(user_group_id);
        return user;
    }

}
