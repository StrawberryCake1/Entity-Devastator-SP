package sk.uniza.fri.hra.entity;

import sk.uniza.fri.hra.entity.vlastnosti.ElektrickyVyboj;
import sk.uniza.fri.hra.entity.vlastnosti.SpecialnaVlastnost;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Random;
/**
 * Trieda Robot reprezentuje nepriateľskú entitu v hre, ktorá má schopnosť vykonávať
 * špeciálny útok v podobe elektrických výbojov.
 * Tento špeciálny útok má náhodnú pravdepodobnosť aktivácie po prijatí útoku.
 * Trieda implementuje SpecialnaVlastnost a LietajuciObjekt,
 * čím umožňuje spravovanie výbojov, ktoré vytvára.
 */
public class Robot extends Entita implements SpecialnaVlastnost, LietajuciObjekt<ElektrickyVyboj> {
    private final ArrayList<ElektrickyVyboj> zoznamVybojov;
    /**
     * Vytvorí novú inštanciu robota so zadaným hráčom, počtom životov a typom entity.
     * @param hrac hráč, proti ktorému robot bojuje
     * @param zivot počiatočný počet životov robota
     * @param typEntity typ robota (napr. "robot", "boss robot", atď.)
     */
    public Robot(Hrac hrac, int zivot, TypEntity typEntity) {
        super(hrac, zivot, typEntity);
        this.zoznamVybojov = new ArrayList<>();

    }
    /**
     * Náhodne vykoná špeciálny útok – vygeneruje dva elektrické výboje,
     * ktoré sú pridané do zoznamu výbojov, ak náhodné číslo je väčšie ako 9.
     */
    @Override
    public void vykonajSpecialnyUtok() {
        Random random = new Random();
        int nahodna = random.nextInt(11);
        if (nahodna > 9) {
            ElektrickyVyboj elektrickyVybojJedna = new ElektrickyVyboj(this, true);
            ElektrickyVyboj elektrickyVybojDva = new ElektrickyVyboj(this, false);
            this.zoznamVybojov.add(elektrickyVybojJedna);
            this.zoznamVybojov.add(elektrickyVybojDva);
        }
    }
    /**
     * Zavolá metódu prijimiUtok nad rodičovskou triedou a následne sa môže spustiť špeciálny útok.
     *
     * @param poskodenie hodnota poškodenia, ktoré má byť odpočítané zo života robota
     */
    public void prijimiUtok(int poskodenie) {
        super.prijimiUtok(poskodenie);
        this.vykonajSpecialnyUtok();
    }
    /**
     * Vráti zoznam elektrických výbojov, ktoré robot vytvoril.
     * @return zoznam elektrických výbojov
     */
    @Override
    public ArrayList<ElektrickyVyboj> getZoznamObjektov() {
        return this.zoznamVybojov;
    }

}
