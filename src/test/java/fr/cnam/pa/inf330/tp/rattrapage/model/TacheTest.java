package fr.cnam.pa.inf330.tp.rattrapage.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TacheTest {

    @Test
    void testExceptionSiAlarmesApresDateHeureProgrammee() throws InvalidOperationException{
        Tache tache = new Tache();
        tache.setDateHeureProgrammee(1649456400L);
        Assertions.assertThrows(InvalidOperationException.class, () -> tache.setAlarmes(Arrays.asList(1649460000L, 1649463600L)));
    }

    @Test
    void testPasExceptionSiAlarmesAvantDateHeureProgrammee () throws InvalidOperationException {

        Tache tache = new Tache();
        tache.setDateHeureProgrammee(1649467200L);
        Assertions.assertDoesNotThrow( () -> tache.setAlarmes(Arrays.asList(1649456400L,1649460000L, 1649463600L)));
    }

    @Test
    void testLeverAlerteRetourneTrue () throws InvalidOperationException {

        TacheCreneau tache = new TacheCreneau(1649456400L);
        tache.setDateHeureProgrammee(1649449200L);
        Assertions.assertTrue(tache.leverAlarme(1649452800L));
    }

    @Test
    void testLeverAlerteRetourneFalse() throws InvalidOperationException {

        TacheCreneau tache = new TacheCreneau(1649456400L);
        tache.setActive(false);
        Assertions.assertFalse(tache.leverAlarme(1649453200L));
    }
}
