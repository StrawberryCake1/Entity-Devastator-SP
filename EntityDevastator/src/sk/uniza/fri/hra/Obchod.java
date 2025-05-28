package sk.uniza.fri.hra;

import fri.shapesge.BlokTextu;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import fri.shapesge.Manazer;
import sk.uniza.fri.hra.zbrane.TypZbrane;
/**
 * Trieda Obchod reprezentuje obchodné rozhranie v hre, kde si hráč môže zakúpiť zbrane.
 * Zobrazuje vizuálne prvky a spracúva kliknutia myšou na zakúpenie zbraní podľa dostupných financií.
 * Implementuje rozhranie  pre správu objektov cez Manazer.
 */
public class Obchod implements Menezovatelne {
    private final BlokTextu nadpis;
    private final BlokTextu popisPreSamopal;
    private final BlokTextu popisPreBrokovnicu;
    private final BlokTextu popisPreUtocnuPusku;
    private final Hrac hrac;
    private final Obdlznik pozadie;
    private final Obdlznik polickoPreSamopal;
    private final Obdlznik polickoPreBrokovnicu;
    private final Obdlznik polickoPreUtocnuPusku;
    private final Obrazok obrazokSamopalu;
    private final Obrazok obrazokBrokovnice;
    private final Obrazok obrazokUtocnejPusky;
    private final HerneMenu herneMenu;
    private  int polohaMysiX;
    private int polohaMysiY;
    private boolean jeObchodZobrazeny;
    /**
     * Vytvára novú inštanciu obchodu pre daného hráča a herné menu.
     * Inicializuje a zobrazí všetky grafické komponenty obchodu.
     * @param hrac hráč, ktorý bude môcť nakupovať
     * @param herneMenu referenca na herné menu s informáciou o financiách
     */
    public Obchod(Hrac hrac, HerneMenu herneMenu) {
        this.pozadie = new Obdlznik();
        this.pozadie.zmenFarbu("black");
        this.pozadie.zmenStrany(1920, 1080);
        this.pozadie.zobraz();
        this.pozadie.zmenPolohu(0, 0);
        this.nadpis = new BlokTextu("OBCHOD");
        this.nadpis.zmenFont("BOLD", StylFontu.BOLD, 50);
        this.nadpis.zmenPolohu(800, 100);
        this.nadpis.zmenFarbu("white");
        this.nadpis.zobraz();
        this.hrac = hrac;
        this.herneMenu = herneMenu;
        //Samopal
        this.popisPreSamopal = new BlokTextu("SAMOPAL CENA - 1500€");
        this.popisPreSamopal.zmenFont("BOLD", StylFontu.BOLD, 40);
        this.popisPreSamopal.zmenPolohu(800, 280);
        this.popisPreSamopal.zmenFarbu("red");
        this.popisPreSamopal.zobraz();
        this.polickoPreSamopal = new Obdlznik();
        this.polickoPreSamopal.zmenStrany(120, 120);
        this.polickoPreSamopal.zmenFarbu("red");
        this.polickoPreSamopal.zmenPolohu(600, 200);
        this.polickoPreSamopal.zobraz();

        this.obrazokSamopalu = new Obrazok("resources/samopalPreMenu.png");
        this.obrazokSamopalu.zmenUhol(45);
        this.obrazokSamopalu.zmenPolohu(530, 190);
        this.obrazokSamopalu.zobraz();

        //Brokovnica
        this.popisPreBrokovnicu = new BlokTextu("BROKOVNICA CENA - 3000€");
        this.popisPreBrokovnicu.zmenFont("BOLD", StylFontu.BOLD, 40);
        this.popisPreBrokovnicu.zmenPolohu(800, 580);
        this.popisPreBrokovnicu.zmenFarbu("blue");
        this.popisPreBrokovnicu.zobraz();
        this.polickoPreBrokovnicu = new Obdlznik();
        this.polickoPreBrokovnicu.zmenStrany(120, 120);
        this.polickoPreBrokovnicu.zmenFarbu("blue");
        this.polickoPreBrokovnicu.zmenPolohu(600, 500);
        this.polickoPreBrokovnicu.zobraz();

        this.obrazokBrokovnice = new Obrazok("resources/brokovnicaPreMenu.png");
        this.obrazokBrokovnice.zmenUhol(45);
        this.obrazokBrokovnice.zmenPolohu(540, 470);
        this.obrazokBrokovnice.zobraz();

        //Utocna puska
        this.popisPreUtocnuPusku = new BlokTextu("UTOCNA PUSKA CENA - 5000€");
        this.popisPreUtocnuPusku.zmenFont("BOLD", StylFontu.BOLD, 40);
        this.popisPreUtocnuPusku.zmenPolohu(800, 880);
        this.popisPreUtocnuPusku.zmenFarbu("yellow");
        this.popisPreUtocnuPusku.zobraz();
        this.polickoPreUtocnuPusku = new Obdlznik();
        this.polickoPreUtocnuPusku.zmenStrany(120, 120);
        this.polickoPreUtocnuPusku.zmenFarbu("yellow");
        this.polickoPreUtocnuPusku.zmenPolohu(600, 800);
        this.polickoPreUtocnuPusku.zobraz();

        this.obrazokUtocnejPusky = new Obrazok("resources/utocnaPuskaPreMenu.png");
        this.obrazokUtocnejPusky.zmenUhol(45);
        this.obrazokUtocnejPusky.zmenPolohu(500, 770);
        this.obrazokUtocnejPusky.zobraz();
        this.jeObchodZobrazeny = false;
    }
    /**
     * Pokúsi sa zakúpiť položku na základe aktuálnej polohy myši a dostupných financií.
     * Ak hráč klikne do príslušného poľa a má dostatok peňazí, zbraň sa mu pridelí.
     * @param x poloha myši na osi X
     * @param y poloha myši na osi Y
     */
    public void zakupPolozku(int x, int y) {
        if (this.jeObchodZobrazeny) {
            if (this.polohaMysiX >= 600 && this.polohaMysiX <= 720) {
                if (this.polohaMysiY >= 196 && this.polohaMysiY <= 320 && this.herneMenu.getFinancie() >= 1500) {
                    this.hrac.zakupZbran(TypZbrane.SAMOPAl);
                    this.herneMenu.znizFinancie(1500);
                } else if (this.polohaMysiY >= 496 && this.polohaMysiY <= 620 && this.herneMenu.getFinancie() >= 3000) {
                    this.hrac.zakupZbran(TypZbrane.BROKOVNICA);
                    this.herneMenu.znizFinancie(3000);
                } else if (this.polohaMysiY >= 796 && this.polohaMysiY <= 920 && this.herneMenu.getFinancie() >= 5000) {
                    this.hrac.zakupZbran(TypZbrane.UTOCNAPUSKA);
                }
            }
        }
    }
    /**
     * Aktualizuje internú polohu myši v obchode.
     * @param x nová pozícia myši na osi X
     * @param y nová pozícia myši na osi Y
     */
    public void hybMysou(int x, int y) {
        this.polohaMysiX = x;
        this.polohaMysiY = y;
    }
    /**
     * Zobrazí všetky vizuálne prvky obchodu na obrazovke.
     */
    public void zobrazObchod() {
        this.jeObchodZobrazeny = true;
        this.pozadie.zobraz();
        this.nadpis.zobraz();
        this.popisPreSamopal.zobraz();
        this.polickoPreSamopal.zobraz();
        this.obrazokSamopalu.zobraz();
        this.popisPreBrokovnicu.zobraz();
        this.polickoPreBrokovnicu.zobraz();
        this.obrazokBrokovnice.zobraz();
        this.popisPreUtocnuPusku.zobraz();
        this.polickoPreUtocnuPusku.zobraz();
        this.obrazokUtocnejPusky.zobraz();
    }
    /**
     * Skryje všetky vizuálne prvky obchodu z obrazovky.
     */
    public void skryObchod() {
        this.jeObchodZobrazeny = false;
        this.pozadie.skry();
        this.nadpis.skry();
        this.popisPreSamopal.skry();
        this.polickoPreSamopal.skry();
        this.obrazokSamopalu.skry();
        this.popisPreBrokovnicu.skry();
        this.polickoPreBrokovnicu.skry();
        this.obrazokBrokovnice.skry();
        this.popisPreUtocnuPusku.skry();
        this.polickoPreUtocnuPusku.skry();
        this.obrazokUtocnejPusky.skry();
    }
    /**
     * Manazer začne spravovať obchod.
     * @param manazer správca, ktorý má spravovať tento objekt
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Manazer prestane spravovať obchod.
     * @param manazer správca, ktorý má spravovať tento objekt
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }
}
