package models;

import SoP.ConnToDB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import static models.User.loadAllUsers;
import static models.User.loadUserById;
import static models.Group.loadAllGroups;
import static models.Group.loadGroupById;
import static models.Exercise.loadAllExercises;
import static models.Exercise.loadExerciseById;
import static models.Solution.loadAllSolutions;
import static models.Solution.loadSolutionById;
import static models.Solution.loadAllByUserId;
import static models.Solution.loadAllByExerciseId;
import static models.User.loadAllByGroupId;

public class Main1 {

    public static void main(String[] args) {

//        stworzenie 2 użytkowników
        User user = new User();
        user.setUsername("Marek");
        user.setEmail("mn@gmail.com");
        user.setPassword("marek");
        user.setUser_Group_Id(1);

        User user1 = new User();
        user1.setUsername("Magda");
        user1.setEmail("mz@gmail.com");
        user1.setPassword("magda");
        user1.setUser_Group_Id(2);

//        zapisanie do bazy użytkownika #2
//        try {
//            users1.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        wyświetlenie konkretnego użytkownika #1
        try {
            System.out.println(loadUserById(ConnToDB.getInstance().getConnection(), 6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie nie istniejącego użytkownika
        try {
            System.out.println(loadUserById(ConnToDB.getInstance().getConnection(), 10));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie wszystkich użytkowników
        try {
            System.out.println(Arrays.toString(loadAllUsers(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        edycja istniejącego użytkownika #2
        try {
            user1 = loadUserById(ConnToDB.getInstance().getConnection(), 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        user1.setUsername("Magda");
        user1.setEmail("magda@gmail.com");
        user1.setPassword("magda");

        try{
            user1.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(user1);

//        utworzenie użytkownika #3
        User user2 = new User();
        user2.setUsername("Marcin");
        user2.setEmail("marcin@gmail.com");
        user2.setPassword("marcin");

//        zapisanie do bazy użytkownika #3
//        try {
//            users2.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        usunięcie użytkownika #3
//        Users users3 = null;
//        try {
//            users3 = loadUserById(ConnToDB.getInstance().getConnection(), 55);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            users3.delete(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //        stworzenie 2 użytkowników
        User user4 = new User();
        user4.setUsername("Wiktor");
        user4.setEmail("wp@gmail.com");
        user4.setPassword("wiktor");
        user4.setUser_Group_Id(1);

        User user5 = new User();
        user5.setUsername("Dagmara");
        user5.setEmail("dj@gmail.com");
        user5.setPassword("dagmara");
        user5.setUser_Group_Id(2);

//        zapisanie do bazy użytkownika #4
//        try {
//            users4.saveToDB(ConnToDB.getInstance().getConnection());
//            users5.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        stworzenie grup #2
        Group group = new Group();
        group.setName("Grupa1");
        Group group1 = new Group();
        group1.setName("Grupa2");

//        zapisanie grup #2
//        try {
//            group.saveToDB(ConnToDB.getInstance().getConnection());
//            group1.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        wyświetlanie konkretnej grupy
        try {
            System.out.println(loadGroupById(ConnToDB.getInstance().getConnection(), 2));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie nieistniejącej grupy
        try {
            System.out.println(loadGroupById(ConnToDB.getInstance().getConnection(), 3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie wszystkich grup
        try {
            System.out.println(Arrays.toString(loadAllGroups(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        edycja istniejącej grupy #2
        try {
            group1 = loadGroupById(ConnToDB.getInstance().getConnection(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        group1.setName("Grupa");

        try {
            group1.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        utworzenie grupy #3
        Group group2 = new Group();
        group2.setName("Grupa3");

//        zapisanie do bazy grupy #3
//        try {
//            group2.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        usunięcie z bazy grupy #3
//        Group group3 = null;
//        try {
//            group3 = loadGroupById(ConnToDB.getInstance().getConnection(), 5);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            group3.delete(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        stworzenie zadań #2
        Exercise exercise = new Exercise();
        exercise.setTitle("zadanie");
        exercise.setDescription("wykonanie zadania");
        Exercise exercise1 = new Exercise();
        exercise1.setTitle("zadanie 1");
        exercise1.setDescription("wykonanie zadania 1");

//        zapisanie zadań do bazy danych
//        try {
//            exercise.saveToDB(ConnToDB.getInstance().getConnection());
//            exercise1.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        wyświetlenie konkretnego zadania
        try {
            System.out.println(loadExerciseById(ConnToDB.getInstance().getConnection(), 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlanie zadania z poza zakresu
        try {
            System.out.println(loadExerciseById(ConnToDB.getInstance().getConnection(), 10));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//      wyświetlanie wszystkich zadań
        try {
            System.out.println(Arrays.toString(loadAllExercises(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        edycja istniejącego zadania #2
        try {
            exercise1 = loadExerciseById(ConnToDB.getInstance().getConnection(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        exercise1.setTitle("zadanie 2");
        exercise1.setDescription("wykonania zadania 2");

        try {
            exercise1.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        stworzenie zadania #3
        Exercise exercise2 = new Exercise();
        exercise2.setTitle("zadanie 3");
        exercise2.setDescription("wykonanie zadania 3");

//        zapisanie do bazy zadania #3
//        try {
//            exercise2.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        usunięcie zadania #3
//        Exercise exercise3 = null;
//        try {
//            exercise3 = loadExerciseById(ConnToDB.getInstance().getConnection(), 5);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            exercise3.delete(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        stworzenie rozwiązania #2
        Solution solution = new Solution();
        Date now = new Date(System.currentTimeMillis());
        solution.setCreated(now);
        solution.setUpdated(now);
        solution.setDescription("Rozwiązanie");
        solution.setExercise_id(1);
        solution.setUser_id(6);
        Solution solution1 = new Solution();
        solution1.setCreated(now);
        solution1.setUpdated(now);
        solution1.setDescription("Rozwiązanie 1");
        solution1.setExercise_id(2);
        solution1.setUser_id(8);

//        try {
//            solution.saveToDB(ConnToDB.getInstance().getConnection());
//            solution1.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        wyświetlenie konkretnego roziązania #1
        try {
            System.out.println(loadSolutionById(ConnToDB.getInstance().getConnection(), 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie rozwiązania z poza zakresu
        try {
            System.out.println(loadSolutionById(ConnToDB.getInstance().getConnection(), 8));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie wszystkich rozwiązań
        try {
            System.out.println(Arrays.toString(loadAllSolutions(ConnToDB.getInstance().getConnection())));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//      edycja istniejącego rozwiązania #2
        try {
            solution1 = loadSolutionById(ConnToDB.getInstance().getConnection(), 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        solution1.setDescription("Rozwiązanie 2");

        try {
            solution1.saveToDB(ConnToDB.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        stworzenie rozwiązania #3
        Solution solution2 = new Solution();
        solution2.setCreated(now);
        solution2.setUpdated(now);
        solution2.setDescription("Rozwiązanie 3");
        solution2.setExercise_id(1);
        solution2.setUser_id(8);

//        zapisanie do bazy rozwiązania #3
//        try {
//            solution2.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        usunięcie roziązania #3
//        Solution solution3 = new Solution();
//        try {
//            solution3 = loadSolutionById(ConnToDB.getInstance().getConnection(), 13);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            solution3.delete(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        stworzenie dodatkowych rozwiązań #4
        Solution solution4 = new Solution();
        solution4.setCreated(now);
        solution4.setUpdated(now);
        solution4.setDescription("Rozwiązanie 3");
        solution4.setExercise_id(2);
        solution4.setUser_id(6);
        Solution solution5 = new Solution();
        solution5.setCreated(now);
        solution5.setUpdated(now);
        solution5.setDescription("Rozwiązanie 4");
        solution5.setExercise_id(1);
        solution5.setUser_id(8);

//        zapisanie rozwiązań so bazy #4
//        try {
//            solution4.saveToDB(ConnToDB.getInstance().getConnection());
//            solution5.saveToDB(ConnToDB.getInstance().getConnection());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        wyświetlenie wszystkich rozwiązań danego uzytkownika
        try {
            System.out.println(Arrays.toString(loadAllByUserId(ConnToDB.getInstance().getConnection(), 8)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie wszystkich rozwiązań danego zadania od najnowszego
        try {
            System.out.println(Arrays.toString(loadAllByExerciseId(ConnToDB.getInstance().getConnection(), 1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        wyświetlenie wszystkich użytkowników danej grupy
        try {
            System.out.println(Arrays.toString(loadAllByGroupId(ConnToDB.getInstance().getConnection(), 1)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
