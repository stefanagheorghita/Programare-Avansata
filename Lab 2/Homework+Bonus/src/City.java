public class City extends Location {
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    private int population;

    public City(String name, int x, int y, int population) {
        super(name, "CITY", x, y);
        setPopulation(population);
    }

    public City() {
    }

    public void setType() {
        this.type = "CITY";
    }


    public String getType() {
        return "CITY";
    }
}
