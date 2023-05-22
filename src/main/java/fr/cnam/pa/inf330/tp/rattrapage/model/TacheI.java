package fr.cnam.pa.inf330.tp.rattrapage.model;

import java.util.List;

public interface TacheI {

    boolean leverAlarme(Long currentTime);
    boolean afficherTacheAFaire(Long currentTime);
    void print();
    Long getDateHeureProgrammee();
    void setDateHeureProgrammee(final Long dateHeureProgrammee);
    Long getDateHeureEffectuee();
    void setDateHeureEffectuee(final Long dateHeureEffectuee);
    List<Long> getAlarmes();
    void setAlarmes(final List<Long> alarmes) throws InvalidOperationException ;
    String getLibelle();
    void setLibelle(final String libelle);
    Boolean getActive();
    void setActive(final Boolean active);
}
