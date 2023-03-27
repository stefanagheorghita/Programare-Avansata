package org.example.commands;

import org.example.Catalog;
import org.example.exceptions.InvalidCatalogException;

public class ListCommand implements Command {

    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void run() throws InvalidCatalogException, Exception {
        System.out.println(
                "documentList=" + catalog.getDocumentList());
    }
}
