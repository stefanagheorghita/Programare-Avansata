package org.example.commands;

import org.example.Catalog;
import org.example.exceptions.InvalidCatalogException;

public class ToStringCommand implements Command {

    private Catalog catalog;

    public ToStringCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void run() throws Exception {
        System.out.println("Catalog{" +
                "documentList=" + catalog.getDocumentList() +
                '}');

    }
}