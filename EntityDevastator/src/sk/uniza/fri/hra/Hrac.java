package sk.uniza.fri.hra;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.zbrane.Zbran;
import sk.uniza.fri.hra.zbrane.Pistol;
import sk.uniza.fri.hra.zbrane.Samopal;
import sk.uniza.fri.hra.zbrane.UtocnaPuska;
import sk.uniza.fri.hra.zbrane.Brokovnica;
import sk.uniza.fri.hra.zbrane.TypZbrane;

import java.util.HashMap;
/**
 * Trieda Hrac reprezentuje hráča v hre.
 * Obsahuje logiku pohybu, správy zbraní, životov, rýchlosti a interakcií s manažérom.
 */
public class Hrac implements Menezovatelne {
    private final Obrazok hrac;
    private final Obrazok obratenyHrac;
    private int polohaX;
    private int polohaY;
    private final HashMap<TypZbrane, Zbran> inventar;
    private boolean pozeraSaVPravo;
    private int pocetZivotov;
    private Zbran aktualnaZbran;

    private TypZbrane aktualnyTypZbrane;

    private boolean jeZvysenRychlost;
    private int counterZvyseniaRychlosti;
    /**
     * Vytvára nového hráča, nastavuje jeho počiatočnú polohu, obrázky a základnú výbavu (pištoľ).
     */
    public Hrac() {
        this.polohaX = 920;
        this.polohaY = 500;
        this.hrac = new Obrazok("resources/hrac.png");
        this.hrac.zmenPolohu(this.polohaX , this.polohaY);
        this.hrac.zobraz();

        this.obratenyHrac = new Obrazok("resources/obratenyHrac.png");
        this.obratenyHrac.zmenPolohu(this.polohaX, this.polohaY);

        this.inventar = new HashMap<>();
        Pistol pistol = new Pistol(this, TypZbrane.PISTOL);

        this.inventar.put(TypZbrane.PISTOL, pistol);

        this.pozeraSaVPravo = true;
        this.pocetZivotov = 6;

        this.aktualnyTypZbrane = TypZbrane.PISTOL;
        this.aktualnaZbran = pistol;
        this.jeZvysenRychlost = false;
        this.counterZvyseniaRychlosti = 0;
    }
    /**
     * Získava aktuálny typ držanej zbrane.
     *
     * @return Typ aktuálnej zbrane.
     */
    public TypZbrane getAktualnyTypZbrane() {
        return this.aktualnyTypZbrane;
    }
    /**
     * Pridá do inventára zbraň, ak ju hráč ešte nevlastní.
     *
     * @param typ Typ zbrane, ktorú chce hráč zakúpiť.
     */
    public void zakupZbran(TypZbrane typ) {
        if (this.inventar.containsKey(typ)) {
            System.out.println("Zbraň už vlastníš.");
            return;
        }

        switch (typ) {
            case SAMOPAl -> this.inventar.put(typ, new Samopal(this, typ));
            case BROKOVNICA -> this.inventar.put(typ, new Brokovnica(this, typ));
            case UTOCNAPUSKA -> this.inventar.put(typ, new UtocnaPuska(this, typ));
        }
    }
    /**
     * Získava počet životov hráča.
     *
     * @return Aktuálny počet životov.
     */
    public int getZivoty() {
        return this.pocetZivotov;
    }
    /**
     * Posunie hráča smerom dole, berúc do úvahy zvýšenú rýchlosť a hranice.
     */
    public void posunDole() {
        if (this.polohaY >= 830) {
            this.hrac.posunZvisle(-30);
            this.obratenyHrac.posunZvisle(-30);
            this.polohaY -= 30;
        }
        if (this.jeZvysenRychlost) {
            this.hrac.posunZvisle(30);
            this.obratenyHrac.posunZvisle(30);
            this.polohaY += 30;
            this.counterZvyseniaRychlosti++;
            if (this.counterZvyseniaRychlosti == 50) {
                this.znizRychlost();
                this.counterZvyseniaRychlosti = 0;
            }
        } else {
            this.hrac.posunZvisle(15);
            this.obratenyHrac.posunZvisle(15);
            this.polohaY += 15;
        }
        if (this.getPolohaY() >= 1080) {
            this.hrac.posunHore();
        }
    }
    /**
     * Posunie hráča smerom hore, berúc do úvahy zvýšenú rýchlosť a hranice.
     */
    public void posunHore() {
        if (this.polohaY <= 10) {
            this.hrac.posunZvisle(30);
            this.obratenyHrac.posunZvisle(30);
            this.polohaY += 30;
        }
        if (this.jeZvysenRychlost) {
            this.hrac.posunZvisle(-30);
            this.obratenyHrac.posunZvisle(-30);
            this.polohaY -= 30;
            this.counterZvyseniaRychlosti++;
            if (this.counterZvyseniaRychlosti == 50) {
                this.znizRychlost();
                this.counterZvyseniaRychlosti = 0;
            }
        } else {
            this.hrac.posunZvisle(-15);
            this.obratenyHrac.posunZvisle(-15);
            this.polohaY -= 15;
        }
    }
    /**
     * Posunie hráča smerom doprava, berúc do úvahy zvýšenú rýchlosť a hranice.
     */
    public void posunVpravo() {
        if (this.polohaX >= 1850) {
            this.hrac.posunVodorovne(-30);
            this.obratenyHrac.posunVodorovne(-30);
            this.polohaX -= 30;
        }
        if (this.jeZvysenRychlost) {
            this.hrac.posunVodorovne(30);
            this.obratenyHrac.posunVodorovne(30);
            this.polohaX += 30;
            this.hrac.zobraz();
            this.obratenyHrac.skry();
            this.pozeraSaVPravo = true;
            this.counterZvyseniaRychlosti++;
            if (this.counterZvyseniaRychlosti == 50) {
                this.znizRychlost();
                this.counterZvyseniaRychlosti = 0;
            }
        } else {
            this.hrac.posunVodorovne(15);
            this.obratenyHrac.posunVodorovne(15);
            this.hrac.zobraz();
            this.obratenyHrac.skry();
            this.pozeraSaVPravo = true;
            this.polohaX += 15;
        }
    }
    /**
     * Posunie hráča smerom doľava, berúc do úvahy zvýšenú rýchlosť a hranice.
     */
    public void posunVlavo() {
        if (this.polohaX <= -40) {
            this.hrac.posunVodorovne(30);
            this.obratenyHrac.posunVodorovne(30);
            this.polohaX += 30;
        }
        if (this.jeZvysenRychlost) {
            this.hrac.posunVodorovne(-30);
            this.obratenyHrac.posunVodorovne(-30);
            this.polohaX -= 30;
            this.hrac.skry();
            this.obratenyHrac.zobraz();
            this.pozeraSaVPravo = false;
            this.counterZvyseniaRychlosti++;
            if (this.counterZvyseniaRychlosti == 50) {
                this.znizRychlost();
                this.counterZvyseniaRychlosti = 0;
            }
        } else {
            this.hrac.posunVodorovne(-15);
            this.obratenyHrac.posunVodorovne(-15);
            this.hrac.skry();
            this.obratenyHrac.zobraz();
            this.pozeraSaVPravo = false;
            this.polohaX -= 15;
        }
    }
    /**
     * Získava horizontálnu polohu hráča.
     *
     * @return Horizontálna pozícia (X).
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získava vertikálnu polohu hráča.
     *
     * @return Vertikálna pozícia (Y).
     */

