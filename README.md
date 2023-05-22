[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/OrZoxZXO)
# CNAM INF330 - Exercice de rattrapage

L'idée est d'implémenter une application qui permet de gérer de "ToDo" lists.
L'exercice est structuré en 3 parties :
- modélisation
- lecture de données
- traitement de données

## Modélisation
Il y a deux types de tâche :
- tâche simple modélisée par la classe `Tâche`
- tâche avec un créneau modélisée par la classe `TacheCreneau`
- les deux classes sont l'implémentation de l'interface `TacheI`

Une ToDo list, modélisée par la classe `ToDoList`, contient une liste de tâches qui peuvent être des deux types mentionnés ci-dessus (`Tâche` et `TacheCreneau`).

### Exercice :
- Implémenter les méthodes ci-dessous :
  - `fr.cnam.pa.inf330.tp.rattrapage.model.Tache.leverAlarme(timestamp)`: elle doit retourner `true` si la tâche est activée et si :
  - le timestamp passé en paramètre est _"interieur"_ à une ou plusieurs alarmes, s'il y en a
  - le timestamp passé en paramètre est _"posterieur"_ au timestamp de la date/heure

- `fr.cnam.pa.inf330.tp.rattrapage.model.Tache.afficherTacheAFaire(timestamp)`: elle doit retouner `true` si la tâche est activée et si :
  - le timestamp passé en paramètre est _"posterieur"_ au timestamp de la date/heure
  - la tâche n'a pas été effectuée

- `fr.cnam.pa.inf330.tp.rattrapage.model.Tache.setAlarmes(List<Long> alarmes)`: cette méthode doit lever l'exception `fr.cnam.pa.inf330.tp.rattrapage.model.InvalidOperationException.InvalidOperationException` si au moins une des alarmes est _posterieur_ à la date/here programmée

## Lecture de données
L'idée ici est de lire (_désérialiser_) le contenu des fichiers fournis dans le répertoire `/todolists`. Ces fichiers contiennent les données des tâches d'une ToDo list dont le nombre est le nom du fichier. 
### Exercice :
Compléter l'implémentation de la méthode `fr.cnam.pa.inf330.tp.rattrapage.readers.ToDoListReader.loadTaches` afin de :
- lire le contenu fichier `csv` passé en paramètre et de retourner l'objet correspondant de type `ToDoList`
- la structure du fichier est `dateHeureProgrammee,dateHeureProgrammeeFin,dateHeureEffectuee,libelle,active,alarmes,type` où :
  - `dateHeureProgrammee` : timestamp en secondes de la date/heure de la tâche
  - `dateHeureProgrammeeFin` : timestamp en secondes de la date/heure de la fin de la tâche (uniquement pour les taches avec créneau)
  - `dateHeureEffectuee` : timestamp en secondes de la date/heure de réalisation de la tache
  - `libelle` : libellé de la tâche
  - `active` : valeur `true`si la tache est encore active, `false`sinon
  - `alarmes` : liste de timestamps séparés par `;` qui représentent les alarmes programmées pour la tâche
  - `type`: valeur `tache` si c'est une tâche simple et `tacheCreneau`si c'est une tâche avec une `dateHeureProgrammeeFin`
- les date/heures sont exprimées par timestamps (type Long) pour faciliter les calculs

## Traitement de données
### Exercice :
Compléter l'implémentation de la méthode `fr.cnam.pa.inf330.tp.rattrapage.processor.ToDoListProcessor.traiterTaches` afin de lister les informations des tâches et d'afficher, à l'aide de la méthode `fr.cnam.pa.inf330.tp.rattrapage.model.Tache.afficherTacheAFaire(timestamp)`, les messages suivants : 
- `Tâche à faire !!!` : si la tache est encore à faire
- `Tâche effectuée le 2022-04-09 03:21:22` : si la tâche a été déjà effectuée
- `Tâche inactive !!!` : si la tache n'est plus active

Ci-dessous un exemple de la sortie souhaitée :

```
-------------------------------
ToDo List : famille
 Fichier : /users/gbobeff/documents/cnam/templates/cnam_inf330_pa_tp_rattrapage_template/todolists/famille.csv
Tache{dateHeureProgrammee=2022-04-10 12:30:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='aller au supermarcher', active=true}
 Tâche à faire !!!
TacheCreneau{dateHeureProgrammee=2022-04-20 12:30:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 16:00:00, 2022-04-15 16:55:00], libelle='rdv électricien', active=true, dateHeureProgrammeeFin=2022-04-20 13:30:00}
 Tâche à faire !!!
Tache{dateHeureProgrammee=2022-04-15 17:00:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='passer tondeuse', active=false}
 Tâche inactive !!!
------------ Fin du traitement -------------------
```

