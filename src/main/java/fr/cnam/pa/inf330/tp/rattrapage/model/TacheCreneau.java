package fr.cnam.pa.inf330.tp.rattrapage.model;

import java.util.stream.Collectors;

public class TacheCreneau extends Tache {

    Long dateHeureProgrammeeFin;

    public TacheCreneau(final Long dateHeureProgrammeeFin) {
        super();
        this.dateHeureProgrammeeFin = dateHeureProgrammeeFin;
    }

    public Long getDateHeureProgrammeeFin() {
        return dateHeureProgrammeeFin;
    }

    public void setDateHeureProgrammeeFin(final Long dateHeureProgrammeeFin) {
        this.dateHeureProgrammeeFin = dateHeureProgrammeeFin;
    }

    public String toString() {
        return "TacheCreneau{" +
                "dateHeureProgrammee=" + Tache.TimestampToDateHeure(dateHeureProgrammee) +
                ", dateHeureEffectuee=" + Tache.TimestampToDateHeure(dateHeureEffectuee) +
                ", alarmes=" + (alarmes==null?"N/A":alarmes.stream().map(a -> Tache.TimestampToDateHeure(a)).collect(Collectors.toList())) +
                ", libelle='" + libelle + '\'' +
                ", active=" + active +
                ", dateHeureProgrammeeFin=" + Tache.TimestampToDateHeure(dateHeureProgrammeeFin) +
                '}';
    }
}
