
package com.springjunky.hsqlfileserver;

import javax.sql.DataSource;

/**
 */
public interface HSQLFileDatabaseServer {
    
    public String getJdbcConnectionString();

    public DataSource getBasicDataSourceForFileDatabaseServer();
}
