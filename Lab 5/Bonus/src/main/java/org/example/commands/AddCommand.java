package org.example.commands;

import org.example.Catalog;
import org.example.entries.Document;
import org.example.exceptions.InvalidCatalogException;

import java.util.ArrayList;
import java.util.List;


public class AddCommand implements Command {
    private Catalog catalog;
    private List<Document> documents=new ArrayList<>();

    public AddCommand(Catalog catalog) {
        this.catalog = catalog;

    }
    public void add(Document d){
        if (documents.contains(d) || catalog.getDocumentList().contains(d))
            System.out.println("The document already exists.");
        else documents.add(d);
    }
    @Override
    public void run() throws Exception {

        catalog.setDocumentList(documents);

    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}

