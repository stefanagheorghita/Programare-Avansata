package org.example.com;

import org.example.Messages;

import java.util.Locale;

public class DisplayLocales {
    public static void execute() {
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(Messages.getString("locales"));
        for (Locale locale : locales) {
            System.out.println(locale);
        }
    }
}