package sk.uniza.fri.hra;

import fri.shapesge.Obrazok;

/**
 * Trieda Zivot reprezentuje graficke zobrazenie zivota hraca v hre.
 * Pouziva obrazok ako vizualny indikator zivota.
 */
public class Zivot {
    private final Obrazok zivot;

    /**
     * Vytvori novy objekt Zivot s obrazkom na zadanej pozicii.
     *
     * @param polohaX horizontalna pozicia obrazka
     * @param polohaY verticalna pozicia obrazka
     */
    public Zivot(int polohaX, int polohaY) {
        this.zivot = new Obrazok("resources/zivot.png");
        this.zivot.zmenPolohu(polohaX, polohaY);
        this.zivot.zobraz();
    }

    /**
     * Skryje obrazok zivota z obrazovky.
     */
    public void skryZivot() {
        this.zivot.skry();
    }

    /**
     * Zobrazi obrazok zivota na obrazovke.
     */
    public void zobrazZivot() {
        this.zivot.zobraz();
    }
}
