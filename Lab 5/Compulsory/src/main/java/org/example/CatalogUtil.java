package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

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
            return catalog;
        } catch (IOException e) {

            throw new InvalidCatalogException("Unable to load catalog", e);
        }

    }


}
