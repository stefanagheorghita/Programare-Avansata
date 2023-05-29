package org.example.com;

import org.example.Messages;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info {
    public static void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);

        String mess=Messages.getString("info", locale);
        String format = MessageFormat.format(mess, locale);
        System.out.println(format);
        String country = Locale.getDefault().getDisplayCountry();
        String language = locale.getDisplayLanguage();


        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        String[] weekDays = dateFormatSymbols.getWeekdays();
        String[] months = dateFormatSymbols.getMonths();
        if(locale.equals(Locale.forLanguageTag("ro-RO")) || locale.equals(Locale.forLanguageTag("ro"))) {
            System.out.println("Tara: " + country);
            System.out.println("Limba: " + language);
            try {
                Currency currency = Currency.getInstance( locale);
                String currencyCode = currency.getCurrencyCode();
                String currencySymbol = currency.getSymbol();

                System.out.println("Moneda: " + currencyCode + " (" + currencySymbol + ")");
            } catch (IllegalArgumentException e) {
                System.out.println("Nu exista informatii.");
            }
            System.out.println("Zilele saptamanii: " + String.join(", ", weekDays));
            System.out.println("Luni: " + String.join(", ", months));
            System.out.println("Azi: " + DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()));
        }
        else{
        System.out.println("Country: " + country);
        System.out.println("Language: " + language);
            try {
                Currency currency = Currency.getInstance( locale);
                String currencyCode = currency.getCurrencyCode();
                String currencySymbol = currency.getSymbol();

                System.out.println("Currency: " + currencyCode + " (" + currencySymbol + ")");
            } catch (IllegalArgumentException e) {
                System.out.println("Currency information not available for the specified locale.");
            }
        System.out.println("Week Days: " + String.join(", ", weekDays));
        System.out.println("Months: " + String.join(", ", months));
        System.out.println("Today: " + DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()));}
    }
}
