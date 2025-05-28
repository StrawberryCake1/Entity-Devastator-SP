package sk.uniza.fri.hra.entity;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import sk.uniza.fri.hra.Hrac;
import sk.uniza.fri.hra.potraviny.Bageta;
import sk.uniza.fri.hra.potraviny.EnergetickyNapoj;
import sk.uniza.fri.hra.potraviny.Potravina;
import sk.uniza.fri.hra.potraviny.Protein;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Abstraktná trieda Entita reprezentuje rodiča entít,
 * ktorá môže interagovať s hráčom, pohybovať sa, útočiť a zanechať po sebe potravu po smrti.
 */
public abstract class Entita {
    private int zivoty;
    private int polohaX;
    private int polohaY;
    private Obrazok entita;
    private final Hrac hrac;
    private final Timer timer;
    private boolean utoci;
    private final Manazer manazer;
    private Potravina potravina;
    /**
     * Vytvorí entitu s daným počtom životov a typom.
     * @param hrac Referencia na hráča (cieľ útoku).
     * @param zivot Počiatočný počet životov entity.
     * @param typEntity Typ entity, ktorý určuje jej obrázok a počiatočnú polohu.
     */
    public Entita(Hrac hrac, int zivot, TypEntity typEntity) {
        this.zivoty = zivot;
        this.polohaX = typEntity.getPolohaX();
        this.polohaY = typEntity.getPolohaY();
        this.entita = new Obrazok(typEntity.getObrazok());
        this.entita.posunVodorovne(typEntity.getPolohaX());
        this.entita.posunZvisle(typEntity.getPolohaY());
        this.hrac = hrac;
        this.entita.zobraz();

        this.potravina = null;
        this.timer = new Timer();
        this.utoci = false;
        this.manazer = new Manazer();

    }
    /**
     * Zvýši počet životov entity.
     * @param okolko O koľko sa majú zvýšiť životy.
     */
    public void zvysZivoty(int okolko) {
        this.zivoty += okolko;
    }
    /**
     * Získa aktuálny počet životov entity.
     * @return Počet životov.
     */
    public int getZivoty() {
        return this.zivoty;
    }
    /**
     * Aplikuje poškodenie entite.
     * @param poskodenie Hodnota poškodenia.
     */
    public void prijimiUtok(int poskodenie) {
        if (this.entita != null) {
            if (this.zivoty >= 1) {
                this.zivoty -= poskodenie;
            }
        }
    }
    /**
     * Vykoná útok na hráča.
     */
    public void zautoc() {
        this.hrac.prijmiUtok();
    }
    /**
     * Ukončí život entity a zobrazí potravu, ak má šťastie.
     */
    public void prestanZit() {
        if (this.entita != null) {
            this.entita.skry();
            this.entita = null;
            this.nahodneVyhodPotravinu();
        }
    }
    /**
     * Získa aktuálnu X pozíciu entity.
     * @return X súradnica.
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Získa aktuálnu Y pozíciu entity.
     * @return Y súradnica.
     */
    public int getPolohaY() {
        return this.polohaY;
    }
    /**
     * Nasleduje hráča a útočí, ak sa priblíži na blízko.
     */
    //Tu som si tiež pomohol internetom s naháňaním hráča.
    public void nahanajHraca()  {
        if (this.entita != null) {
            int dx = this.hrac.getPolohaX() - this.polohaX - 100;
            int dy = this.hrac.getPolohaY() - this.polohaY - 100;
            double vzdialenost = Math.sqrt(dx * dx + dy * dy);
            int moveX = (int)(6 * dx / vzdialenost);
            int moveY = (int)(6 * dy / vzdialenost);
            this.posunVodorovne(moveX);
            this.posunZvisle(moveY);
            if (vzdialenost < 5) {
                if (!this.utoci) {
                    this.utoci = true;
                    //Tu som si tiež pomohol internetom
                    this.timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Entita.this.utoci = false;
                        }
                    }, 1000);
                    this.zautoc();
                }
            }
        }
    }
    /**
     * Posunie entitu po X osi.
     * @param posun Počet pixelov na posun.
     */
    public void posunVodorovne(int posun) {
        this.entita.posunVodorovne(posun);
        this.polohaX += posun;
    }
    /**
     * Posunie entitu po Y osi.
     * @param posun Počet pixelov na posun.
     * @return Posun po Y osi.
     */
    public int posunZvisle(int posun) {
        this.entita.posunZvisle(posun);
        this.polohaY += posun;
        return posun;
    }
    /**
     * Náhodne vytvorí potravu po smrti entity.
     */
    public void nahodneVyhodPotravinu() {
        Random random = new Random();
        int prvaNahoda = random.nextInt(11);
        int druhaNahoda = random.nextInt(4);
        if (prvaNahoda > 7) {
            switch (druhaNahoda) {
                case 1:
                    this.potravina = new EnergetickyNapoj(this.hrac, this, new Obrazok("resources/energetak.png"));
                    this.potravina.nahodneSaZobraz();
                    break;
                case 2:
                    this.potravina = new Protein(this.hrac, this, new Obrazok("resources/protein.png"));
                    this.potravina.nahodneSaZobraz();
                    break;
                case 3:
                    this.potravina = new Bageta(this.hrac, this, new Obrazok("resources/bageta.png"));
                    this.potravina.nahodneSaZobraz();
                    break;
            }
        }
    }
    /**
     * Získa potravu, ktorú entita zanechala.
     * @return Objekt potravy alebo, null ak žiadna nevznikla.
     */
    public Potravina getPotravina() {
        return this.potravina;
    }
    /**
     * Manazer začne spravovať entitu.
     */
    public void spravujEntitu() {
        this.manazer.spravujObjekt(this);
    }
    /**
     * Manazer prestane spravovať entitu.
     */
    public void prestanSpravovatEntitu() {
        this.manazer.prestanSpravovatObjekt(this);
    }
}
