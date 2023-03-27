package org.example;

import org.example.commands.*;
import org.example.entries.Article;
import org.example.entries.Book;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathsException;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws Exception {

        Main app = new Main();
        app.testCreateSave();
        // app.testLoad();
        app.testLoadView();
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

    private void testReport() {

    }

}