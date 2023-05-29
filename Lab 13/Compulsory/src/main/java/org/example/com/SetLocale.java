package org.example.com;

import org.example.Messages;

import java.text.MessageFormat;
import java.util.Locale;

public class SetLocale {
    public static void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        String message = Messages.getString("locale.set", locale);
        //System.out.println(Messages.getString("locale.set", locale));
        String format = MessageFormat.format(message, locale);
        System.out.println(format);
    }
}