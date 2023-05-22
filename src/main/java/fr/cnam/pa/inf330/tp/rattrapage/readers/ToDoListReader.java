package fr.cnam.pa.inf330.tp.rattrapage.readers;


import fr.cnam.pa.inf330.tp.rattrapage.model.Tache;
import fr.cnam.pa.inf330.tp.rattrapage.model.TacheCreneau;
import fr.cnam.pa.inf330.tp.rattrapage.model.TacheI;
import fr.cnam.pa.inf330.tp.rattrapage.model.ToDoList;

import javax.swing.text.StyledEditorKit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoListReader {

    public ToDoList loadTaches(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        ToDoList toDoList = new ToDoList(Paths.get(fileName).getFileName().toString().substring(0,Paths.get(fileName).getFileName().toString().indexOf(".")), fileName);
        try (reader){
            String line1 = reader.readLine();
            while (reader.ready()) {
                String line = reader.readLine();
                String[] fields = line.split(",");
                Long dateHeureProgrammee = Long.parseLong(fields[0]);
                long dateHeureProgrammeeFin = fields[1].equals("") ? 0L : Long.parseLong(fields[1]);
                long dateHeureEffectuee = fields[2].equals("") ? 0L : Long.parseLong(fields[2]);
                String libelle = fields[3];
                boolean active = Boolean.parseBoolean(fields[4]);
                List<Long> alarmes = fields[5].equals("") ? new ArrayList<>() :
                        Arrays.stream(fields[5].split(";"))
                                .map(Long::parseLong)
                                .collect(Collectors.toList());
                String type = fields[6];

                TacheI tache;
                if (type.equals("tache")) {
                    tache = new Tache();
                    tache.setDateHeureProgrammee(dateHeureProgrammee);
                    tache.setDateHeureEffectuee(dateHeureEffectuee);
                    tache.setLibelle(libelle);
                    tache.setActive(active);
                    tache.setAlarmes(alarmes);
                    List<TacheI> taches = toDoList.getTaches();
                    taches.add(tache);
                    toDoList.setTaches(taches);
                } else if (type.equals("tacheCreneau")) {
                    tache = new TacheCreneau(dateHeureProgrammeeFin);
                    tache.setDateHeureProgrammee(dateHeureProgrammee);
                    tache.setDateHeureEffectuee(dateHeureEffectuee);
                    tache.setLibelle(libelle);
                    tache.setActive(active);
                    tache.setAlarmes(alarmes);
                    List<TacheI> taches = toDoList.getTaches();
                    taches.add(tache);
                    toDoList.setTaches(taches);
                } else {
                    throw new IllegalArgumentException("Type de tâche non valide : " + type);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du traitement des ToDo lists, Exception :" + e.getMessage());
        }
        return toDoList;
    }

    private List<Long> readAlarmes(String alarmes){
        if (alarmes != null && !alarmes.isEmpty()){
            return Arrays.stream(alarmes.split(";")).map(a -> Long.parseLong(a)).collect(Collectors.toList());
        }
        return null;
    }
}
