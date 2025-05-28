package sk.uniza.fri.hra;

import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;

/**
 * Trieda Dimenzia reprezentuje herné pozadie a vizuálne rozloženie štyroch rôznych oblastí (dimenzií) v hre.
 * Každá časť obrazovky má vlastné farebné pozadie (stenu) a špecifické objekty ako hroby, továreň, UFO a kostol,
 * ktoré slúžia ako dekorácie alebo orientačné body v hre.
 */
public class Dimenzia {
    /**
     * Konštruktor Dimenzia vytvorí vizuálne prvky rozmiestnené v štyroch častiach obrazovky.
     * - Nastavuje pozadie a rozmiestňuje jednotlivé steny (oblasti)
     * - Pridáva dekoratívne objekty ako hroby, továreň, UFO a kostol
     * - Vytvára vizuálne predely medzi jednotlivými oblasťami pomocou bielych obdĺžnikov
     */
    public Dimenzia() {
        Obdlznik pozadie = new Obdlznik();
        pozadie.zmenFarbu("black");
        pozadie.zmenPolohu(0, 0);
        pozadie.zmenStrany(1920, 1080);
        pozadie.zobraz();
        Obdlznik prvaStena = new Obdlznik();
        prvaStena.zmenFarbu("brown");
        prvaStena.zmenPolohu(0, 0);
        prvaStena.zmenStrany(900, 500);
        prvaStena.zobraz();
        int rozostup = 200;
        for (int i = 0; i < 7; i++) {
            Obrazok hrob = new Obrazok("resources/hrobka.png");
            if (i >= 4) {
                hrob.posunVodorovne(rozostup * (i - 4));
                hrob.posunZvisle(rozostup);
            } else {
                hrob.posunVodorovne(rozostup * i);
            }
            hrob.zobraz();
        }
        Obdlznik druhaStena = new Obdlznik();
        druhaStena.zmenFarbu("red");
        druhaStena.zmenPolohu(1020, 0);
        druhaStena.zmenStrany(900, 500);
        druhaStena.zobraz();
        Obrazok fabrika = new Obrazok("resources/fabrika.png");
        fabrika.zmenPolohu(1300, 50);
        fabrika.zobraz();
        Obdlznik tretiaStena = new Obdlznik();
        tretiaStena.zmenFarbu("blue");
        tretiaStena.zmenPolohu(0, 600);
        tretiaStena.zmenStrany(900, 500);
        tretiaStena.zobraz();

        for (int i = 0; i < 3; i++) {
            Obrazok ufo = new Obrazok("resources/ufoDekoracne.png");
            ufo.posunVodorovne((rozostup + 100) * i);
            ufo.posunZvisle(500);
            ufo.zobraz();
            if (i == 1) {
                ufo.posunZvisle(200);
            }
        }

        Obdlznik stvrtaStena = new Obdlznik();
        stvrtaStena.zmenFarbu("magenta");
        stvrtaStena.zmenPolohu(1020, 600);
        stvrtaStena.zmenStrany(900, 500);
        stvrtaStena.zobraz();
        Obrazok kostol = new Obrazok("resources/kostol.png");
        kostol.zmenPolohu(1400, 620);
        kostol.zobraz();
        int medzera = 100;
        for (int i = 0; i < 19; i++) {
            Obdlznik obdlznikX  = new Obdlznik();
            obdlznikX.zmenStrany(50, 10);
            obdlznikX.zmenFarbu("white");
            obdlznikX.zmenPolohu((medzera * i) + 30, 540);
            obdlznikX.zobraz();
            Obdlznik obdlznikY  = new Obdlznik();
            obdlznikY.zmenStrany(10, 50);
            obdlznikY.zmenFarbu("white");
            obdlznikY.zmenPolohu(950, (i * medzera) + 30);
            obdlznikY.zobraz();
            if (i == 9) {
                obdlznikX.skry();
            }
            if (i == 5) {
                obdlznikY.skry();
            }
        }
    }
}