## Test
Et pour finir ... quelques tests.
### Exercice :
Compléter les tests de la classe `fr.cnam.pa.inf330.tp.rattrapage.model.TacheTest` :
- `testExceptionSiAlarmesApresDateHeureProgrammee` : tester que l'exception est bien levée quand au mois une alarme est _posterieure_ à la date/heure programmée de la tâche.
- `testPasExceptionSiAlarmesAvantDateHeureProgrammee` : tester que l'exception n'est pas levée quand toutes les alarmes sont _antérieures_ à la date/heure programmée de la tâche.
- `testLeverAlerteRetourneTrue`: tester que la méthode `leverAlarme` retourne `true` si les conditions pour retourner `true` sont remplies.
- `testLeverAlerteRetourneFalse` : tester que la méthode `leverAlarme` retourne `false` si les conditions pour retourner `false` sont remplies.

## Lancement de l'application
```bash
./run.sh 
Utilisation: run.sh <folder>
```

## Résultat attendu
Le lancement de l'application devrait rendre dans la sortie standard le résultat ci-dessous :
```
######### ToDo List ##########
Date/Heure : 2022-04-08 17:26:34
-------------------------------
ToDo List : famille
 Fichier : /users/gbobeff/documents/cnam/templates/cnam_inf330_pa_tp_rattrapage_template/todolists/famille.csv
Tache{dateHeureProgrammee=2022-04-10 12:30:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='aller au supermarché', active=true}
 Tâche à faire !!!
TacheCreneau{dateHeureProgrammee=2022-04-20 12:30:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 16:00:00, 2022-04-15 16:55:00], libelle='rdv électricien', active=true, dateHeureProgrammeeFin=2022-04-20 13:30:00}
 Tâche à faire !!!
Tache{dateHeureProgrammee=2022-04-15 17:00:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='passer tendeuse', active=false}
 Tâche inactive !!!
------------ Fin du traitement -------------------
Date/Heure : 2022-04-08 17:26:34
-------------------------------
ToDo List : travail
 Fichier : /users/gbobeff/documents/cnam/templates/cnam_inf330_pa_tp_rattrapage_template/todolists/travail.csv
TacheCreneau{dateHeureProgrammee=2022-05-02 12:00:00, dateHeureEffectuee=N/A, alarmes=[2022-05-02 11:50:00], libelle='réunion produit', active=true, dateHeureProgrammeeFin=2022-05-02 13:00:00}
 Tâche à faire !!!
Tache{dateHeureProgrammee=2022-05-10 12:00:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='daily agile', active=true}
 Tâche à faire !!!
------------ Fin du traitement -------------------
Date/Heure : 2022-04-08 17:26:34
-------------------------------
ToDo List : personnel
 Fichier : /users/gbobeff/documents/cnam/templates/cnam_inf330_pa_tp_rattrapage_template/todolists/personnel.csv
TacheCreneau{dateHeureProgrammee=2022-05-15 17:00:00, dateHeureEffectuee=N/A, alarmes=[2022-05-15 16:55:00], libelle='match tennis', active=true, dateHeureProgrammeeFin=2022-05-15 18:00:00}
 Tâche à faire !!!
Tache{dateHeureProgrammee=2022-04-15 12:00:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 11:50:00], libelle='rdv Dr', active=true}
 Tâche à faire !!!
------------ Fin du traitement -------------------
Date/Heure : 2022-04-08 17:26:34
-------------------------------
ToDo List : famille
 Fichier : /users/gbobeff/documents/cnam/templates/cnam_inf330_pa_tp_rattrapage_template/todolists/famille.csv
Tache{dateHeureProgrammee=2022-04-10 12:30:00, dateHeureEffectuee=2022-04-09 03:30:31, alarmes=N/A, libelle='aller au supermarcher', active=true}
 Tâche effectuée le 2022-04-09 03:30:31
TacheCreneau{dateHeureProgrammee=2022-04-20 12:30:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 16:00:00, 2022-04-15 16:55:00], libelle='rdv électricien', active=true, dateHeureProgrammeeFin=2022-04-20 13:30:00}
 Tâche à faire !!!
Tache{dateHeureProgrammee=2022-04-15 17:00:00, dateHeureEffectuee=N/A, alarmes=N/A, libelle='passer tondeuse', active=false}
 Tâche passée ou inactive !!!
------------ Fin du traitement -------------------
######### Alertes ##########
Maintenant est : 2022-04-15 17:00:00
Tache{dateHeureProgrammee=2022-04-10 12:30:00, dateHeureEffectuee=2022-04-09 03:30:31, alarmes=N/A, libelle='aller au supermarcher', active=true}
TacheCreneau{dateHeureProgrammee=2022-04-20 12:30:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 16:00:00, 2022-04-15 16:55:00], libelle='rdv électricien', active=true, dateHeureProgrammeeFin=2022-04-20 13:30:00}
Tache{dateHeureProgrammee=2022-04-15 12:00:00, dateHeureEffectuee=N/A, alarmes=[2022-04-15 11:50:00], libelle='rdv Dr', active=true}
```