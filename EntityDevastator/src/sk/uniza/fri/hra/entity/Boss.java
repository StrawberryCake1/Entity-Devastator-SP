package sk.uniza.fri.hra.entity;

import sk.uniza.fri.hra.entity.vlastnosti.Meteorit;
import sk.uniza.fri.hra.entity.vlastnosti.SpecialnaVlastnost;
import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
import java.util.Random;
/**
 * Trieda Boss reprezentuje špeciálnu entitu typu boss v hre,
 * ktorá má viac životov a vykonáva špeciálne útoky (napr. hádzanie meteoritov).
 * Boss reaguje na útoky znižovaním svojich životov a občasným spustením špeciálneho útoku.
 * Táto trieda implementuje SpecialnaVlastnost a LietajuciObjekt Meteorit,
 * čím zaručuje, že môže vykonávať špeciálne akcie a uchovávať zoznam lietajúcich objektov.
 */
public class Boss extends Entita implements SpecialnaVlastnost, LietajuciObjekt<Meteorit>  {
    private final ZivotyBossa zivotyBossa;
    private final ArrayList<Meteorit> vystrelenneMeteority;
    /**
     * Vytvorí nového bossa s určeným počtom životov a typom entity.
     *
     * @param hrac hráč, na ktorého boss reaguje a útočí
     * @param zivot počiatočný počet životov bossa
     * @param typEntity typ bossa vrátane obrázku a počiatočnej polohy
     */
    public Boss(Hrac hrac, int zivot, TypEntity typEntity) {
        super(hrac, zivot, typEntity);
        this.zivotyBossa = new ZivotyBossa(this);
        this.vystrelenneMeteority = new ArrayList<>();
    }
    /**
     * Prijme útok, zníži životy a vykoná špeciálny útok ak je splnená podmienka.
     *
     * @param poskodenie množstvo poškodenia, ktoré boss prijíma
     */
    @Override
    public void prijimiUtok(int poskodenie) {
        super.prijimiUtok(poskodenie);
        this.zivotyBossa.zmenUkazatelZivota();
        this.vykonajSpecialnyUtok();
    }
    /**
     * Spustí špeciálny útok – s určitou pravdepodobnosťou boss vystrelí meteorit.
     */
    @Override
    public void vykonajSpecialnyUtok() {
        Random random = new Random();
        int nahodna = random.nextInt(11);
        if (nahodna > 6) {
            Meteorit meteorit = new Meteorit();
            this.vystrelenneMeteority.add(meteorit);
        }
    }
    /**
     * Získa zoznam meteorov vystrelených bossom.
     *
     * @return zoznam vystrelených objektov typu {@code Meteorit}
     */
    @Override
    public ArrayList<Meteorit> getZoznamObjektov() {
        return this.vystrelenneMeteority;
    }


}
