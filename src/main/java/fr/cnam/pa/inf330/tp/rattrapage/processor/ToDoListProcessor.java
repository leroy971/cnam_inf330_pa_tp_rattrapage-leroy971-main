package fr.cnam.pa.inf330.tp.rattrapage.processor;

import fr.cnam.pa.inf330.tp.rattrapage.model.Tache;
import fr.cnam.pa.inf330.tp.rattrapage.model.ToDoList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoListProcessor {

    public void traiterTaches(ToDoList toDoList){
        Long currentTime = 1649431594L; // Friday 8 April 2022 15:26:43
        System.out.println("Date/Heure : " + Tache.TimestampToDateHeure(currentTime));
        System.out.println("-------------------------------");
        System.out.println("ToDo List : " + toDoList.getNom());
        System.out.println("\tFichier : " + toDoList.getFileName());
        toDoList.getTaches().stream().forEach(t -> {
            // Compléter cette méthode
            System.out.println(t.toString());
            if (t.getActive()) {
                if (t.getDateHeureEffectuee() == 0) {
                    System.out.println(" Tâche à faire !!!");
                } else {
                    System.out.println(" Tâche effectuée le : " + Tache.TimestampToDateHeure(t.getDateHeureEffectuee()));
                }
            } else {
                System.out.println(" Tâche inactive !!!");
            }
        });
        System.out.println("------------ Fin du traitement -------------------");
    }

}
