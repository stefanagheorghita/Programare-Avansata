public class Highway extends Road {
    public Highway(String name, int speed,Location a, Location b) {
        super(name, speed,"HIGHWAY", a, b);
    }

    public void setType() {
        this.type = "HIGHWAY";
    }

    public Highway() {
    }

    public String getType() {
        return "HIGHWAY";
    }
}
