package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Trieda Pistol reprezentuje základnú strelnú zbraň (pištoľ),
 * ktorú môže hráč použiť v hre. Zbraň má možnosť vystreľovania nábojov
 * a dočasného zvýšenia poškodenia s obmedzeným počtom výstrelov.
 */
public class Pistol extends Zbran {
    private int poskodenie;
    private final Timer timer;
    private final int velkostZasobnika;
    private boolean striela;
    private int counterZvysenehoPoskodenia;
    private boolean jeZvysenePoskodenie;
    private final ArrayList<Naboj> zoznamVystrelenychNabojov;
    /**
     * Konštruktor pre vytvorenie novej pištole.
     * @param hrac hráč, ktorý vlastní túto zbraň
     * @param typZbrane typ zbrane s vlastnosťami (poškodenie, obrázok)
     */
    public Pistol(Hrac hrac , TypZbrane typZbrane) {
        super(hrac, typZbrane);
        this.timer = new Timer();
        this.striela = false;
        this.counterZvysenehoPoskodenia = 0;
        this.poskodenie = typZbrane.getPoskodenie();
        this.velkostZasobnika = 7;
        this.jeZvysenePoskodenie = false;
        this.zoznamVystrelenychNabojov = new ArrayList<>();
    }
    /**
     * Vystrelí náboj, ak je zbraň držaná a nie je v stave cool-down.
     * Ak je aktivované zvýšené poškodenie, po 7 výstreloch sa automaticky deaktivuje.
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
                //Tu som si tiež pomohol internetom
                this.striela = true;
                this.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Pistol.this.striela = false;
                    }
                }, 420);
                Naboj nabojPistole = new Naboj(this);
                this.zoznamVystrelenychNabojov.add(nabojPistole);
            }
        } else {
            this.skryZbran();
        }
    }
    /**
     * Aktualizuje pozíciu zbrane podľa aktuálnej pozície hráča.
     */
    @Override
    public void aktualizujPoziciu() {
        super.aktualizujPoziciu();
    }
    /**
     * Vráti aktuálne poškodenie spôsobené touto zbraňou.
     *
     * @return hodnota poškodenia
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Nastaví, či je zbraň momentálne držaná hráčom.
     *
     * @param jeDrzana true, ak je zbraň držaná, inak false
     */
    @Override
    public void setJeMomentalneDrzana(boolean jeDrzana) {
        super.setJeMomentalneDrzana(jeDrzana);
    }
    /**
     * Zvýši poškodenie zbrane na dvojnásobok a aktivuje režim zvýšeného poškodenia.
     */
    public void zvysPoskodenie() {
        this.setPoskodenie(this.poskodenie * 2);
        this.jeZvysenePoskodenie = true;
    }
    /**
     * Zníži poškodenie na polovicu a deaktivuje zvýšené poškodenie.
     */
    public void znizPoskodenie() {
        this.setPoskodenie(this.poskodenie / 2);
        this.jeZvysenePoskodenie = false;
    }
    /**
     * Nastaví nové poškodenie zbrane.
     *
     * @param novePoskodenie hodnota, na ktorú sa má poškodenie zmeniť
     */
    @Override
    public void setPoskodenie(int novePoskodenie) {
        this.poskodenie = novePoskodenie;
    }
    /**
     * Zistí, či je aktivovaný režim zvýšeného poškodenia.
     *
     * @return {@code true}, ak je poškodenie zvýšené, inak {@code false}
     */
    @Override
    public boolean getJeZvysenePoskodenie() {
        return this.jeZvysenePoskodenie;
    }
    /**
     * Získaj zoznam všetkých nábojov, ktoré boli vystrelené z tejto zbrane.
     *
     * @return zoznam nábojov
     */
    public ArrayList<Naboj> getZoznamVystrelenychNabojov() {
        return this.zoznamVystrelenychNabojov;
    }
    /**
     * Pridá túto zbraň do správy {@code Manazer}a pre interakciu so vstupmi.
     *
     * @param manazer inštancia {@code Manazer}
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Odstráni túto zbraň z {@code Manazer}a.
     *
     * @param manazer inštancia {@code Manazer}
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }
}
