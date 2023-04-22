package org.example.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.example.Catalog;
import org.example.exceptions.InvalidCatalogException;
import org.example.exceptions.InvalidFileException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public class ReportCommand implements Command {
    private Catalog catalog;
    private Configuration cfg;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/templates");
    }

    @Override
    public void run() throws  Exception {
        Template template = cfg.getTemplate("templates.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("documents", catalog.getDocumentList());
        StringWriter out = new StringWriter();
        template.process(data, out);
        String output = out.toString();
        try (FileWriter writer = new FileWriter("report.html")) {
            writer.write(output);
        } catch (Exception e) {
                new InvalidFileException("Cannot create this file or cannot use this file for writing.",e);
        }
    }
}
