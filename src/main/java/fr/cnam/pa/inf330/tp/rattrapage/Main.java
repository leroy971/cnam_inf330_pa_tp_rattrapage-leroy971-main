package fr.cnam.pa.inf330.tp.rattrapage;

import fr.cnam.pa.inf330.tp.rattrapage.model.Tache;
import fr.cnam.pa.inf330.tp.rattrapage.model.TacheI;
import fr.cnam.pa.inf330.tp.rattrapage.model.ToDoList;
import fr.cnam.pa.inf330.tp.rattrapage.processor.ToDoListProcessor;
import fr.cnam.pa.inf330.tp.rattrapage.readers.ToDoListReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            String todoListFolder = "C:\\Users\\lramkalia\\Documents\\EICNAM\\JAVA\\cnam_inf330_pa_tp_rattrapage-leroy971-main\\todolists";
            ToDoListReader todoListReader = new ToDoListReader();
            ToDoListProcessor processor = new ToDoListProcessor();
            List<String> csvfiles = readCSVFilesFromFolder(todoListFolder);
            List<ToDoList> toDoLists = new ArrayList<>();
            System.out.println("######### ToDo List ##########");
            for(String fileName : csvfiles) {
                ToDoList toDoList = todoListReader.loadTaches(fileName);
                processor.traiterTaches(toDoList);
                toDoLists.add(toDoList);
            }
            // Re-traitement de la première ToDo list
            List<TacheI> tachesPasEffectuees = toDoLists.get(0).getTaches().stream().filter(t -> t.getDateHeureEffectuee() == null).collect(Collectors.toList());
            if (!tachesPasEffectuees.isEmpty()){
                // on met une date d'effectuée uniquement à la première tache
                tachesPasEffectuees.get(0).setDateHeureEffectuee(System.currentTimeMillis()/1000);
            }
            processor.traiterTaches(toDoLists.get(0));


            System.out.println("######### Alertes  ##########");
            Long maintenat = 1650034800L;
            System.out.println("Maintenant est : " + Tache.TimestampToDateHeure(maintenat));
            for(ToDoList tdl : toDoLists){
                tdl.getTaches().stream().filter(t -> t.leverAlarme(maintenat)).forEach(
                        t -> t.print()
                );
            }

        } else {
            System.out.println("Utilisation: java programme <folder>");
            System.exit(0);
        }
    }

    private static List<String> readCSVFilesFromFolder(String folder) throws IOException {
        List<String> csvfiles;
        try (Stream<Path> walk = Files.walk(Paths.get(folder))) {
            csvfiles = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith("csv"))
                    .collect(Collectors.toList());
        }
        return csvfiles;
    }
}
