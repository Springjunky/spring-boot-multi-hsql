package com.springjunky;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.springjunky.hsqlfileserver.HSQLFileDatabaseServer;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    @Qualifier("SourceDB-DataSource")
    DataSource sourceDataSource;

    @Autowired
    @Qualifier("DestDB-DataSource")
    DataSource destDataSource;

    
    @Autowired
    @Qualifier ("SourceDB")
    HSQLFileDatabaseServer sourceServer;
    
    
    @Test
    public void contextLoads() {
	logger.debug("all happy");
    }

    @Test
    public void testInitializedSourceDatabase() {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(sourceDataSource);
	Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM TEST_TABLE ", Integer.class);
	assertTrue("No rows selected", count > 0);

    }

    /**
     * if dest.database.drop-and-create=false 
     * this test fails, because the Database exists. 
     */
    @Test
    public void testInitializedDestDatabase() {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(destDataSource);
	jdbcTemplate.update ("INSERT INTO  TEST_TABLE_DEST (Col1,Col2,Col3,Col4) Values (1,2,3,4)" );
	Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM TEST_TABLE_DEST ", Integer.class);
	assertTrue("To many rows selected", count == 1);
    }
    
    @Test
    public void testDataSource ()
    {
	DataSource dataSource = sourceServer.getBasicDataSourceForFileDatabaseServer();
	assertNotNull (dataSource );
    }
    
    @Test
    public void testJdbcConnection ()
    {
	String connectString = sourceServer.getJdbcConnectionString();
	assertTrue (connectString.contains("jdbc:hsqldb:hsql://"));
    }
    
    
    
}
