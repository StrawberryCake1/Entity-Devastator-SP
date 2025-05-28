package sk.uniza.fri.hra;
import sk.uniza.fri.hra.zbrane.StartMenu;
/**
 * Hlavná trieda aplikácie, ktorá slúži ako vstupný bod pre spustenie hry.
 * Vytvára menu na začiatku a čaká, kým hráč začne hru.
 */
public class Main {
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        // Nekonečný cyklus, ktorý čaká, kým hráč nespustí hru
        for (;;) {
            System.out.println("Hra ešte nezačala");
            if (startMenu.getZacalaHra()) {
                Hra hra = new Hra();
                System.out.println("Hra už začala");
                break;
            }
        }
    }
}