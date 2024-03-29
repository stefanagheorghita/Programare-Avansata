package org.example;

import javax.persistence.Persistence;
import java.util.Properties;

public class EntityManagerFactoryObject {
    private static final String PERSISTENCE_UNIT_NAME = "ExamplePU";
    private static javax.persistence.EntityManagerFactory entityManagerFactory;

    private EntityManagerFactoryObject() {}

    public static synchronized javax.persistence.EntityManagerFactory getInstance() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

}
