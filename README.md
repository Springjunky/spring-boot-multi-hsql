# spring-boot-multi-hsql
A Spring-Boot-Example with an unlimted an easy to configure HSQL file based _permanent_ database

### My intro to GitHub  
### Description

From time to time I need more than one _independet_ und _permanent_ databases to
* see whats going on in my tables
* try out my XA-Transactions 
* do some rapid prototyping with permanent data

Spring-Boot Autoconfigure just provide only *one* database out of the box, if that is enough for you, stop reading..
to all the others ... this is comming soon ... here.

Here is a little spoiler of the usage :-)

``` java
    @ConfigurationProperties(prefix = "cool.database")
    @Bean 
    public HSQLFileDatabaseServer coolDataBase() {
	     return new HSQLFileDatabaseServerImpl(); // thats all, instantiate and starts the server
    }

    @Bean 
    public DataSource coolDatabaseDataSource() {
	     return coolDataBase.getBasicDataSourceForFileDatabaseServer();
    }
    
    ...
    and the Logging is
    
    Your JDBC-Connection-String:  jdbc:hsqldb:hsql://mydomain:4000/COOL_DB
```
so start your favorite database tool, cut&paste the jdbc-connection String and see whats happens or use the DataSource-Bean in your code.


##### Thanks
...to the real cool employees of https://www.codecentric.de/ 

