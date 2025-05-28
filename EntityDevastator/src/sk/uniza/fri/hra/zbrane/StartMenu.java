package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
/**
 * Trieda StartMenu reprezentuje úvodné menu hry s tlačidlom "Štart".
 * Zobrazuje pozadie a reaguje na pohyb a kliknutie myši.
 * Ak hráč klikne do určeného oblasti tlačidla, hra sa začne.
 */
public class StartMenu {
    private final Obrazok pozadie;
    private int polohaMysiX;
    private int polohaMysiY;
    private boolean zacalaHra;
    /**
     * Vytvorí nové štartovacie menu, zobrazí jeho pozadie a zaregistruje objekt u manažéra vstupov.
     */
    public StartMenu() {
        this.pozadie = new Obrazok("resources/startMenu.jpg");
        this.pozadie.zmenPolohu(0, 0);
        this.pozadie.zobraz();
        this.zacalaHra = false;
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    /**
     * Overí, či bolo kliknuté na tlačidlo "Štart" a ak áno, začne hru.
     *
     * @param x súradnica X kliknutia myši
     * @param y súradnica Y kliknutia myši
     */
    public void zakupPolozku(int x, int y) {
        if (this.polohaMysiX >= 710 && this.polohaMysiX <= 1210 && this.polohaMysiY >= 670 && this.polohaMysiY <= 790) {
            this.zacalaHra = true;
            this.pozadie.skry();
        }
    }
    /**
     * Zaznamená aktuálnu polohu myši.
     *
     * @param x súradnica X myši
     * @param y súradnica Y myši
     */
    public void hybMysou(int x, int y) {
        this.polohaMysiX = x;
        this.polohaMysiY = y;
    }
    /**
     * Zistí, či hra bola spustená kliknutím na tlačidlo.
     *
     * @return {@code true}, ak hráč klikol na "Štart" a hra sa začala, inak {@code false}
     */
    public boolean getZacalaHra() {
        return this.zacalaHra;
    }
}
