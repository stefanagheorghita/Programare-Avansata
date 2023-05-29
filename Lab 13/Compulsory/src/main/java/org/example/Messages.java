package org.example;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "res.Messages";
    private static ResourceBundle RESOURCE_BUNDLE;

    private Messages() {
    }

    public static void setLocale(Locale locale) {
        RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getString(String key, Object... args) {
        try {
            String message = RESOURCE_BUNDLE.getString(key);
            return String.format(message, args);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return '!' + key + '!';
        }
    }
}
