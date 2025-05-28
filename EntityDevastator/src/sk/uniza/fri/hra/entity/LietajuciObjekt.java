package sk.uniza.fri.hra.entity;

import java.util.ArrayList;
/**
 * Rozhranie  LietajuciObjekt predstavuje entitu alebo objekt, ktorý má schopnosť lietať
 * a uchováva zoznam takýchto lietajúcich objektov.
 *
 * @param <T> typ objektu, ktorý toto rozhranie reprezentuje.
 */
public interface LietajuciObjekt <T>  {
    /**
     * Získa zoznam lietajúcich objektov.
     *
     * @return ArrayList objektov typu T, ktoré reprezentujú lietajúce objekty.
     */
    ArrayList<T> getZoznamObjektov();
}
