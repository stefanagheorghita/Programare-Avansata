package org.example.commands;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.example.entries.Document;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidPathsException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InfoCommand implements Command {

    private Document document;

    public InfoCommand(Document document) {
        this.document = document;


    }

    private  Metadata getMetadata(String filePath) throws InvalidPathsException, TikaException, IOException, SAXException {
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        FileInputStream stream = new FileInputStream(new File(filePath));
        parser.parse(stream, handler, metadata, context);
        for (String name : metadata.names()) {
            document.getTags().put(name, metadata.get(name));
        }
        return metadata;
    }

    private void info(String filePath) throws Exception {
        Metadata metadata = getMetadata(filePath);
//        for (String name : metadata.names()) {
//            System.out.println(name + ": " + metadata.get(name));
//        }

    }

    @Override
    public void run() throws InvalidCatalogException, Exception {
        if (document == null) {
            System.out.println("This isn't a valid document.");
            System.exit(3);
        }

        info(document.getLocation());

    }


}
