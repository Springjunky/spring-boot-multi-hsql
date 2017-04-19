# spring-boot-multi-hsql
A Spring-Boot-Example with an unlimted an easy to configure HSQL file based _permanent_ database

### My intro to GitHub  
### Description

From time to time I need more than one _independet_ und _permanent_ databases to
* see whats going on in my tables during first steps of a new project
* try out my XA-Transactions 
* do some rapid prototyping with permanent data

Spring-Boot Autoconfigure just provide only *one* database out of the box, if that is enough for you, stop reading..
to all the others ... this is comming soon ... here.

#### Usage

1. Just clone the source
2. Edit FileDatabaseConfig and add your DataBaseServer with different ConfigurationProperties  
``` java
    @ConfigurationProperties(prefix = "cool.database")
    @Bean 
    public HSQLFileDatabaseServer coolDataBase() {
	     return new HSQLFileDatabaseServerImpl(); // thats all, instantiate and starts the server
    }
  
    @ConfigurationProperties(prefix = "foo.database")
    @Bean 
    public HSQLFileDatabaseServer fooDataBase() {
	     return new HSQLFileDatabaseServerImpl(); // thats all, instantiate and starts the server
    }

```
3. edit the application.properties and add the values

```
# Cool-Database
cool.database.db-name=COOL_DB
cool.database.server-port=5000
cool.database.db-number=1
cool.database.drop-and-create=true
cool.database.create-script=createTableDest.sql
cool.database.fill-script=createRowsSrc.sql
# Foo Database
cool.database.db-name=FOO_DB
cool.database.server-port=6000
cool.database.db-number=3
......
```

4. put the sql-scripts in your classpath (main\resources)

5. Start the Spring-Boot Application an take a closer look at the log-File, the connection-String is there
```` 
 Your JDBC-Connection-String:  jdbc:hsqldb:hsql://mydomain:4000/COOL_DB
```` 
6. so start your favorite database tool, cut&paste the jdbc-connection String and see whats happens or use the DataSource-Bean in your code.

##### Need more ?

Take a closer look at the JUnit-Tests to see how to get the Datasource bind to your configured HSQLDatabaseServer


##### Thanks
...to the real cool employees of https://www.codecentric.de/ 

