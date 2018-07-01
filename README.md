# er-diagram-endpoint
Generate ER Diagram automatically and expose it as PNG file on URL.

## Test:
Run application and open:
```
http://localhost:10000/er
```

## Uses:
- SpringBoot
- SchemaCrawler (https://www.schemacrawler.com)
- Graphviz (https://www.graphviz.org)


## Based on StackOverflow entry: 
https://stackoverflow.com/questions/9614957/required-java-api-to-generate-database-er-diagram/33277773#33277773


## Sample database
If you need example database to check how ER Diagram generator works you can check my other repo:
- [test-db-pg-dvdrental](https://github.com/kedziorski/test-db-pg-dvdrental)

There you can find Docker based container with sample PostgreSQL database. 

If you decided to use it remember to change application.properties:
```
spring.datasource.url=jdbc:postgresql://127.0.0.1:5433/dvdrental
spring.datasource.username=dvdrental
spring.datasource.password=dvdrental
```