package sk.uniza.fri.hra.entity.vlastnosti;
import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

import java.util.Random;
/**
 * Trieda reprezentuje objekt UFO, ktorý sa pohybuje smerom nadol po obrazovke.
 * UFO sa zobrazí na náhodnej horizontálnej pozícii a po dosiahnutí spodnej hranice zmizne.
 */
public class Ufo {
    private final int polohaX;
    private int polohaY;
    private final Obrazok ufo;
    /**
     * Vytvorí nové UFO na náhodnej pozícii v hornej časti obrazovky
     * a zaregistruje ho do správcu pohybu (Manazer).
     */
    public Ufo() {
        Random random = new Random();
        this.polohaX = random.nextInt(1801);
        this.polohaY = 10;
        this.ufo = new Obrazok("resources/ufo.png");
        this.ufo.zmenPolohu(this.polohaX, this.polohaY);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    /**
     * Získa aktuálnu X-ovú pozíciu UFO.
     * @return horizontálna pozícia UFO
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získa aktuálnu Y-ovú pozíciu UFO.
     * @return vertikálna pozícia UFO
     */
    public int getPolohaY() {
        return this.polohaY;
    }
    /**
     * Posunie UFO smerom nadol. Ak dosiahne spodnú hranicu obrazovky,
     * objekt sa skryje.
     */
    public void letObjektu() {
        if (this.polohaY >= 830) {
            this.znicUfo();
        } else {
            this.ufo.zobraz();
            this.ufo.posunZvisle(10);
            this.polohaY += 10;
        }
    }
    /**
     * Skryje UFO z obrazovky.
     */
    public void znicUfo() {
        this.ufo.skry();
    }
}

