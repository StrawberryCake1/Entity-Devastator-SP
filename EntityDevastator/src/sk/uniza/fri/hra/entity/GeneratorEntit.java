package sk.uniza.fri.hra.entity;

import sk.uniza.fri.hra.Hrac;

import java.util.ArrayList;
/**
 * Trieda GeneratorEntit slúži na vytváranie a správu entít v hre.
 * Na základe čísla kola generuje príslušný počet entít (nepriateľov) alebo bossa.
 */
public class GeneratorEntit {
    private final Hrac hrac;
    private final ArrayList<Entita> zoznamEntit;
    /**
     * Vytvorí nový generátor entít s referenciou na hráča.
     * @param hrac Inštancia hráča, ktorého budú nepriatelia naháňať a napádať.
     */
    public GeneratorEntit(Hrac hrac) {
        this.hrac = hrac;
        this.zoznamEntit = new ArrayList<>();
    }
    /**
     * Generuje novú vlnu entít podľa čísla kola.
     * Každé kolo obsahuje viac entít. V 4. kole sa vygeneruje boss.
     *
     * @param cisloKola Aktuálne číslo kola hry.
     */
    public void generujVlnu(int cisloKola) {
        int rozostupX = 150;
        int rozostupY;
        if (cisloKola == 4) {
            Entita boss = new Boss(this.hrac, 800, TypEntity.BOSS);
            this.zoznamEntit.add(boss);
        } else {
            for (int i = 0; i < cisloKola; i++) {
                if (i % 2 == 1) {
                    rozostupY = 100;
                } else {
                    rozostupY = 0;
                }
                Duch duch = new Duch(this.hrac, 200, TypEntity.DUCH);
                Mimozemstan mimozemstan = new Mimozemstan(this.hrac, 110, TypEntity.MIMOZEMSTAN);
                Zombie zombie = new Zombie(this.hrac, 120, TypEntity.ZOMBIE);
                Robot robot = new Robot(this.hrac, 100, TypEntity.ROBOT);
                duch.posunVodorovne(i * rozostupX);
                duch.posunZvisle(rozostupY);
                mimozemstan.posunVodorovne(i * rozostupX);
                mimozemstan.posunZvisle(rozostupY);
                zombie.posunVodorovne(i * rozostupX);
                zombie.posunZvisle(rozostupY);
                robot.posunVodorovne(i * rozostupX);
                robot.posunZvisle(rozostupY);
                this.zoznamEntit.add(duch);
                this.zoznamEntit.add(mimozemstan);
                this.zoznamEntit.add(zombie);
                this.zoznamEntit.add(robot);
            }
        }
    }
    /**
     * Získa aktuálny zoznam všetkých entít na ploche.
     *
     * @return Zoznam entít.
     */
    public ArrayList<Entita> getAktualnyZoznamEntit() {
        return this.zoznamEntit;
    }
    /**
     * Manažér začne spravovať entity
     */
    public void prestanSpravovatEntity() {
        for (Entita e : this.zoznamEntit) {
            e.prestanSpravovatEntitu();
        }
    }
    /**
     Manažér prestane spravovať entity
     */
    public void spravujEntity() {
        for (Entita e : this.zoznamEntit) {
            e.spravujEntitu();
        }
    }
}
