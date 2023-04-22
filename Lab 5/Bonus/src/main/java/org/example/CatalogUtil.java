package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entries.Document;
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


    public boolean areConnected(Document d1, Document d2) {
        for (Object o1 : d1.getTags().keySet()) {
            for (Object o2 : d2.getTags().keySet()) {
                if (o1.equals(o2)) {
                    if (d1.getTags().get(o1).equals(d2.getTags().get(o2)))
                        return true;
                }
            }
        }
        return false;
    }


}
