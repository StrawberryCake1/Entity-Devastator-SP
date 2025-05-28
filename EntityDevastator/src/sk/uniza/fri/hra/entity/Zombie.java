package sk.uniza.fri.hra.entity;

import sk.uniza.fri.hra.entity.vlastnosti.SpecialnaVlastnost;
import sk.uniza.fri.hra.Hrac;

import java.util.Random;
/**
 * Trieda Zombie predstavuje nepriateľskú entitu, ktorá má šancu
 * pri prijatí útoku aktivovať špeciálnu schopnosť — samoliečenie.
 * Dedi po vlastnostiach zo základnej triedy Entita a implementuje rozhranie SpecialnaVlastnost.
 */
public class Zombie extends Entita implements SpecialnaVlastnost {
    /**
     * Vytvorí novú inštanciu triedy Zombie.
     * @param hrac hráč, s ktorým interaguje
     * @param zivot počiatočný počet životov zombíka
     * @param typEntity typ entity (napr. ZOMBIE)
     */
    public Zombie(Hrac hrac, int zivot, TypEntity typEntity) {
        super(hrac, zivot, typEntity);
    }
    /**
     * Vykoná špeciálny útok — existuje 10% šanca (náhodné číslo 10 z intervalu 0–10),
     * že sa zombie vylieči o 10 životov.
     */
    @Override
    public void vykonajSpecialnyUtok() {
        Random random = new Random();
        int nahodna = random.nextInt(11);
        if (nahodna > 9) {
            System.out.println("Zombie bol vylieceny");
            this.zvysZivoty(10);
        }
    }
    /**
     * Spracuje prijatý útok a zároveň vykoná možný špeciálny útok po prijatí zranenia.
     * @param poskodenie počet bodov poškodenia, ktoré zombie utrpí
     */
    @Override
    public void prijimiUtok(int poskodenie) {
        super.prijimiUtok(poskodenie);
        this.vykonajSpecialnyUtok();
    }

}
