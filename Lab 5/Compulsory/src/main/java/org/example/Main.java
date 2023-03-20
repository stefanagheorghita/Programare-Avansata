package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException, InvalidPathsException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoad();
    }

    private void testCreateSave() throws IOException, InvalidPathsException {
        Catalog catalog =
                new Catalog("MyDocuments");
        var book = new Document("article1" );
        var article = new Document("book1");
        catalog.add(book);
        catalog.add(article);

        CatalogUtil.save(catalog, "c:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");
    }

    private void testLoad() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("C:\\Users\\user\\Documents\\AN2 SEM 2\\PA\\Lab 5\\doc.json");


    }
}