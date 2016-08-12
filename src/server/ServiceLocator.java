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

package server;

import java.util.Locale;
import java.util.logging.Logger;

import tools.Translator;

public class ServiceLocator {
    private static ServiceLocator serviceLocator; // singleton

    // Application-global constants
    final private Class<?> APP_CLASS = ServerView.class;
    final private String APP_NAME = "ServerView";
    
    // Supported locales (for translations)
    final private Locale[] locales = new Locale[] { new Locale("en"), new Locale("de") };

    // Resources
    private Logger logger;
    private Translator translator;

    /**
     * Factory method for returning the singleton
     * @param mainClass The main class of this program
     * @return The singleton resource locator
     */
    
    public static ServiceLocator getServiceLocator() {
    	
        if (serviceLocator == null)
            serviceLocator = new ServiceLocator();
        return serviceLocator;
    }

    /**
     * Private constructor, because this class is a singleton
     * @param appName Name of the main class of this program
     */
    private ServiceLocator() {
    
    }

    public Class<?> getAPP_CLASS() {
        return APP_CLASS;
    }
    
    public String getAPP_NAME() {
        return APP_NAME;
    }

    public Logger getLogger() {
        if(this.logger == null){
        	this.logger = Logger.getLogger(getAPP_NAME());
        	return this.logger;
        }
        else return this.logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    public Locale[] getLocales() {
        return locales;
    }

    public Translator getTranslator() {
        if(this.translator == null){
        	this.translator = new Translator("en");
        	return this.translator;
        }
        else return this.translator;
    }
    
    public void setTranslator(Translator translator) {
        this.translator = translator;
    }
}
