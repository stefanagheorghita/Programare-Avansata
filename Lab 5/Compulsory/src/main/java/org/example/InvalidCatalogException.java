package org.example;

import java.io.IOException;

public class InvalidCatalogException extends Throwable {

    public InvalidCatalogException(String unableToLoadCatalog, IOException e) {
        super(unableToLoadCatalog, e);

    }
}
