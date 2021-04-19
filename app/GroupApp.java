package app;

import SoP.ConnToDB;
import models.Group;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static models.Group.*;

public class GroupApp {

    private static String name;
    private static Scanner scann = new Scanner(System.in);
    private static Group group;
    private static int id;

    public static void main(String[] args) {

        seeAll();

        String text = scann.nextLine();

        while (!text.equals("quit")) {
            if (text.equals("add")) {
                System.out.println("In order to add group");
                System.out.println("Name");
                name = scann.nextLine();
                add(name);
                save();
                seeAll();
            } else if (text.equals("edit")) {
                System.out.println("Enter the ID of the Group you want to edit");
                writeId();
                giveId(id);
                edit();
                save();
                seeAll();
            } else if (text.equals("delete")) {
                System.out.println("Enter the ID of the Group you want to delete");
                writeId();
                giveId(id);
                try {
                    group.delete(ConnToDB.getInstance().getConnection());
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
//        wyświetlenie wszystki grup
        try {
            System.out.println(Arrays.toString(loadAllGroups(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        menu
        System.out.println("Choose one of the options:");
        System.out.println("- add");
        System.out.println("- edit");
        System.out.println("- delete");
        System.out.println("- quit");
    }

    public static Group add(String name) {
        group = new Group(name);
        return group;
    }

    public static Group save() {
        try {
            group.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static void writeId() {
//        pobierz id grupy od użytkownika
        while (!scann.hasNextInt()) {
            System.out.println("Please provide correct ID");
            scann.nextLine();
        }
        id = scann.nextInt();
    }

    public static Group giveId(int id) {
//        wybór grupy do edycji
        try {
            group = loadGroupById(ConnToDB.getInstance().getConnection(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static Group edit() {
//        pobieranie danych od użytkonika
        System.out.println("Name");
        scann.nextLine();
        name = scann.nextLine();

//        edycja grupy
        group.setName(name);
        return group;
    }

}
