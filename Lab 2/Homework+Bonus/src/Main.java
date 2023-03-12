import java.util.Random;

public class Main {

    public static String generate(int length) {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


        StringBuilder sb = new StringBuilder();


        Random random = new Random();


        for (int i = 0; i < length; i++) {


            int index = random.nextInt(alphabet.length());


            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String name = sb.toString();

        return name;
    }

    public static void main(String[] args) {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();


/* Exemplu de instanta
//    Location[] locations = new Location[3];
//        locations[0] = new City();
//        locations[0].setName("Iasi");
//        locations[0].setX(1);
//        locations[0].setY(2);
//        System.out.println(locations[0].getName());
//        System.out.println(locations[0].getType());
//        System.out.println(locations[0].getX());
//        System.out.println(locations[0].getY());
//
//        System.out.println();
//        locations[1] = new Airport("Otopeni", 4, 3, 5);
//        System.out.println(locations[1].getName());
//        System.out.println(locations[1].getType());
//        System.out.println(locations[1].getX());
//        System.out.println(locations[1].getY());
//        locations[1].setY(7);
//        System.out.println(locations[1].getY());
//        System.out.println(locations[1]);
//
//        locations[2] = new City("Bucuresti", 2, 4, 2_000_000);
//        locations[3] = new GasStation();
//        locations[3].setName("Gas1");
//        locations[3].setX(3);
//        locations[3].setY(5);
//        Road[] roads = new Road[2];
//        roads[0] = new Express("A2", 120, locations[0], locations[2]);
//        System.out.println(roads[0].getLength());
//        System.out.println(roads[0].getType());
//        System.out.println(roads[0].getSpeed());
//        System.out.println(roads[0]);
//        System.out.println();
//        roads[1] = new Highway("A123", 230, locations[0], locations[3]);
//        BestRouteProblem pb = new BestRouteProblem(locations, roads, locations[2], locations[3]);
//        pb.connectedLocations(locations[1], locations[2]);
//        Algorithm alg = new SolutionAlgorithm(pb);
//        Solution sol = alg.solve();
//        System.out.println(sol);

 */

        //generare random instanta mare
        Location[] locations = new Location[601];
        Road[] roads = new Road[600];
        Random ran = new Random();
        for (int i = 0; i < 601; i++) {
            if (i % 3 == 0)
                locations[i] = new City();
            if (i % 3 == 1)
                locations[i] = new Airport();
            if (i % 3 == 2)
                locations[i] = new GasStation();
            String name = new String();
            name = "Location" + i;
            locations[i].setName(name);

            locations[i].setX(ran.nextInt(20));
            locations[i].setY(ran.nextInt(30));
        }
        for (int i = 0; i < 600; i++) {
            if (i % 3 == 0)
                roads[i] = new Express();
            if (i % 3 == 1)
                roads[i] = new Highway();
            if (i % 3 == 2)
                roads[i] = new Country();
            String name;
            name = generate(i % 7 + 2);
            roads[i].setName(name);

            roads[i].start = locations[i];
            roads[i].end = locations[i + 1];
            roads[i].setLength();
        }

        BestRouteProblem pb = new BestRouteProblem(locations, roads, locations[ran.nextInt(601)], locations[ran.nextInt(601)]);

        Algorithm alg = new SolutionAlgorithm(pb);
        Solution sol = alg.solve();
        System.out.println(sol);

        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("\nRunning time: " + runningTime + " \nmemoryIncrease: " + memoryIncrease);
    }
}