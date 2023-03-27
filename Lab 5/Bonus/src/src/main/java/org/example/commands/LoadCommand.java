package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Catalog;
import org.example.exceptions.InvalidCatalogException;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    private Catalog catalog;
    private String path;

    public LoadCommand() {
    }

    public LoadCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    public void run()
            throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            catalog = objectMapper.readValue(
                    new File(path),
                    Catalog.class);
            System.out.println("loaded");
            System.out.println(catalog);
            } catch (IOException e) {

            throw new InvalidCatalogException("Unable to load catalog", e);
        }

    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}




