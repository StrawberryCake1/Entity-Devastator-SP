package sk.uniza.fri.hra;

import fri.shapesge.BlokTextu;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import fri.shapesge.Manazer;
import sk.uniza.fri.hra.zbrane.TypZbrane;

import java.util.ArrayList;
/**
 * Trieda HerneMenu reprezentuje používateľské rozhranie v hre,
 * ktoré zobrazuje skóre, financie, životy hráča a vybraný typ zbrane.
 * Implementuje rozhranie Menezovatelne pre správu objektov pomocou triedy Manazer.
 * Zobrazuje tiež rôzne vizuálne oddelovače a obrázky zbraní ako súčasť herného menu.
 */
public class HerneMenu implements Menezovatelne {
    private final BlokTextu score;
    private final BlokTextu financie;
    private int vyskaScore;
    private int vyskaFinancii;
    private final Hrac hrac;
    private final ArrayList<Zivot> zoznamZivotov;
    private final Obdlznik ukazovatelZbrane;
    /**
     * Vytvorí nové herné menu, nastaví základné komponenty ako skóre, financie,
     * životy hráča a ukazovateľ aktuálne zvolenej zbrane.
     * @param hrac Odkaz na inštanciu hráča, pre ktorého sa menu vytvára.
     */
    public HerneMenu(Hrac hrac) {
        this.hrac = hrac;
        Obdlznik menu = new Obdlznik();
        menu.zmenFarbu("magenta");
        menu.zmenPolohu(0, 920);
        menu.zmenStrany(1920, 150);
        menu.zobraz();

        //Oddelovac 1
        Obdlznik prvyOddelovac = new Obdlznik();
        prvyOddelovac.zmenFarbu("black");
        prvyOddelovac.zmenPolohu(500, 920);
        prvyOddelovac.zmenStrany(5, 200);
        prvyOddelovac.zobraz();

        //Oddelovac 2
        Obdlznik druhyOddelovac = new Obdlznik();
        druhyOddelovac.zmenFarbu("black");
        druhyOddelovac.zmenPolohu(700, 920);
        druhyOddelovac.zmenStrany(5, 200);
        druhyOddelovac.zobraz();

        //Oddelovac 3
        Obdlznik tretiOddelovac = new Obdlznik();
        tretiOddelovac.zmenFarbu("black");
        tretiOddelovac.zmenPolohu(900, 920);
        tretiOddelovac.zmenStrany(5, 200);
        tretiOddelovac.zobraz();

        //Oddelovac 4
        Obdlznik stvrtyOddelovac = new Obdlznik();
        stvrtyOddelovac.zmenFarbu("black");
        stvrtyOddelovac.zmenPolohu(1100, 920);
        stvrtyOddelovac.zmenStrany(5, 200);
        stvrtyOddelovac.zobraz();

        //Oddelovac 4
        Obdlznik piatyOddelovac = new Obdlznik();
        piatyOddelovac.zmenFarbu("black");
        piatyOddelovac.zmenPolohu(1300, 920);
        piatyOddelovac.zmenStrany(5, 200);
        piatyOddelovac.zobraz();


        Obrazok pistol = new Obrazok("resources/pistolPreMenu.png");
        pistol.zmenPolohu(440, 940);
        pistol.zmenUhol(90);
        pistol.zobraz();
        //samopal
        Obrazok samopal = new Obrazok("resources/samopalPreMenu.png");
        samopal.zmenPolohu(670, 880);
        samopal.zmenUhol(90);
        samopal.zobraz();
        //Brokovnica
        Obrazok brokovnica = new Obrazok("resources/brokovnicaPreMenu.png");
        brokovnica.zmenPolohu(890, 880);
        brokovnica.zmenUhol(90);
        brokovnica.zobraz();
        //Puska
        Obrazok utocnaPuska = new Obrazok("resources/utocnaPuskaPreMenu.png");
        utocnaPuska.zmenPolohu(1020, 860);
        utocnaPuska.zmenUhol(90);
        utocnaPuska.zobraz();


        //Score
        this.vyskaScore = 0;
        this.score = new BlokTextu("Score:");
        this.score.zmenFont("BOLD", StylFontu.BOLD, 30);
        this.score.zmenPolohu(1330, 960);
        this.score.zmenFarbu("red");
        this.score.zobraz();

        //Finanancieu
        this.vyskaFinancii = 0;
        this.financie = new BlokTextu("Financie:");
        this.financie.zmenFont("BOLD", StylFontu.BOLD, 30);
        this.financie.zmenPolohu(1330, 1020);
        this.financie.zmenFarbu("blue");
        this.financie.zobraz();

        this.zoznamZivotov = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int oddelovacX = i * 75;
            this.zoznamZivotov.add(new Zivot(((-10) + oddelovacX), 950));
        }
        for (Zivot z : this.zoznamZivotov) {
            z.zobrazZivot();
        }
        this.ukazovatelZbrane = new Obdlznik();
        this.ukazovatelZbrane.zmenStrany(50, 10);
        this.ukazovatelZbrane.zobraz();
    }
    /**
     * Aktualizuje herné menu, mení pozíciu a farbu ukazovateľa zbrane
     * a aktualizuje počet zobrazených životov podľa aktuálneho stavu hráča.
     */
    public void kontroluj() {
        this.zmenUkazovatelZbrane();
        this.aktualizujZivot();
    }
    /**
     * Zmení polohu a farbu ukazovateľa zbrane podľa aktuálne zvoleného typu zbrane hráča.
     */
    public void zmenUkazovatelZbrane() {
        switch (this.hrac.getAktualnyTypZbrane()) {
            case TypZbrane.PISTOL -> {
                this.ukazovatelZbrane.zmenPolohu(580, 940);
                this.ukazovatelZbrane.zmenFarbu("green");
            }
            case TypZbrane.SAMOPAl -> {
                this.ukazovatelZbrane.zmenPolohu(780, 940);
                this.ukazovatelZbrane.zmenFarbu("red");
            }
            case TypZbrane.BROKOVNICA -> {
                this.ukazovatelZbrane.zmenPolohu(980, 940);
                this.ukazovatelZbrane.zmenFarbu("blue");
            }
            case TypZbrane.UTOCNAPUSKA -> {
                this.ukazovatelZbrane.zmenPolohu(1170, 940);
                this.ukazovatelZbrane.zmenFarbu("yellow");
            }
        }
    }
    /**
     * Aktualizuje zobrazenie životov podľa počtu životov hráča.
     * Skryje alebo zobrazí ikony srdiečok reprezentujúcich životy.
     */
    public void aktualizujZivot() {
        int hracoveZivoty = this.hrac.getZivoty();
        for (Zivot z : this.zoznamZivotov) {
            z.zobrazZivot();
        }
        switch (hracoveZivoty) {
            case 0:
                for (Zivot z : this.zoznamZivotov) {
                    z.skryZivot();
                }
                return;
            case 1:
                for (int i = 1; i < this.zoznamZivotov.size(); i++) {
                    this.zoznamZivotov.get(i).skryZivot();
                }
                break;
            case 2:
                for (int i = 2; i < this.zoznamZivotov.size(); i++) {
                    this.zoznamZivotov.get(i).skryZivot();
                }
                break;
            case 3:
                for (int i = 3; i < this.zoznamZivotov.size(); i++) {
                    this.zoznamZivotov.get(i).skryZivot();
                }
                break;
            case 4:
                for (int i = 4; i < this.zoznamZivotov.size(); i++) {
                    this.zoznamZivotov.get(i).skryZivot();
                }
                break;
            case 5:
                for (int i = 5; i < this.zoznamZivotov.size(); i++) {
                    this.zoznamZivotov.get(i).skryZivot();
                }
                break;
            case 6:
                break;
            default:
                for (Zivot z : this.zoznamZivotov) {
                    z.skryZivot();
                }
                break;
        }
    }
    /**
     * Zvýši skóre hráča o 200 bodov a aktualizuje text v menu.
     */
    public void zvysScore() {
        this.vyskaScore += 200;
        this.score.zmenText("SCORE: " + this.vyskaScore);
    }
    /**
     * Zvýši financie hráča o 400 jednotiek a aktualizuje text v menu.
     */
    public void zvysFinancie() {
        this.vyskaFinancii += 400;
        this.financie.zmenText("FINANCIE: " + this.vyskaFinancii);
    }
    /**
     * Zníži financie hráča o zadanú sumu.
     * @param suma Hodnota, o ktorú sa znížia financie hráča.
     */
    public void znizFinancie(int suma) {
        this.vyskaFinancii -= suma;
    }
    /**
     * Vráti aktuálne skóre hráča.
     * @return Aktuálna hodnota skóre.
     */
    public int getScore() {
        return this.vyskaScore;
    }
    /**
     * Vráti aktuálnu výšku financií hráča.
     * @return Aktuálna hodnota financií.
     */
    public int getFinancie() {
        return this.vyskaFinancii;
    }
    /**
     * Zaregistruje objekt herného menu pre správu pomocou Manazera.
     * @param manazer Inštancia triedy Manazer, ktorá spravuje herné objekty.
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Odstráni objekt herného menu zo správy Manazera.
     * @param manazer Inštancia triedy Manazer, ktorá spravuje herné objekty.
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }
}
