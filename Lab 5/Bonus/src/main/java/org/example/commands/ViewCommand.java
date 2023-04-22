package org.example.commands;

import org.example.entries.Document;
import org.example.exceptions.InvalidPathsException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    private Document d;

    public ViewCommand(Document d) {
        this.d = d;

    }

    public void run() throws InvalidPathsException {

        if (Desktop.isDesktopSupported()) {
            try {

                Desktop desktop = Desktop.getDesktop();
                if (d == null) {
                    System.out.println("We don't have a valid document.");
                    System.exit(2);
                }
                File myFile = new File(d.getLocation());

                desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                throw new InvalidPathsException("Cannot open the file from this path.");
            }
        }

    }

    public Document getDocument() {
        return d;
    }

    public void setDocument(Document d) {
        this.d = d;
    }
}
