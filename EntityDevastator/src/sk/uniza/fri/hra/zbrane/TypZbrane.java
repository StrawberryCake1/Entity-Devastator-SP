package sk.uniza.fri.hra.zbrane;
/**
 * Výčtový typ TypZbrane reprezentuje rôzne typy zbraní v hre.
 * Každý typ má priradené:
 * - cestu k obrázku zbrane a jej obrátenej verzii,
 * - základné poškodenie,
 * - odchýlku pozície zbrane voči hráčovi (X, Y).
 */
public enum TypZbrane {
    /**
     * Pištoľ - základná zbraň s menším poškodením.
     */
    PISTOL("resources/pistolMala.png", "resources/pistolMalaObratena.png", 15,  15, 15),
    /**
     * Samopal - automatická zbraň so stredným poškodením.
     */
    SAMOPAl("resources/samopal.png", "resources/samopalObrateny.png", 20,  20, -15),
    /**
     * Brokovnica - silná zbraň s veľkým poškodením, bez odchýlky.
     */
    BROKOVNICA("resources/brokovnica.png", "resources/brokovnicaObratena.png", 35, 0, 0),
    /**
     * Útočná puška - vyvážená automatická zbraň s odchýlkou doľava.
     */
    UTOCNAPUSKA("resources/utocnaPuska.png", "resources/utocnaPuskaObratena.png", 25, 0, -40);
    private final String obrazok;
    private final String obratenyObrazok;
    private final int poskodenie;
    private final int odchylkaX;
    private final int odchylkaY;
    /**
     * Konštruktor enum hodnoty typu zbrane.
     *
     * @param obrazok Cesta k obrázku zbrane smerujúcej doprava.
     * @param obratenyObrazok Cesta k obrázku zbrane smerujúcej doľava.
     * @param poskodenie Počiatočná hodnota poškodenia spôsobená zbraňou.
     * @param odchylkaX Vodorovná pozícia zbrane voči hráčovi.
     * @param odchylkaY Zvislá pozícia zbrane voči hráčovi.
     */
    TypZbrane(String obrazok, String obratenyObrazok, int poskodenie, int odchylkaX, int odchylkaY) {
        this.obrazok = obrazok;
        this.obratenyObrazok = obratenyObrazok;
        this.poskodenie = poskodenie;
        this.odchylkaX = odchylkaX;
        this.odchylkaY = odchylkaY;
    }
    /**
     * Získa cestu k obrázku zbrane (smer doprava).
     * @return Cesta k obrázku.
     */
    public String getObrazok() {
        return this.obrazok;
    }
    /**
     * Získa cestu k obrátenému obrázku zbrane (smer doľava).
     * @return Cesta k obrátenému obrázku.
     */
    public String getObratenyObrazok() {
        return this.obratenyObrazok; }
    /**
     * Získa základné poškodenie zbrane.
     * @return Hodnota poškodenia.
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }
    /**
     * Získa vodorovnú odchýlku zbrane od hráča.
     * @return X odchýlka.
     */
    public int getOdchylkaX() {
        return this.odchylkaX;
    }
    /**
     * Získa zvislú odchýlku zbrane od hráča.
     * @return Y odchýlka.
     */
    public int getOdchylkaY() {
        return this.odchylkaY;
    }
}
