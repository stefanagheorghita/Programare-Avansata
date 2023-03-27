package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathsException;

import java.io.File;
import java.io.IOException;

public class CatalogUtil {
    public CatalogUtil() {
    }

    public static void save(Catalog catalog, String path)
            throws InvalidPathsException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(
                    new File(path),
                    catalog);
            System.out.println("saved");
        } catch (IOException e) {
            throw new InvalidPathsException("Unable to find path.");
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = null;
        try {
            catalog = objectMapper.readValue(
                    new File(path),
                    Catalog.class);
            System.out.println("loaded");
            return catalog;
        } catch (IOException e) {

            throw new InvalidCatalogException("Unable to load catalog", e);
        }

    }


}
