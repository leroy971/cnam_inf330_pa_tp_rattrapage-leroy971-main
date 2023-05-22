package fr.cnam.pa.inf330.tp.rattrapage.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Tache implements TacheI {
    Long dateHeureProgrammee;
    Long dateHeureEffectuee;
    List<Long> alarmes;
    String libelle;
    Boolean active = true;

    public boolean leverAlarme(Long currentTime){
        Date date = new Date();
        if (this.active) {
            if(this.alarmes!=null) {
                if (!this.alarmes.isEmpty()) {
                    for (long alarme : alarmes) {
                        if (currentTime < alarme) {
                            return true;
                        }
                    }
                }
            }
            if (currentTime > this.dateHeureProgrammee) {
                return true;
            }
        }
        return false;
    }


    public boolean afficherTacheAFaire(Long currentTime){
        if (this.active) {
            if (currentTime > this.dateHeureProgrammee) {
                return true;
            }
            if(this.dateHeureEffectuee==null){
                return true;
            }
        }
        return false;
    }

    public void setAlarmes(final List<Long> alarmes) throws InvalidOperationException {
        for (long alarme : alarmes) {
            if (alarme > this.dateHeureProgrammee) {
                throw new InvalidOperationException("Alarme posterieure à la date/heure programmée");
            }
        }
    }

    public Long getDateHeureProgrammee() {
        return dateHeureProgrammee;
    }

    public void setDateHeureProgrammee(final Long dateHeureProgrammee) {
        this.dateHeureProgrammee = dateHeureProgrammee;
    }

    public Long getDateHeureEffectuee() {
        return dateHeureEffectuee;
    }

    public void setDateHeureEffectuee(final Long dateHeureEffectuee) {
        this.dateHeureEffectuee = dateHeureEffectuee;
    }

    public List<Long> getAlarmes() {
        return alarmes;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Tache{" +
                "dateHeureProgrammee=" + Tache.TimestampToDateHeure(dateHeureProgrammee) +
                ", dateHeureEffectuee=" + Tache.TimestampToDateHeure(dateHeureEffectuee) +
                ", alarmes=" + (alarmes==null?"N/A":alarmes.stream().map(a -> Tache.TimestampToDateHeure(a)).collect(Collectors.toList())) +
                ", libelle='" + libelle + '\'' +
                ", active=" + active +
                '}';
    }

    public static String TimestampToDateHeure(Long timestamp){
        if (timestamp != null) {
            Date currentDate = new Date(timestamp * 1000);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            String date = dateFormat.format(currentDate);
            return date;
        }
        return "N/A";
    }
}
