package sk.uniza.fri.hra.entity;
import sk.uniza.fri.hra.Hrac;
/**
 * Trieda Duch reprezentuje entitu ducha v hre.
 * Duch je typ nepriateľa, ktorý dedí všetku funkcionalitu od rodičovskej triedy {@link Entita},
 * no neobsahuje žiadne špeciálne vlastnosti alebo správanie.
 * V budúcnosti môže byť rozšírený o unikátne schopnosti špecifické pre ducha.
 */
public class Duch extends Entita {
    /**
     * Vytvorí novú inštanciu ducha s referenciou na hráča, počtom životov a typom entity.
     * @param hrac hráč, ktorý je cieľom ducha
     * @param zivot počiatočný počet životov ducha
     * @param typEntity typ entity, reprezentujúci vizuál a vlastnosti ducha
     */
    public Duch(Hrac hrac, int zivot, TypEntity typEntity) {
        super(hrac, zivot, typEntity);
    }

}
