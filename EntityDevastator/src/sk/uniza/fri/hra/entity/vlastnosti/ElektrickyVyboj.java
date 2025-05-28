package sk.uniza.fri.hra.entity.vlastnosti;

import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
import sk.uniza.fri.hra.entity.Robot;
/**
 * Trieda reprezentuje elektrický výboj, ktorý vytvára robot.
 * Výboj sa pohybuje horizontálne buď doprava alebo doľava
 * a automaticky sa skryje, keď opustí obrazovku.
 */
public class ElektrickyVyboj {
    private final Obdlznik vyboj;
    private final Obdlznik obrysVyboju;
    private int polohaX;
    private final int polohaY;
    private final boolean vpravo;
    /**
     * Vytvorí nový elektrický výboj na základe pozície robota a smeru streľby.
     * Zaregistruje výboj do správcu objektov (Manazer), aby sa mohol automaticky aktualizovať.
     * @param robot  Robot, ktorý výboj vystrelil
     * @param vpravo True, ak sa má výboj pohybovať doprava, inak doľava
     */
    public ElektrickyVyboj(Robot robot, boolean vpravo) {
        this.obrysVyboju = new Obdlznik();
        this.obrysVyboju.zmenStrany(20, 8);
        this.obrysVyboju.zmenFarbu("black");
        this.obrysVyboju.zmenPolohu(robot.getPolohaX() + 200, robot.getPolohaY() + 120);
        this.obrysVyboju.zobraz();
        this.vyboj = new Obdlznik();
        this.vyboj.zmenStrany(16, 4);
        this.vyboj.zmenPolohu(robot.getPolohaX() + 200, robot.getPolohaY() + 120);
        this.vyboj.zmenFarbu("blue");
        this.vyboj.zobraz();
        this.vpravo = vpravo;
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
        this.polohaX = robot.getPolohaX() + 200;
        this.polohaY = robot.getPolohaY() + 120;
    }
    /**
     * Posunie výboj v smere podľa nastavenia (doprava alebo doľava).
     * Ak výboj opustí obrazovku, automaticky sa skryje.
     */
    public void vystrel() {
        if (this.vpravo) {
            this.obrysVyboju.posunVodorovne(2);
            this.vyboj.posunVodorovne(2);
            this.polohaX += 2;
            if (this.polohaX > 2000) {
                this.skryVyboj();
            }
        } else {
            this.obrysVyboju.posunVodorovne(-2);
            this.vyboj.posunVodorovne(-2);
            this.polohaX -= 2;
            if (this.polohaX < -2000) {
                this.skryVyboj();
            }
        }
    }
    /**
     * Skryje výboj aj jeho obrys z obrazovky.
     */
    public void skryVyboj() {
        this.vyboj.skry();
        this.obrysVyboju.skry();
    }
    /**
     * Posunie výboj a jeho obrys vertikálne o zadanú hodnotu.
     * @param kolko počet pixelov, o ktoré sa má výboj posunúť vertikálne
     */
    public void posunZvisle(int kolko) {
        this.vyboj.posunZvisle(kolko);
        this.obrysVyboju.posunZvisle(kolko);
    }
    /**
     * Získa aktuálnu horizontálnu pozíciu výboja.
     * @return hodnota X pozície výboja
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získa aktuálnu vertikálnu pozíciu výboja.
     * @return hodnota Y pozície výboja
     */

    public int getPolohaY() {
        return this.polohaY;
    }
}