    public int getPolohaY() {
        return this.polohaY;
    }
    /**
     * Reaguje na útok znížením počtu životov, ak sú k dispozícii.
     */
    public void prijmiUtok() {
        if (this.pocetZivotov > 0) {
            this.pocetZivotov--;
        }
    }
    /**
     * Získava aktuálne držiacu zbraň hráča.
     *
     * @return Objekt zbrane.
     */
    public Zbran getAktualnaZbran() {
        return this.aktualnaZbran;
    }
    /**
     * Nastaví aktuálnu zbraň na pištoľ, ak ju hráč vlastní.
     */
    public void zvolPistol() {
        TypZbrane typ = TypZbrane.PISTOL;
        Zbran zbran = this.inventar.get(typ);
        if (zbran != null) {
            for (Zbran z : this.inventar.values()) {
                z.skryZbran();
                z.setJeMomentalneDrzana(false);
            }
            zbran.zobrazZbran();
            zbran.setJeMomentalneDrzana(true);
            this.aktualnaZbran = zbran;
            this.aktualnyTypZbrane = typ;
        }
    }

    /**
     * Nastaví aktuálnu zbraň na samopal, ak ho hráč vlastní.
     */
    public void zvolSamopal() {
        TypZbrane typ = TypZbrane.SAMOPAl;
        Zbran zbran = this.inventar.get(typ);
        if (zbran != null) {
            for (Zbran z : this.inventar.values()) {
                z.skryZbran();
                z.setJeMomentalneDrzana(false);
            }
            zbran.zobrazZbran();
            zbran.setJeMomentalneDrzana(true);
            this.aktualnaZbran = zbran;
            this.aktualnyTypZbrane = typ;
        }
    }
    /**
     * Nastaví aktuálnu zbraň na brokovnicu, ak ju hráč vlastní.
     */
    public void zvolBrokovnicu() {
        TypZbrane typ = TypZbrane.BROKOVNICA;
        Zbran zbran = this.inventar.get(typ);
        if (zbran != null) {
            for (Zbran z : this.inventar.values()) {
                z.skryZbran();
                z.setJeMomentalneDrzana(false);
            }
            zbran.zobrazZbran();
            zbran.setJeMomentalneDrzana(true);
            this.aktualnaZbran = zbran;
            this.aktualnyTypZbrane = typ;
        }
    }
    /**
     * Nastaví aktuálnu zbraň na útočnú pušku, ak ju hráč vlastní.
     */
    public void zvolUtocnuPusku() {
        TypZbrane typ = TypZbrane.UTOCNAPUSKA;
        Zbran zbran = this.inventar.get(typ);
        if (zbran != null) {
            for (Zbran z : this.inventar.values()) {
                z.skryZbran();
                z.setJeMomentalneDrzana(false);
            }
            zbran.zobrazZbran();
            zbran.setJeMomentalneDrzana(true);
            this.aktualnaZbran = zbran;
            this.aktualnyTypZbrane = typ;
        }
    }
    /**
     * Aktivuje zvýšenú rýchlosť hráča.
     */
    public void zvysRychlost() {
        this.jeZvysenRychlost = true;
    }
    /**
     * Deaktivuje zvýšenú rýchlosť hráča.
     */
    public void znizRychlost() {
        this.jeZvysenRychlost = false;
    }
    /**
     * Zvyšuje počet životov hráča, ak ešte nedosiahol maximum.
     */
    public void zvysZivot() {
        if (this.pocetZivotov < 6) {
            this.pocetZivotov++;
        }
    }
    /**
     * Zvyšuje poškodenie aktuálnej zbrane, ak je k dispozícii.
     */
    public void zvysPoskodenie() {
        if (this.aktualnaZbran != null) {
            this.aktualnaZbran.zvysPoskodenie();
        }
    }
    /**
     * Zisťuje, či sa hráč pozerá vpravo.
     * @return true, ak sa pozerá vpravo; inak false.
     */
    public boolean getPozeraSaVPravo() {
        return this.pozeraSaVPravo;
    }
    /**
     * Manažér začne spracovovať hráča.
     *
     * @param manazer Manažér, ktorý bude hráča spravovať.
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Manažér prestane spracovovať hráča.
     *
     * @param manazer Manažér, ktorý hráča prestane spravovať.
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer)  {
        manazer.prestanSpravovatObjekt(this);
    }
}