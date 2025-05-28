package sk.uniza.fri.hra.entity;

import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
/**
 * Trieda ZivotyBossa zodpovedá za grafické znázornenie zdravia (životov) bossa v hre.
 * Zobrazí ukazovateľ s pozadím, hlavičkou bossa a mení jeho veľkosť podľa aktuálneho počtu životov bossa.
 */
public class ZivotyBossa {
    private final Obdlznik ukazatelZivota;
    private final Boss boss;
    private int velkostUkazatela;
    /**
     * Vytvorí inštanciu ukazateľa životov pre daného bossa.
     * Zobrazí pozadie, samotný ukazovateľ a obrázok hlavy bossa.
     * @param boss boss, ktorého životy budú reprezentované
     */
    public ZivotyBossa(Boss boss) {
        Obdlznik pozadieUkazatela = new Obdlznik();
        pozadieUkazatela.zmenFarbu("black");
        pozadieUkazatela.zmenPolohu(1800, 40);
        pozadieUkazatela.zmenStrany(32, 420);
        pozadieUkazatela.zobraz();
        this.ukazatelZivota = new Obdlznik();
        this.ukazatelZivota.zmenFarbu("yellow");
        this.ukazatelZivota.zmenPolohu(1808, 50);
        this.ukazatelZivota.zmenStrany(16, 400);
        this.ukazatelZivota.zobraz();
        Obrazok hlavaBossa = new Obrazok("resources/hlavaBossa.png");
        hlavaBossa.zmenPolohu(1792, 20);
        hlavaBossa.zobraz();
        this.velkostUkazatela = 400;
        this.boss = boss;
    }
    /**
     * Aktualizuje veľkosť ukazateľa života na základe aktuálneho počtu životov bossa.
     * Pri maximálnych 800 životoch má ukazovateľ veľkosť 400 pixelov.
     * Ak počet životov klesne pod 0, ukazovateľ sa nastaví na 0.
     */
    public void zmenUkazatelZivota() {
        int pocetZivotov = this.boss.getZivoty();
        if (pocetZivotov < 0) {
            pocetZivotov = 0;
        }
        this.velkostUkazatela = (int)((pocetZivotov / 1000.0) * 400);
        this.ukazatelZivota.zmenStrany(16, this.velkostUkazatela);
    }
}
