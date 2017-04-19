/* @(#) $HeadURL$
 * Hier folgt eine kurze Beschreibung der Datei
 * 
 * Copyright © DEVK 2009. All rights reserved.
 * 
 * $Id$
 */

package com.springjunky.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springjunky.hsqlfileserver.HSQLFileDatabaseServer;
import com.springjunky.hsqlfileserver.HSQLFileDatabaseServerImpl;

/**
 */
@Configuration
public class FileDatabaseConfig {

    @ConfigurationProperties(prefix = "source.database")
    @Bean("SourceDB")
    public HSQLFileDatabaseServer sourceDataBase() {
	// Just return the Bean the @PostConstruct starts it for you
	return new HSQLFileDatabaseServerImpl();
    }

    @ConfigurationProperties(prefix = "dest.database")
    @Bean("DestDB")
    public HSQLFileDatabaseServer destDataBase() {
	return new HSQLFileDatabaseServerImpl();
    }

}
