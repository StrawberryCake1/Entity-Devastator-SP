package sk.uniza.fri.hra.zbrane;

import fri.shapesge.Manazer;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Trieda Brokovnica reprezentuje brokovnicu ako typ zbrane,
 * ktorá vystreľuje viac nábojov naraz a má možnosť zvýšiť svoje poškodenie.
 */
public class Brokovnica extends Zbran {
    private final Timer timer;
    private boolean striela;
    private int poskodenie;
    private boolean jeZvysenePoskodenie;
    private final ArrayList<Naboj> zoznamVystrelenychNabojov;
    private int counterZvysenehoPoskodenia;
    private final int velkostZasobnika;
    /**
     * Vytvorí novú brokovnicu priradenú hráčovi s daným typom zbrane.
     *
     * @param hrac hráč, ktorý drží túto zbraň
     * @param typZbrane konfigurácia zbrane (napr. poškodenie)
     */
    public Brokovnica(Hrac hrac, TypZbrane typZbrane) {
        super(hrac, typZbrane);
        this.timer = new Timer();
        this.striela = false;
        this.counterZvysenehoPoskodenia = 0;
        this.poskodenie = typZbrane.getPoskodenie();
        this.velkostZasobnika = 6;
        this.jeZvysenePoskodenie = false;
        this.zoznamVystrelenychNabojov = new ArrayList<>();
    }
    /**
     * Vystrelí z brokovnice. Naraz vystrelí 3 náboje s vertikálnym rozptylom.
     * Ak je aktívne zvýšené poškodenie, po vyčerpaní zásobníka sa zníži.
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
                        Brokovnica.this.striela = false;
                    }
                }, 600);
                for (int i = 0; i < 3; i++) {
                    Naboj nabojBrokovnice = new Naboj(this);
                    nabojBrokovnice.posunZvisle((i * 20));
                    this.zoznamVystrelenychNabojov.add(nabojBrokovnice);
                }

            }
        } else {
            this.skryZbran();
        }
    }
    /**
     * Aktualizuje pozíciu brokovnice podľa pozície hráča.
     */
    @Override
    public void aktualizujPoziciu() {
        super.aktualizujPoziciu();
    }
    /**
     * Vráti aktuálne poškodenie brokovnice.
     *
     * @return hodnota poškodenia
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Nastaví, či je brokovnica momentálne držaná hráčom.
     *
     * @param jeDrzana true ak je držaná, inak false
     */
    @Override
    public void setJeMomentalneDrzana(boolean jeDrzana) {
        super.setJeMomentalneDrzana(jeDrzana);
    }
    /**
     * Zvýši poškodenie brokovnice na dvojnásobok.
     */
    public void zvysPoskodenie() {
        this.setPoskodenie(this.poskodenie * 2);
        this.jeZvysenePoskodenie = true;
    }
    /**
     * Zníži poškodenie brokovnice na pôvodnú hodnotu.
     */
    public void znizPoskodenie() {
        this.setPoskodenie(this.poskodenie / 2);
        this.jeZvysenePoskodenie = false;
    }
    /**
     * Nastaví poškodenie brokovnice.
     *
     * @param novePoskodenie nová hodnota poškodenia
     */
    @Override
    public void setPoskodenie(int novePoskodenie) {
        this.poskodenie = novePoskodenie;
    }
    /**
     * Zistí, či má brokovnica momentálne zvýšené poškodenie.
     *
     * @return true ak je poškodenie zvýšené, inak false
     */
    @Override
    public boolean getJeZvysenePoskodenie() {
        return this.jeZvysenePoskodenie;
    }
    /**
     * Vráti zoznam nábojov, ktoré boli vystrelené z brokovnice.
     *
     * @return zoznam objektov Naboj
     */
    public ArrayList<Naboj> getZoznamVystrelenychNabojov() {
        return this.zoznamVystrelenychNabojov;
    }
    /**
     * Zaregistruje brokovnicu v {@code Manazer}-ovi na spracovanie.
     *
     * @param manazer inštancia správcu objektov
     */
    @Override
    public void spravujObjekt(Manazer manazer) {
        manazer.spravujObjekt(this);
    }
    /**
     * Odstráni brokovnicu z Manazer-a.
     *
     * @param manazer inštancia správcu objektov
     */
    @Override
    public void prestanSpravovatObjekt(Manazer manazer) {
        manazer.prestanSpravovatObjekt(this);
    }
}
