# task-manager-api

## Test containers :

```
runtime 'mysql:mysql-connector-java:8.0.19'
testCompile "org.testcontainers:testcontainers:1.14.2"
testImplementation('org.testcontainers:mysql:1.12.4') 
```

```
public abstract class AbstractContainerBaseTest {

    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer();
        MY_SQL_CONTAINER.start();
    }
}
```

- [Spring boot Controller test](https://spring.io/guides/gs/testing-web/)
- ObjectMapper configuration for date in the request to avoid date getting converted to date with timestamp

```
ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
 false);
```