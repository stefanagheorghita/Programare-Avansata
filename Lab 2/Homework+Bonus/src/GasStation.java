/**
 * Clasa mostenita din Location
 */
public class GasStation extends Location {
    /**
     *
     * @return pret
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price, pretul
     */
    public void setPrice(int price) {
        this.price = price;
    }


    private int price;

    /**
     * constructorul fara parametri al clasei GasStation
     */
    public GasStation() {
    }

    /**
     * constructorul cu pasametri al clasei GasStation
     * @param name
     * @param x
     * @param y
     * @param price
     */
    public GasStation(String name,int x, int y, int price) {
        super(name, "GAS STATION", x, y);
        setPrice(price);
    }

    /**
     * setare tip
     */
    public void setType() {
        this.type = "GAS STATION";
    }

    /**
     *
     * @return tipul
     */
    public String getType() {
        return "GAS STATION";
    }
}
