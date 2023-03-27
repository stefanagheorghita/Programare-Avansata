package org.example;

import org.example.entries.Document;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String name;
    private List<Document> documentList = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog() {
    }


    public void add(Document d) {
        if (documentList.contains(d))
            System.out.println("The document already exists.");
        else documentList.add(d);
    }

    public Document findById(String id) {
        return documentList.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "documentList=" + documentList +
                '}';
    }
}
