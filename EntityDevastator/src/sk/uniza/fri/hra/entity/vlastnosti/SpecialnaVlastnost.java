package sk.uniza.fri.hra.entity.vlastnosti;
/**
 * Rozhranie pre entity, ktoré majú špeciálnu vlastnosť alebo schopnosť.
 * Triedy, ktoré implementujú toto rozhranie, musia definovať metódu
 * vykonajSpecialnyUtok(), ktorá vykoná špeciálny útok alebo efekt.
 */
public interface SpecialnaVlastnost {
    /**
     * * Vykoná špeciálny útok alebo schopnosť entity.
     */
    void vykonajSpecialnyUtok();
}
