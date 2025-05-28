package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.Hrac;
import sk.uniza.fri.hra.Menezovatelne;
/**
 * Abstraktná trieda Zbran reprezentuje spoločné vlastnosti a správanie všetkých zbraní v hre.
 * Každá zbraň je vizuálne zobrazená pomocou dvoch obrázkov (pre pohľad vpravo a vľavo) a je naviazaná na hráča,
 * pričom sa dynamicky prispôsobuje jeho pozícii a smeru pohľadu.
 */
public abstract class Zbran implements Menezovatelne {
    private final Hrac hrac;
    private int poskodenie;
    private final Obrazok zbran;
    private final Obrazok obratenaZbran;
    private int polohaX;
    private int polohaY;
    private boolean jeMomentalneDrzana;
    private boolean jeZvysenePoskodenie;
    private boolean pozeraSaVPravo;
    /**
     * Vytvára novú zbraň s daným typom a priraďuje ju hráčovi.
     * Inicializuje pozíciu, obrázky zbrane a nastaví manažér pre aktualizáciu objektu.
     *
     * @param hrac Hráč, ktorému zbraň patrí
     * @param typZbrane Typ zbrane, ktorý určuje vizuál a počiatočné poškodenie
     */
    public Zbran(Hrac hrac, TypZbrane typZbrane) {
        this.hrac = hrac;
        this.poskodenie = typZbrane.getPoskodenie();

        this.polohaX = hrac.getPolohaX();
        this.polohaY = hrac.getPolohaY();
        this.zbran = new Obrazok(typZbrane.getObrazok());

        this.zbran.zmenUhol(90);
        this.obratenaZbran = new Obrazok(typZbrane.getObratenyObrazok());
        this.obratenaZbran.zmenUhol(270);

        this.zbran.zmenPolohu(this.polohaX - typZbrane.getOdchylkaX(), this.polohaY + typZbrane.getOdchylkaY());
        this.obratenaZbran.zmenPolohu(this.polohaX + typZbrane.getOdchylkaX(), this.polohaY + typZbrane.getOdchylkaY());

        if (this instanceof UtocnaPuska) {
            this.zbran.posunVodorovne(-60);
        }
        this.pozeraSaVPravo = this.hrac.getPozeraSaVPravo();
        this.jeMomentalneDrzana = false;
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }
    /**
     * Nastaví novú polohu zbrane na základe posunutia o hodnoty x a y.
     *
     * @param x Horizontálne posunutie
     * @param y Vertikálne posunutie
     */
    public void zmenPolohu(int x, int y) {
        this.zbran.zmenPolohu(x, y);
        this.obratenaZbran.zmenPolohu(x, y);
        this.polohaX += x;
        this.polohaY += y;
    }
    /**
     * Nastaví, či je zbraň aktuálne držaná hráčom.
     *
     * @param jeDrzana true, ak je zbraň držaná; false inak
     */
    public void setJeMomentalneDrzana(boolean jeDrzana) {
        this.jeMomentalneDrzana = jeDrzana;
    }
    /**
     * Zistí, či je zbraň aktuálne držaná hráčom.
     *
     * @return true, ak je držaná; false inak
     */
    public boolean getJeMomentalneDrzana()  {
        return this.jeMomentalneDrzana;
    }
    /**
     * Zobrazí obidve verzie zbrane (pravá a ľavá). Pre zobrazenie podľa smeru pohľadu použite {@link #aktualizujPoziciu()}.
     */
    public void zobrazZbran() {
        this.zbran.zobraz();
        this.obratenaZbran.zobraz();
    }
    /**
     * Skryje obidve verzie zbrane.
     */
    public void skryZbran() {
        this.zbran.skry();
        this.obratenaZbran.skry();
    }
    /**
     * Zistí, či sa hráč (a teda zbraň) pozerá vpravo.
     *
     * @return true, ak sa pozerá vpravo; false inak
     */
    public boolean getPozeraSaVpravo() {
        return this.pozeraSaVPravo;
    }
    /**
     * Aktualizuje polohu a zobrazenie zbrane podľa aktuálnej pozície hráča a smeru pohľadu.
     */
    public void aktualizujPoziciu() {
        this.pozeraSaVPravo = this.hrac.getPozeraSaVPravo();
        if (this.pozeraSaVPravo) {
            if (this.jeMomentalneDrzana) {
                this.zbran.zobraz();
                this.obratenaZbran.skry();
            } else {
                this.zbran.skry();
                this.obratenaZbran.skry();
            }
        } else {
            if (this.jeMomentalneDrzana) {
                this.zbran.skry();
                this.obratenaZbran.zobraz();
            } else {
                this.zbran.skry();
                this.obratenaZbran.skry();
            }
        }
        this.zbran.posunVodorovne(this.hrac.getPolohaX() - this.polohaX);
        this.zbran.posunZvisle(this.hrac.getPolohaY() - this.polohaY);
        this.obratenaZbran.posunVodorovne(this.hrac.getPolohaX() - this.polohaX);
        this.obratenaZbran.posunZvisle(this.hrac.getPolohaY() - this.polohaY);
        this.polohaX += this.hrac.getPolohaX() - this.polohaX;
        this.polohaY += this.hrac.getPolohaY() - this.polohaY;
    }
    /**
     * Získa aktuálnu horizontálnu polohu zbrane.
     * @return poloha X
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získa aktuálnu vertikálnu polohu zbrane.
     * @return poloha Y
     */
    public int getPolohaY() {
        return this.polohaY;
    }
    /**
     * Zdvojnásobí poškodenie zbrane a označí, že má zvýšené poškodenie.
     */
    public void zvysPoskodenie() {
        this.setPoskodenie(this.poskodenie * 2);
        this.jeZvysenePoskodenie = true;
    }
    /**
     * Zníži poškodenie zbrane na polovicu a zruší zvýšený stav poškodenia.
     */
    public void znizPoskodenie() {
        this.setPoskodenie(this.poskodenie / 2);
        this.jeZvysenePoskodenie = false;
    }
    /**
     * Získa aktuálnu hodnotu poškodenia zbrane.
     * @return poškodenie
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Nastaví novú hodnotu poškodenia zbrane.
     *
     * @param novePoskodenie Nová hodnota poškodenia
     */
    public void setPoskodenie(int novePoskodenie) {
        this.poskodenie = novePoskodenie;
    }
    /**
     * Zistí, či má zbraň aktuálne zvýšené poškodenie.
     *
     * @return true, ak je poškodenie zvýšené; false inak
     */
    public boolean getJeZvysenePoskodenie() {
        return this.jeZvysenePoskodenie;
    }
}
