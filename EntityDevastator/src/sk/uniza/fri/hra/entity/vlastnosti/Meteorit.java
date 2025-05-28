package sk.uniza.fri.hra.entity.vlastnosti;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

import java.util.Random;
/**
 * Trieda reprezentuje meteorit, ktorý padá zhora nadol na náhodnej horizontálnej pozícii.
 * Meteorit sa automaticky pohybuje smerom nadol a po dosiahnutí určenej hranice zmizne.
 */
public class Meteorit {
    private final int polohaX;
    private int polohaY;
    private Obrazok meteorit;
    /**
     * Vytvorí nový meteorit s náhodnou horizontálnou pozíciou.
     * Zaregistruje objekt do správcu (Manazer) na automatické spravovanie pohybu.
     */
    public Meteorit() {
        Random random = new Random();
        this.polohaX = random.nextInt(1801);
        this.polohaY = 10;
        this.meteorit = new Obrazok("resources/meteor.png");
        this.meteorit.zmenPolohu(this.polohaX, this.polohaY);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    /**
     * Získa aktuálnu horizontálnu pozíciu meteoritu.
     * @return X-ová pozícia meteoritu
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získa aktuálnu vertikálnu pozíciu meteoritu.
     * @return Y-ová pozícia meteoritu
     */
    public int getPolohaY() {
        return this.polohaY;
    }
    /**
     * Posunie meteorit smerom nadol. Ak dosiahne spodnú hranicu (830),
     * automaticky sa zničí (skryje).
     */
    public void letObjektu() {
        if (this.polohaY >= 830) {
            this.znicMeteorit();
        } else {
            this.meteorit.zobraz();
            this.meteorit.posunZvisle(10);
            this.polohaY += 10;
        }
    }
    /**
     * Skryje meteorit z obrazovky a zneplatní jeho referenciu.
     */
    public void znicMeteorit() {
        if (this.meteorit != null) {
            this.meteorit.skry();
            this.meteorit = null;
        }
    }
}
