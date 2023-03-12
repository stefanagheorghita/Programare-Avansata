public class Country extends Road {
    public Country(String name, int speed,Location a, Location b) {
        super(name, speed, "COUNTRY", a, b);
    }

    public void setType() {
        this.type = "COUNTRY";
    }

    public Country() {

    }

    public String getType() {
        return "COUNTRY";
    }
}
