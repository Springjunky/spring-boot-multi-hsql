package com.springjunky.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.springjunky.hsqlfileserver.HSQLFileDatabaseServer;

@Configuration
public class DataSourceConfig {

    static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    @Qualifier("SourceDB")
    HSQLFileDatabaseServer sourceDatabase;

    @Autowired
    @Qualifier("DestDB")
    HSQLFileDatabaseServer destDatabase;

    @Bean("SourceDB-DataSource")
    @Primary // Spring's JPA Autoconfig needs a hint :-)
    public DataSource sourceDatabaseDataSource() {
	logger.info("Creating Datasource of SourceDB");
	return sourceDatabase.getBasicDataSourceForFileDatabaseServer();
    }

    @Bean("DestDB-DataSource")
    public DataSource destDatabaseDataSource() {
	logger.info("Creating Datasource of DestDB ");
	return destDatabase.getBasicDataSourceForFileDatabaseServer();
    }

}
