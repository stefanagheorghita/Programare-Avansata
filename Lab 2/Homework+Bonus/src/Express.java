/**
 * Clasa ce reprezinta un tip de sosea
 */
public class Express extends Road {
    /**
     * constructor default al clasei
     */
    public Express() {
    }

    /**
     * constructor cu parametri
     * @param name - nume
     * @param speed -viteza
     * @param a - locatie inceput
     * @param b - locatie sfarsit
     */
    public Express(String name, int speed,Location a, Location b) {
        super(name, speed,"EXPRESS", a, b);
    }

    /**
     * setare tip
     */
    public void setType() {
        this.type = "EXPRESS";
    }

    /**
     *
     * @return tipul
     */

    public String getType() {
        return "EXPRESS";
    }
}
