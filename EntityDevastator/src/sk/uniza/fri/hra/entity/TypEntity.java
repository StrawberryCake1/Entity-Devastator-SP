package sk.uniza.fri.hra.entity;
/**
 * Enum TypEntity definuje rôzne typy nepriateľských entít v hre spolu s ich
 * grafickým zobrazením a počiatočnými súradnicami na hernej mape.
 */
public enum TypEntity {
    /** Zombie entita - objavuje sa ľavom hornom rohu. */
    ZOMBIE("resources/zombie.png", 50, 20),
    /** Duch entita – objavuje sa vpravo horey. */
    DUCH("resources/duch.png", 1000, 600),
    /** Robot entita – objavuje sa vpravo dole. */
    ROBOT("resources/robot.png", 1000, 200),
    /** Mimozemstan entita – objavuje sa vľavo dole. */
    MIMOZEMSTAN("resources/mimozemstan.png", 50, 600),
    /** Boss entita – veľký nepriateľ, objavuje sa v strede mapy. */
    BOSS("resources/darkKnightDevastator.png", 400, 300);
    private final String obrazok;
    private final int polohaX;
    private final int polohaY;
    /**
     * Konštruktor typu entity.
     *
     * @param obrazok Cesta k súboru obrázka reprezentujúceho entitu.
     * @param polohaX Počiatočná vodorovná pozícia entity.
     * @param polohaY Počiatočná zvislá pozícia entity.
     */
    TypEntity(String obrazok, int polohaX, int polohaY) {
        this.obrazok = obrazok;
        this.polohaX = polohaX;
        this.polohaY = polohaY;
    }
    /**
     * Vráti cestu k obrázku reprezentujúcemu entitu.
     *
     * @return Cesta k obrázku.
     */
    public String getObrazok() {
        return this.obrazok;
    }
    /**
     * Vráti počiatočnú X-ovú súradnicu entity.
     *
     * @return Hodnota osi X.
     */
    public int getPolohaX() {
        return this.polohaX;
    }
    /**
     * Vráti počiatočnú Y-ovú súradnicu entity.
     *
     * @return Hodnota osi Y.
     */
    public int getPolohaY() {
        return this.polohaY;
    }
}
