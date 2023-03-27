package org.example.exceptions;

import java.io.IOException;

public class InvalidCatalogException extends Exception {

    public InvalidCatalogException(String unableToLoadCatalog, IOException e) {
        super(unableToLoadCatalog, e);

    }
}
