package org.example.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Catalog;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathsException;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command{
    private Catalog catalog;
    private String path;

    public SaveCommand(Catalog catalog, String path) {
        this.catalog = catalog;
        this.path = path;
    }

    @Override
    public void run()
           throws Exception {

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
}
