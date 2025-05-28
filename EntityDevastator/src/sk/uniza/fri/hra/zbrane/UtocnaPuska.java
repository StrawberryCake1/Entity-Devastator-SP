package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Trieda UtocnaPuska reprezentuje konkrétny typ zbrane — útočnú pušku.
 * Umožňuje opakovanú streľbu s časovým obmedzením, evidenciu vystrelených nábojov
 * a manipuláciu so zvýšeným poškodením po určitý počet výstrelov.
 */
public class UtocnaPuska extends Zbran {
    private int poskodenie;
    private final Timer timer;
    private final int velkostZasobnika;
    private boolean striela;
    private int counterZvysenehoPoskodenia;
    private boolean jeZvysenePoskodenie;
    private final ArrayList<Naboj> zoznamVystrelenychNabojov;
    /**
     * Vytvára novú útočnú pušku pre daného hráča a s daným typom zbrane.
     *
     * @param hrac Hráč, ktorému zbraň patrí.
     * @param typZbrane Typ útočnej pušky (určuje poškodenie a obrázky).
     */
    public UtocnaPuska(Hrac hrac, TypZbrane typZbrane) {
        super(hrac, typZbrane);
        this.timer = new Timer();
        this.striela = false;
        this.counterZvysenehoPoskodenia = 0;
        this.poskodenie = typZbrane.getPoskodenie();
        this.velkostZasobnika = 30;
        this.jeZvysenePoskodenie = false;
        this.zoznamVystrelenychNabojov = new ArrayList<>();
    }
    /**
     * Pokúsi sa vystreliť zo zbrane. Ak zbraň nie je v cooldown režime,
     * vytvorí nový objekt Naboj, pridá ho do zoznamu a obmedzí ďalší výstrel na 160 ms.
     * Ak je aktivované zvýšené poškodenie, počíta výstrely a po prekročení kapacity zásobníka ho deaktivuje.
     */
    public void vystrelZoZbrane() {
        if (this.getJeMomentalneDrzana()) {
            if (!this.striela) {
                if (this.getJeZvysenePoskodenie()) {
                    this.counterZvysenehoPoskodenia++;
                    if (this.counterZvysenehoPoskodenia > this.velkostZasobnika) {
                        this.znizPoskodenie();
                        this.counterZvysenehoPoskodenia = 0;
                    }
                }
                this.striela = true;
                //Tu som si tiež pomohol internetom
                this.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        UtocnaPuska.this.striela = false;
                    }
                }, 160);
                Naboj nabojPistole = new Naboj(this);
                this.zoznamVystrelenychNabojov.add(nabojPistole);
            }
        } else {
            this.skryZbran();
        }
    }
    /**
     * Aktualizuje pozíciu zbrane podľa pohybu hráča a smeru jeho pohľadu.
     */
    @Override
    public void aktualizujPoziciu() {
        super.aktualizujPoziciu();
    }
    /**
     * Získa aktuálnu hodnotu poškodenia zbrane.
     *
     * @return Poškodenie zbrane.
     */

    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Nastaví, či je zbraň aktuálne držaná hráčom.
     *
     * @param jeDrzana true, ak je držaná; false inak.
     */
    @Override
    public void setJeMomentalneDrzana(boolean jeDrzana) {
        super.setJeMomentalneDrzana(jeDrzana);
    }
    /**
     * Zdvojnásobí aktuálne poškodenie a aktivuje príznak zvýšeného poškodenia.
     */
    public void zvysPoskodenie() {
        this.setPoskodenie(this.poskodenie * 2);
        this.jeZvysenePoskodenie = true;
    }
    /**
     * Zníži aktuálne poškodenie na polovicu a deaktivuje príznak zvýšeného poškodenia.
     */
    public void znizPoskodenie() {
        this.setPoskodenie(this.poskodenie / 2);
        this.jeZvysenePoskodenie = false;
    }
    /**
     * Nastaví novú hodnotu poškodenia.
     *
     * @param novePoskodenie Nové poškodenie zbrane.
     */
    @Override
    public void setPoskodenie(int novePoskodenie) {
        this.poskodenie = novePoskodenie;
    }
    /**
     * Zistí, či má zbraň aktuálne zvýšené poškodenie.
     *
     * @return true, ak je poškodenie zvýšené; false inak.
     */
    @Override
    public boolean getJeZvysenePoskodenie() {
        return this.jeZvysenePoskodenie;
    }
    /**
     * Získa zoznam všetkých nábojov, ktoré boli vystrelené touto puškou.
     *
     * @return Zoznam objektov Naboj.
     */
    public ArrayList<Naboj> getZoznamVystrelenychNabojov() {
        return this.zoznamVystrelenychNabojov;
    }
    /**
     * Zaregistruje túto zbraň u manažéra, aby bola spravovaná (napr. kvôli pohybu).
     *
     * @param manazer Manažér hry.
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Odregistruje túto zbraň z manažéra.
     *
     * @param manazer Manažér hry.
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }
}
