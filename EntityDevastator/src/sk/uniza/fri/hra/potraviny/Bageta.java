package sk.uniza.fri.hra.potraviny;

import sk.uniza.fri.hra.entity.Entita;
import sk.uniza.fri.hra.Hrac;
import fri.shapesge.Obrazok;
/**
 * Trieda Bageta predstavuje konkrétnu potravinu,
 * ktorá pri použití zvýši hráčovi život a následne sa skryje.
 */
public class Bageta extends Potravina {
    private final Hrac hrac;
    private final Obrazok potravina;
    /**
     * Vytvorí novú inštanciu bagety viazanú na hráča a entitu, pri ktorej sa zobrazuje.
     * @param hrac hráč, ktorý získa účinok potraviny
     * @param entita entita, pri ktorej sa potravina objaví
     * @param potravina obrázok reprezentujúci túto potravinu
     */
    public Bageta(Hrac hrac, Entita entita, Obrazok potravina) {
        super(entita, potravina);
        this.hrac = hrac;
        this.potravina = potravina;
    }
    /**
     * Skryje obrázok bagety a zvýši hráčovi život.
     * Vypíše aj popis vykonaného vylepšenia.
     */
    @Override
    public void vykonajAbilitu() {
        this.potravina.skry();
        System.out.println(this.getVylepsenie());
        this.hrac.zvysZivot();
    }
    /**
     * Vráti reťazec popisujúci efekt bagety – vyliečenie.
     * @return textový popis účinku
     */
    @Override
    public String getVylepsenie() {
        return super.getVylepsenie() + "vyliecenie";
    }
}
