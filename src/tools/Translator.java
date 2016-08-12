/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @function The singleton instance of this class provide central storage for resources
 * used by the program. It also defines application-global constants, such as
 * the application name.
 * 
 * @author Brad Richards
 */

package tools;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import client.ServiceLocator;


public class Translator {
    private ServiceLocator sl = ServiceLocator.getServiceLocator();
    private Logger logger = sl.getLogger();
    
    protected Locale currentLocale;
    private ResourceBundle resourceBundle;

    public Translator(String localeString) {
    	

    	
        // Can we find the language in our supported locales?
        // If not, use VM default locale
        Locale locale = Locale.getDefault();
        if (localeString != null) {
            Locale[] availableLocales = sl.getLocales();
            for (int i = 0; i < availableLocales.length; i++) {
                String tmpLang = availableLocales[i].getLanguage();
                if (localeString.substring(0, tmpLang.length()).equals(tmpLang)) {
                    locale = availableLocales[i];
                    break;
                }
            }
        }
        
        // Load the resource strings
        resourceBundle = ResourceBundle.getBundle("tools.Language", locale);
        Locale.setDefault(locale); // Change VM default (for dialogs, etc.)
        currentLocale = locale;
        
        logger.info("Loaded resources for " + locale.getLanguage());
    }
    
    /**
     * Return the current locale; this is useful for formatters, etc.
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    public String getCurrentLocaleString(){
    	return currentLocale.toString();
    }

    /**
     * Public method to get string resources, default to "--" *
     */
    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            logger.warning("Missing string: " + key);
            return "--";
        }
    }
}
