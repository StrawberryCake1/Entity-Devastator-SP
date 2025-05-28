package sk.uniza.fri.hra;

import sk.uniza.fri.hra.entity.Entita;
import sk.uniza.fri.hra.entity.GeneratorEntit;
import sk.uniza.fri.hra.entity.LietajuciObjekt;
import sk.uniza.fri.hra.entity.vlastnosti.ElektrickyVyboj;
import sk.uniza.fri.hra.entity.vlastnosti.Meteorit;
import sk.uniza.fri.hra.entity.vlastnosti.Ufo;
import sk.uniza.fri.hra.potraviny.Potravina;
import sk.uniza.fri.hra.zbrane.Zbran;
import sk.uniza.fri.hra.zbrane.Naboj;
import sk.uniza.fri.hra.zbrane.TypZbrane;
import sk.uniza.fri.hra.zbrane.Pistol;
import sk.uniza.fri.hra.zbrane.Samopal;
import sk.uniza.fri.hra.zbrane.Brokovnica;
import sk.uniza.fri.hra.zbrane.UtocnaPuska;

import java.util.ArrayList;
/**
 * Trieda Kolíízia zabespečuje detekciu a spracovanie kolízií medzi entitami,
 * nábojmi, hracom a objektmi ako su meteority, UFO a elektricke vyboje.
 * Taktiez spravuje zbieranie potravin a vyhodnocuje ich vplyv.
 */
public class Kolizia {
    private final ArrayList<Entita> entity;
    private final Hrac hrac;
    private final ArrayList<Potravina> zoznamPotravin;
    private final HerneMenu herneMenu;
    /**
     * Vytvorí novú inštanciu triedy Kolízia.
     *
     * @param hrac hrac hry
     * @param generatorEntit generator entit pouzity pre ziskanie zoznamu entit
     * @param herneMenu herne menu, ktore uchovava informacie o stave hry
     */

    public Kolizia(Hrac hrac, GeneratorEntit generatorEntit, HerneMenu herneMenu) {
        this.hrac = hrac;
        this.entity = generatorEntit.getAktualnyZoznamEntit();
        this.zoznamPotravin = new ArrayList<>();
        this.herneMenu = herneMenu;
    }
    /**
     * Aktualizuje stav hry. Spracuje kolizie medzi nabojmi a entitami,
     * entitami a hráčom, ako aj medzi potravinami a hráčom.
     */
    //V tejto metode som si pomonol internetom konrétne pri mazaní objektov za podmienky že sa ešte nevymazali
    public void aktualizuj() {
        //Metoda funguje na prinípe prechádzania zoznamov a kontrolou súradníc objektov.
        ArrayList<Naboj> zoznamNabojov = this.getNaboje();

        ArrayList<Entita> entitNaOdstranenie = new ArrayList<>();
        ArrayList<Naboj> nabojeNaOdstranenie = new ArrayList<>();
        ArrayList<Entita> kopiaEntit = new ArrayList<>(this.entity);

        for (Entita entita : kopiaEntit) {
            int eX = entita.getPolohaX();
            int eY = entita.getPolohaY();
            int entitaSirka = 20;
            int entitaVyska = 120;
            for (Naboj naboj : zoznamNabojov) {
                if (nabojeNaOdstranenie.contains(naboj)) {
                    continue;
                }
                int nx = naboj.getPolohaX();
                int ny = naboj.getPolohaY() - 80;
                if (nx >= eX && nx <= eX + entitaSirka && ny >= eY && ny <= eY + entitaVyska) {
                    entita.prijimiUtok(naboj.getPoskodenie());
                    naboj.skryNaboj();
                    nabojeNaOdstranenie.add(naboj);
                    if (entita.getZivoty() <= 0 && !entitNaOdstranenie.contains(entita)) {
                        entita.prestanZit();
                        entitNaOdstranenie.add(entita);
                        this.zoznamPotravin.add(entita.getPotravina());
                        this.herneMenu.zvysScore();
                        this.herneMenu.zvysFinancie();
                    }
                    break;
                }
            }
        }
        //Využitie generík na ušetrenie pár riadkov kodu a menej instanceof
        for (Entita entita : new ArrayList<>(this.entity)) {
            if (entita instanceof LietajuciObjekt<?> lietajuce) {
                ArrayList<?> objekty = lietajuce.getZoznamObjektov();
                for (Object objekt : objekty) {
                    int oX;
                    int oY;
                    //Spravíme boolean ktorý je true ak platia súradnice a ak sme dostali true tak hráč dostáva zásah
                    boolean zasah = false;
                    if (objekt instanceof Meteorit m) {
                        oX = m.getPolohaX();
                        oY = m.getPolohaY();
                        zasah = oX >= this.hrac.getPolohaX() && oX <= this.hrac.getPolohaX() + 100 &&
                                oY >= this.hrac.getPolohaY() && oY <= this.hrac.getPolohaY() + 100;
                    } else if (objekt instanceof Ufo u) {
                        oX = u.getPolohaX();
                        oY = u.getPolohaY();
                        zasah = oX >= this.hrac.getPolohaX() && oX <= this.hrac.getPolohaX() + 100 &&
                                oY >= this.hrac.getPolohaY() && oY <= this.hrac.getPolohaY() + 100;
                    } else if (objekt instanceof ElektrickyVyboj e) {
                        oX = e.getPolohaX();
                        oY = e.getPolohaY();
                        zasah = oX >= this.hrac.getPolohaX() && oX <= this.hrac.getPolohaX() + 100 &&
                                oY >= this.hrac.getPolohaY() && oY <= this.hrac.getPolohaY() + 50;
                    }
                    if (zasah) {
                        this.hrac.prijmiUtok();
                    }
                }
            }
        }

        this.entity.removeIf(entitNaOdstranenie::contains);

        zoznamNabojov.removeIf(nabojeNaOdstranenie::contains);

        ArrayList<Potravina> potravinyNaOdstranenie = new ArrayList<>();
        for (Potravina potravina : this.zoznamPotravin) {
            if (potravina != null) {
                int pX = potravina.getPolohaX();
                int pY = potravina.getPolohaY();
                if (pX >= this.hrac.getPolohaX() && pX <= this.hrac.getPolohaX() + 100 &&
                        pY >= this.hrac.getPolohaY() && pY <= this.hrac.getPolohaY() + 100) {
                    //Polymorfizmus
                    potravina.vykonajAbilitu();
                    potravinyNaOdstranenie.add(potravina);
                }
            }
        }

        this.zoznamPotravin.removeIf(potravinyNaOdstranenie::contains);
    }
    /**
     * Vráti zoznam vystrelených nábojov na základe aktualne držanej zbrane hráča.
     * @return zoznam aktívnych nábojov
     */
    public ArrayList<Naboj> getNaboje() {
        TypZbrane hracovaAktualnaZbran = this.hrac.getAktualnyTypZbrane();
        Zbran aktualnaZbran = this.hrac.getAktualnaZbran();
        return switch (hracovaAktualnaZbran) {
            case PISTOL -> ((Pistol)aktualnaZbran).getZoznamVystrelenychNabojov();
            case SAMOPAl -> ((Samopal)aktualnaZbran).getZoznamVystrelenychNabojov();
            case BROKOVNICA -> ((Brokovnica)aktualnaZbran).getZoznamVystrelenychNabojov();
            case UTOCNAPUSKA -> ((UtocnaPuska)aktualnaZbran).getZoznamVystrelenychNabojov();
        };
    }
}