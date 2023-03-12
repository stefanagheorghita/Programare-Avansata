public class Airport extends Location {
    /**
     *
     * @return numarul de ternimale
     */
    public int getNrTerminals() {
        return nrTerminals;
    }

    /**
     *
     * @param nrTerminals
     */
    public void setNrTerminals(int nrTerminals) {
        this.nrTerminals = nrTerminals;
    }

    private int nrTerminals;

    /**
     *
     * @param name
     * @param x
     * @param y
     * @param nrTerminals
     * constructorul clasei Airport, cu parametri
     */
    public Airport(String name,int x, int y, int nrTerminals) {
        super(name, "AIRPORT", x, y);
        setNrTerminals(nrTerminals);
    }

    /**
     * constructorul clasei Airport, fara parametri
     */
    public Airport() {
    }

    public void setType() {
        this.type = "AIRPORT";
    }


    public String getType() {
        return "AIRPORT";
    }
}
