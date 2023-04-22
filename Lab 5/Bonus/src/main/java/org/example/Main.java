package org.example;

import com.github.javafaker.Faker;
import org.example.commands.*;
import org.example.entries.Article;
import org.example.entries.Book;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathsException;
import org.example.graph.BrownColoringGraph4J;
import org.example.graph.ColoringGraph4J;
import org.example.graph.ColoringJGraphT;
import org.example.graph.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws Exception {

        Main app = new Main();
        //  app.testCreateSave();
        // app.testLoad();
        // app.testLoadView();
        // app.testInfo();
        largeTest();
    }

    private static void largeTest() throws Exception {
        createLargeInstance();
    }

    private static void createLargeInstance() throws Exception {
        System.gc();
        Faker faker = new Faker();
        Catalog catalog =
                new Catalog("MyDocuments");
        for (int i = 0; i <10; i++) {
            String name = faker.name().fullName();
            String edition = faker.educator().course();
            Random random = new Random();

            var book = new Book("book" + i, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab " + i % 13 + 1);
            if (i % 2 == 0) {
                Map<String, Object> tag2 = new HashMap<>();
                tag2.put("author", name);
                tag2.put("year", random.nextInt(1500, 2023));
                book.setTags(tag2);
            }

            var article = new Article("article" + i, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\docu.txt");
            if (i % 2 == 1) {
                Map<String, Object> tag3 = new HashMap<>();
                tag3.put("publisher", edition);
                tag3.put("pages", random.nextInt(100, 1000));
                tag3.put("year", random.nextInt(1500, 2023));
                article.setTags(tag3);
            }

            catalog.add(book);
            catalog.add(article);
            if (random.nextInt(100) < 60) {
                InfoCommand info = new InfoCommand(catalog.findById("article" + i));
                info.run();
            }
            // InfoCommand info2 = new InfoCommand(catalog.findById("book" + i));
            // info2.run();

        }
        CatalogUtil.save(catalog, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
        Graph graph = new Graph(catalog);
        graph.constructGraph1();
        graph.constructGraph2();
        ColoringJGraphT col1 = new ColoringJGraphT();
        ColoringGraph4J col2 = new ColoringGraph4J(graph.getGraph2());
        System.out.println("----------------------------------------------------");
        System.out.println("Graph4J:");
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        col2.graph4JGreedyColoring();
        System.out.println("Greedy:");
        System.out.println("Coloring: " + col2);
        System.out.println("Chromatic number: " + col2.getChromaticNumber());
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Greedy: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        System.out.println("JGraphT:");
        System.out.println("Greedy:");
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        col1.JGraphTGreedyColoring(graph.getGraph1());
        System.out.println("Coloring: " + col1);
        System.out.println("Chromatic number: " + col1.getChromaticNumber());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Greedy: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);

        System.out.println("Graph4J:");
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        col2.graph4JBrownColoring();
        System.out.println("Brown:");
        System.out.println("Coloring: " + col2);
//        BrownColoringGraph4J brown = new BrownColoringGraph4J(graph.getGraph2());
//        System.out.println(brown.findColoring(graph.getGraph2()));
        System.out.println("Chromatic number: " + col2.getChromaticNumber());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
       System.out.println("Brown: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);
        System.out.println("JGraphT:");
        usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        initialTime = System.currentTimeMillis();
        col1.JGraphTBrownColoring(graph.getGraph1());
        System.out.println("Brown:");
        System.out.println("Coloring: " + col1);
        System.out.println("Chromatic number: " + col1.getChromaticNumber());
        runningTime = System.currentTimeMillis() - initialTime;
        usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Brown: " + "Memory increase: " + memoryIncrease + " Running time: " + runningTime);


    }

    private void testInfo() throws Exception {
        LoadCommand l = new LoadCommand(new Catalog(), "C:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
        l.run();
        Catalog catalog = l.getCatalog();
        InfoCommand i = new InfoCommand(catalog.findById("article1"));
        i.run();
    }

    private void testCreateSave() throws Exception {
        Catalog catalog =
                new Catalog("MyDocuments");

        var article = new Article("article1", "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\docu.txt");
        var book = new Book("book1", "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5");
        //   catalog.add(book);
        //   catalog.add(article);
        AddCommand a = new AddCommand(new Catalog());
        a.add(article);
        a.add(book);
        a.run();
        catalog = a.getCatalog();

        System.out.println(catalog);
        // CatalogUtil.save(catalog, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
        SaveCommand s = new SaveCommand(catalog, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
        s.run();

    }

    private void testLoad() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("C:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");


    }

    private void testLoadView() throws Exception {

        LoadCommand l = new LoadCommand(new Catalog(), "C:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
        l.run();
        Catalog catalog = l.getCatalog();
        System.out.println(catalog);
        ViewCommand v = new ViewCommand(catalog.findById("article1"));
        System.out.println(v.getDocument());
        v.run();
        ReportCommand r = new ReportCommand(catalog);
        r.run();

    }

//    void createGraph(){
//        
//        Graph g=new Graph(catalog);
//    }

}