package sk.uniza.fri.hra.entity;

import sk.uniza.fri.hra.entity.vlastnosti.SpecialnaVlastnost;
import sk.uniza.fri.hra.entity.vlastnosti.Ufo;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda reprezentujúca mimozemšťana ako entitu v hre.
 * Mimozemšťan má schopnosť vykonať špeciálny útok – vystreliť UFO,
 * ktoré sa uchováva v zozname vystrelených objektov.
 */
public class Mimozemstan extends Entita implements SpecialnaVlastnost, LietajuciObjekt<Ufo> {
    private final ArrayList<Ufo> vystreleneUfa;
    /**
     * Konštruktor triedy Mimozemstan.
     * @param hrac       hráč, ku ktorému entita patrí
     * @param zivot      počet životov entity
     * @param typEntity  typ entity (napr. nepriateľ, spojenecký atď.)
     */
    public Mimozemstan(Hrac hrac, int zivot, TypEntity typEntity) {
        super(hrac, zivot, typEntity);
        this.vystreleneUfa = new ArrayList<>();
    }

    /**
     * Prijme útok a aplikuje poškodenie na entitu.
     * Po prijatí útoku automaticky spustí špeciálny útok.
     *
     * @param poskodenie množstvo spôsobeného poškodenia
     */
    @Override
    public void prijimiUtok(int poskodenie) {
        super.prijimiUtok(poskodenie);
        this.vykonajSpecialnyUtok();
    }

    /**
     * Vykoná špeciálny útok s pravdepodobnosťou 1/11.
     * V prípade úspechu vytvorí nové UFO, spustí jeho pohyb a pridá ho do zoznamu.
     */
    @Override
    public void vykonajSpecialnyUtok() {
        Random random = new Random();
        int nahodna = random.nextInt(11);
        if (nahodna > 9) {
            Ufo ufo = new Ufo();
            ufo.letObjektu();
            this.vystreleneUfa.add(ufo);
        }
    }

    /**
     * Vráti zoznam všetkých vystrelených UFO objektov týmto mimozemšťanom.
     *
     * @return zoznam UFO objektov
     */
    @Override
    public ArrayList<Ufo> getZoznamObjektov() {
        return this.vystreleneUfa;
    }
}
