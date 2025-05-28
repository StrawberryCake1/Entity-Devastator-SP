package sk.uniza.fri.hra;
import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.entity.GeneratorEntit;

import java.util.ArrayList;
/**
 * Trieda Hra reprezentuje hlavný riadiaci objekt hry.
 * Inicializuje herné objekty, spravuje kolá, kontroluje stav hry a implementuje funkciu pauzy.
 */
public class Hra {
    /** Aktuálne číslo herného kola. */
    private int cisloKola;
    /** Inštancia hráča. */
    private final Hrac hrac;
    /** Inštancia herného menu. */
    private final HerneMenu herneMenu;
    /** Generátor entít pre vytváranie nepriateľov. */
    private final GeneratorEntit generatorEntit;
    /** Obchod, kde si hráč môže kupovať zbrane a vylepšenia. */
    private final Obchod obchod;
    /** Manažér pre spravovanie objektov v hre. */
    private final Manazer manazer;
    /** Počet stlačení pauzy. */
    private int pocetPauz;
    /** Trieda zodpovedná za zisťovanie kolízií medzi objektmi. */
    private final Kolizia kolizia;
    /** Zoznam objektov, ktoré je možné spravovať pomocou manažéra. */
    private final ArrayList<Menezovatelne> zoznammMenezovatelncychObjektov;
    /**
     * Vytvára novú hru a inicializuje všetky potrebné herné komponenty.
     * Nastavuje hráča, menu, obchod, kolízny systém a generuje prvú vlnu nepriateľov.
     * Tiež registruje spravovateľné objekty do manažéra.
     */
    public Hra() {
        this.manazer = new Manazer();
        this.hrac = new Hrac();
        Dimenzia dimenzia = new Dimenzia();
        this.generatorEntit = new GeneratorEntit(this.hrac);
        this.herneMenu = new HerneMenu(this.hrac);
        this.cisloKola = 1;
        this.obchod = new Obchod(this.hrac, this.herneMenu);
        this.kolizia = new Kolizia(this.hrac, this.generatorEntit, this.herneMenu);
        this.pocetPauz = 0;
        this.manazer.spravujObjekt(this);
        this.generatorEntit.generujVlnu(this.cisloKola);
        this.obchod.skryObchod();
        this.zoznammMenezovatelncychObjektov = new ArrayList<>();
        this.zoznammMenezovatelncychObjektov.add(this.hrac);
        this.zoznammMenezovatelncychObjektov.add(this.herneMenu);
        this.zoznammMenezovatelncychObjektov.add(this.obchod);
        for (Menezovatelne menezovatelne : this.zoznammMenezovatelncychObjektov) {
            menezovatelne.spravujObjekt(this.manazer);
        }
    }
    /**
     * Kontroluje aktuálny stav hry. Ak hráč prehral alebo vyhral, zobrazí príslušný obrázok a ukončí hru.
     * Tiež spravuje prechod medzi kolami na základe skóre.
     */
    public void kontrolujStavHry() {
        this.kolizia.aktualizuj();
        this.herneMenu.kontroluj();
        int score = this.herneMenu.getScore();
        if (this.hrac.getZivoty() <= 0) {
            Obrazok prehra = new Obrazok("resources/prehra.jpg");
            prehra.posunZvisle(-100);
            prehra.posunVodorovne(-100);
            prehra.zobraz();
            this.manazer.prestanSpravovatObjekt(this);
            System.out.println("Smola skus to znova !");
        }
        if (score == 5000) {
            Obrazok vyhra = new Obrazok("resources/vyhra.png");
            vyhra.posunZvisle(-100);
            vyhra.posunVodorovne(-100);
            vyhra.zobraz();
            this.manazer.prestanSpravovatObjekt(this);
            System.out.println("Gratulujem vyhral si !");
        }
        if (score == 800 && this.cisloKola < 2) {
            this.cisloKola++;
            this.generatorEntit.generujVlnu(this.cisloKola);
        } else if (score == 2400 && this.cisloKola < 3) {
            this.cisloKola++;
            this.generatorEntit.generujVlnu(this.cisloKola);
        } else if (score == 4800 && this.cisloKola < 4) {
            System.err.println("DANGER!");
            this.cisloKola++;
            this.generatorEntit.generujVlnu(this.cisloKola);
        }
    }
    /**
     * Spracováva logiku pauzy v hre. Striedavo pozastavuje a opätovne aktivuje pohyb a interakciu herných objektov.
     * Každé druhé stlačenie pauzne/zobrazí obchod a pozastaví/spravuje ostatné objekty.
     */
    public void pauzni() {
        this.pocetPauz++;
        if (this.pocetPauz % 2 == 1) {
            this.obchod.skryObchod();
            for (Menezovatelne menezovatelne : this.zoznammMenezovatelncychObjektov) {
                menezovatelne.spravujObjekt(this.manazer);
            }
            this.obchod.prestanSpravovatObjekt(this.manazer);
            this.generatorEntit.spravujEntity();
        } else {
            this.obchod.zobrazObchod();
            for (Menezovatelne menezovatelne : this.zoznammMenezovatelncychObjektov) {
                menezovatelne.prestanSpravovatObjekt(this.manazer);
            }
            this.obchod.spravujObjekt(this.manazer);
            this.generatorEntit.prestanSpravovatEntity();
        }
    }
}
