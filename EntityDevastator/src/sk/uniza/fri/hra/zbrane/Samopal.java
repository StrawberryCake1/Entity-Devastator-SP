package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Trieda Samopal reprezentuje automatickú zbraň, ktorá môže vystreľovať náboje s možnosťou dočasného
 * zvýšenia poškodenia. Zbraň má obmedzenú kadenciu streľby a veľkosť zásobníka pre efekt zvýšeného poškodenia.
 */
public class Samopal extends Zbran {
    private int poskodenie;
    private final Timer timer;
    private final int velkostZasobnika;
    private boolean striela;
    private int counterZvysenehoPoskodenia;
    private boolean jeZvysenePoskodenie;
    private final ArrayList<Naboj> zoznamVystrelenychNabojov;
    /**
     * Vytvorí novú inštanciu zbrane typu {@code Samopal}.
     * @param hrac hráč, ktorý zbraň používa
     * @param typZbrane typ zbrane (obsahuje cestu k obrázku a základné poškodenie)
     */
    public Samopal(Hrac hrac, TypZbrane typZbrane) {
        super(hrac, typZbrane);
        Obrazok samopal = new Obrazok(typZbrane.getObrazok());
        samopal.posunVodorovne(-200);
        samopal.posunZvisle(-100);
        this.timer = new Timer();

        this.striela = false;
        this.counterZvysenehoPoskodenia = 0;
        this.poskodenie = typZbrane.getPoskodenie();
        this.velkostZasobnika = 25;
        this.jeZvysenePoskodenie = false;
        this.zoznamVystrelenychNabojov = new ArrayList<>();
    }
    /**
     * Pokúsi sa vystreliť zo zbrane, ak nie je v cooldown stave. Ak je aktívne zvýšené poškodenie,
     * sleduje počet výstrelov a po vyčerpaní zásobníka ho deaktivuje.
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
                        Samopal.this.striela = false;
                    }
                }, 200);
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
     * Získaj aktuálne poškodenie zbrane.
     *
     * @return hodnota poškodenia
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Nastaví, či je zbraň momentálne držaná hráčom.
     *
     * @param jeDrzana {@code true}, ak je zbraň držaná, inak {@code false}
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
     * Zníži poškodenie zbrane na polovicu a deaktivuje režim zvýšeného poškodenia.
     */
    public void znizPoskodenie() {
        this.setPoskodenie(this.poskodenie / 2);
        this.jeZvysenePoskodenie = false;
    }
    /**
     * Nastaví aktuálne poškodenie zbrane.
     *
     * @param novePoskodenie nové poškodenie
     */
    @Override
    public void setPoskodenie(int novePoskodenie) {
        this.poskodenie = novePoskodenie;
    }
    /**
     * Zistí, či je aktivované zvýšené poškodenie.
     *
     * @return true, ak je zvýšené poškodenie aktívne, inak false
     */
    @Override
    public boolean getJeZvysenePoskodenie() {
        return this.jeZvysenePoskodenie;
    }
    /**
     * Vráti zoznam všetkých nábojov vystrelených z tejto zbrane.
     *
     * @return zoznam vystrelených nábojov
     */
    public ArrayList<Naboj> getZoznamVystrelenychNabojov() {
        return this.zoznamVystrelenychNabojov;
    }
    /**
     * Zaregistruje zbraň do Manazer a pre spracovanie vstupov.
     * @param manazer inštancia Manazer, ktorá spravuje objekty
     */

    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Odstráni zbraň z Manazer a, čím sa prestane spracovávať jej vstupy.
     *
     * @param manazer inštancia Manazerera
     */

    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }


}

