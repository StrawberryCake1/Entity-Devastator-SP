package sk.uniza.fri.hra.zbrane;
import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
/**
 * Trieda Naboj reprezentuje vystrelený projektil zo zbrane.
 * Každý náboj má svoju grafickú reprezentáciu, pozíciu a smer pohybu.
 */
public class Naboj {
    private final Obdlznik naboj;
    private final Obdlznik obryaNaboja;
    private int polohaX;
    private final int polohaY;
    private final boolean pozeraSaVPravo;
    private final int poskodenie;
    /**
     * Vytvorí nový náboj na základe polohy a smeru danej zbrane.
     * Automaticky ho zobrazí a zaregistruje pre správu {@code Manazer}-om.
     *
     * @param zbran inštancia zbrane, z ktorej náboj vychádza
     */
    public Naboj(Zbran zbran) {
        this.naboj = new Obdlznik();
        this.naboj.zmenStrany(16, 4);
        this.naboj.zmenFarbu("yellow");
        this.obryaNaboja = new Obdlznik();
        this.obryaNaboja.zmenFarbu("black");
        this.obryaNaboja.zmenStrany(20, 8);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
        this.pozeraSaVPravo = zbran.getPozeraSaVpravo();
        if (this.pozeraSaVPravo) {
            this.obryaNaboja.zmenPolohu(zbran.getPolohaX() + 60, zbran.getPolohaY() + 25);
            this.naboj.zmenPolohu(zbran.getPolohaX() + 60, zbran.getPolohaY() + 25);
            this.polohaX = zbran.getPolohaX() + 60;
        } else {
            this.obryaNaboja.zmenPolohu(zbran.getPolohaX() + 20  , zbran.getPolohaY() + 25);
            this.naboj.zmenPolohu(zbran.getPolohaX() + 20, zbran.getPolohaY() + 25);
            this.polohaX = zbran.getPolohaX() + 20;
        }
        this.polohaY = zbran.getPolohaY() + 25;
        this.poskodenie = zbran.getPoskodenie();
        this.obryaNaboja.zobraz();
        this.naboj.zobraz();
    }
    /**
     * Vráti hodnotu poškodenia, ktoré tento náboj spôsobí pri zásahu.
     *
     * @return hodnota poškodenia
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Posunie náboj v smere výstrelu. Ak sa dostane mimo viditeľnú oblasť,
     * automaticky sa skryje.
     */
    public void vystrel() {
        if (this.pozeraSaVPravo) {
            this.obryaNaboja.posunVodorovne(10);
            this.naboj.posunVodorovne(10);
            this.polohaX += 10;
            if (this.polohaX > 2000) {
                this.skryNaboj();
            }
        } else {
            this.obryaNaboja.posunVodorovne(-10);
            this.naboj.posunVodorovne(-10);
            this.polohaX -= 10;
            if (this.polohaX < -100) {
                this.skryNaboj();
            }
        }
    }
    /**
     * Skryje náboj aj jeho obrys.
     */
    public void skryNaboj() {
        this.naboj.skry();
        this.obryaNaboja.skry();
    }
    /**
     * Posunie náboj zvisle o daný počet pixelov.
     *
     * @param kolko počet pixelov, o ktoré sa má náboj posunúť zvisle
     */
    public void posunZvisle(int kolko) {
        this.naboj.posunZvisle(kolko);
        this.obryaNaboja.posunZvisle(kolko);
    }
    /**
     * Vráti X-ovú súradnicu náboja.
     *
     * @return aktuálna pozícia X
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Vráti Y-ovú súradnicu náboja.
     *
     * @return aktuálna pozícia Y
     */

    public int getPolohaY() {
        return this.polohaY;
    }
}
