
public class Main {
    public static void main(String[] args) {
        Location loc1 = new Location();
        loc1.setName("Iasi");
        loc1.setType(LocationType.CITY);
        loc1.setX(123);
        loc1.setY(234);
        System.out.println(loc1.getName());
        System.out.println(loc1.getType());
        System.out.println(loc1.getX());
        System.out.println(loc1.getY());

        System.out.println();
        Location loc2 = new Location("Otopeni", LocationType.AIRPORT, 456, 253);
        System.out.println(loc2.getName());
        System.out.println(loc2.getType());
        System.out.println(loc2.getX());
        System.out.println(loc2.getY());
        loc2.setY(467);
        System.out.println(loc2.getY());
        System.out.println(loc2);

        System.out.println();
        Road r1 = new Road(150, RoadTypes.HIGHWAY, 500);
        System.out.println(r1.getLength());
        System.out.println(r1.getType());
        System.out.println(r1.getSpeed());
        System.out.println(r1);

        Road r2 = new Road();
        r2.setType(RoadTypes.EXPRESS);

    }
}