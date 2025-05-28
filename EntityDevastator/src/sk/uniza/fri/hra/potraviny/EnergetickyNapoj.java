package sk.uniza.fri.hra.potraviny;

import sk.uniza.fri.hra.entity.Entita;
import sk.uniza.fri.hra.Hrac;
import fri.shapesge.Obrazok;
/**
 * Trieda EnergetickyNapoj reprezentuje špeciálnu potravinu,
 * ktorá po použití zvýši rýchlosť hráča.
 */
public class EnergetickyNapoj extends Potravina {
    private final Hrac hrac;
    private final Obrazok potravina;
    /**
     * Vytvorí novú inštanciu energetického nápoja priradeného k hráčovi a entite, pri ktorej sa objaví.
     * @param hrac hráč, ktorý získa účinok potraviny
     * @param entita entita, pri ktorej sa potravina objaví
     * @param potravina obrázok reprezentujúci túto potravinu
     */
    public EnergetickyNapoj(Hrac hrac, Entita entita, Obrazok potravina) {
        super(entita, potravina);
        this.hrac = hrac;
        this.potravina = potravina;
    }
    /**
     * Vykoná účinok energetického nápoja — skryje ho a zvýši rýchlosť hráča.
     * Taktiež vypíše popis vylepšenia na konzolu.
     */
    @Override
    public void vykonajAbilitu() {
        this.potravina.skry();
        System.out.println(this.getVylepsenie());
        this.hrac.zvysRychlost();
    }
    /**
     * Vráti textový popis vylepšenia poskytovaného touto potravinou — zvýšenie rýchlosti.
     * @return popis vylepšenia
     */
    @Override
    public String getVylepsenie() {
        return super.getVylepsenie() + "rychlost";
    }
}
