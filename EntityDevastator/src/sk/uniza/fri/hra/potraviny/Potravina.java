package sk.uniza.fri.hra.potraviny;

import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.entity.Entita;
/**
 * Abstraktná trieda Potravina reprezentuje objekt potraviny,
 * ktorý sa môže náhodne zobraziť na pozícii nejakej Entity
 * a môže poskytnúť určitú schopnosť alebo vylepšenie.
 */
public abstract class Potravina {
    private int polohaX;
    private int polohaY;
    private final Entita entita;
    private final Obrazok potravina;
    /**
     * Vytvorí novú potravinu viazanú na konkrétnu entitu a jej obrázok.
     * @param entita entita, pri ktorej sa potravina zobrazí
     * @param potravina obrázok reprezentujúci potravinu
     */
    public Potravina(Entita entita, Obrazok potravina) {
        this.entita = entita;
        this.potravina = potravina;
        this.polohaX = 0;
        this.polohaY = 0;
    }
    /**
     * Náhodne zobrazí potravinu na pozícii entity.
     * Potravina má približne 70 % šancu sa zobraziť.
     */
    public void nahodneSaZobraz() {
        var min = 1;
        var max = 10;
        var rand = (int)(Math.random() * (max - min + 1)) + min ;
        if (rand < 8) {
            this.potravina.zmenPolohu(this.entita.getPolohaX(), this.entita.getPolohaY());
            this.polohaX += this.entita.getPolohaX();
            this.polohaY += this.entita.getPolohaY();
            this.potravina.zobraz();
        }
    }
    /**
     * Vráti textový popis vylepšenia potraviny.
     * @return reťazec popisujúci účinok potraviny
     */
    public String getVylepsenie() {
        return "Vylepsenie: ";
    }
    /**
     * Abstraktná metóda, ktorú musí implementovať každá konkrétna potravina.
     * Určuje efekt potraviny po jej konzumácii.
     */
    public abstract void vykonajAbilitu();
    /**
     * Vráti horizontálnu pozíciu potraviny.
     * @return X súradnica
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Vráti vertikálnu pozíciu potraviny.
     * @return Y súradnica
     */
    public int getPolohaY() {
        return this.polohaY;
    }
}
