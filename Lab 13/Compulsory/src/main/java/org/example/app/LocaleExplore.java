package org.example.app;

import org.example.Messages;
import org.example.com.DisplayLocales;
import org.example.com.Info;
import org.example.com.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Scanner;

public class LocaleExplore {

    private static String currentLocale = "en-US";
    public static void main(String[] args) {
        Locale locale = new Locale("en", "US");
        Messages.setLocale(locale);
        String message=Messages.getString("locale.set", locale);
        String formatt = MessageFormat.format(message, locale);
        System.out.println(formatt);
        Scanner scanner = new Scanner(System.in);

        while (true) {
           String mess= Messages.getString("prompt");
           String format = MessageFormat.format(mess, currentLocale);
            System.out.println(format);
            String command = scanner.nextLine();

            if (command.equals("display locales")) {

                DisplayLocales.execute();
            } else if (command.startsWith("set locale ")) {
                if(command.substring(11).equals("ro")||command.substring(11).equals("ro_RO")|| command.substring(11).equals("en")||command.substring(11).equals("ro-RO"))
                { Locale locale1 = new Locale("ro", "RO");
                Locale.setDefault(locale1);
                Messages.setLocale(locale1);
                }
                if(command.substring(11).equals("en")||command.substring(11).equals("en_US")||command.substring(11).equals("en-US")||command.substring(11).equals("en_US"))
                { Locale locale1 = new Locale("en", "US");
                    Locale.setDefault(locale1);
                    Messages.setLocale(locale1);
                }
                currentLocale = command.substring(11);
                Messages.setLocale(new Locale(currentLocale));
                SetLocale.execute(command.substring(11));


            } else if (command.startsWith("info")) {

                Info.execute(currentLocale);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println(Messages.getString("invalid"));
            }
        }

        scanner.close();
    }

    private static void displayLocales() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        System.out.println(Messages.getString("locales"));
        for (Locale locale : availableLocales) {
            System.out.println(locale.toString());
        }
    }

    private static void setLocale(String localeTag) {
        Locale locale = new Locale(localeTag);
        Messages.setLocale(locale);
        System.out.println(Messages.getString("locale.set"));
    }


}
