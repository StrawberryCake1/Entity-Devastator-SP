package sk.uniza.fri.hra.potraviny;

import sk.uniza.fri.hra.entity.Entita;
import sk.uniza.fri.hra.Hrac;
import fri.shapesge.Obrazok;
/**
 * Trieda Protein predstavuje špeciálnu potravinu,
 * ktorá po použití zvýši hráčovi silu útoku.
 */
public class Protein extends Potravina {
    private final Hrac hrac;
    private final Obrazok potravina;
    /**
     * Vytvorí novú inštanciu proteínu viazanú na hráča a entitu, pri ktorej sa zobrazuje.
     * @param hrac hráč, ktorý získa účinok potraviny
     * @param entita entita, pri ktorej sa potravina objaví
     * @param potravina obrázok reprezentujúci túto potravinu
     */
    public Protein(Hrac hrac, Entita entita, Obrazok potravina) {
        super(entita, potravina);
        this.hrac = hrac;
        this.potravina = potravina;
    }
    /**
     * Vráti reťazec popisujúci účinok proteínu – zvýšenie sily útoku.
     * @return textový popis účinku
     */
    @Override
    public String getVylepsenie() {
        return super.getVylepsenie() + "sila utoku";
    }
    /**
     * Skryje obrázok proteínu a zvýši hráčovi silu útoku.
     * Vypíše aj popis vykonaného vylepšenia.
     */
    @Override
    public void vykonajAbilitu() {
        this.potravina.skry();
        System.out.println(this.getVylepsenie());
        this.hrac.zvysPoskodenie();
    }
}
