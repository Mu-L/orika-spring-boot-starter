# orika-spring-boot-starter

[![Maven Central][Maven Central Badge]][Maven Central]
[![javadoc.io][javadoc.io Badge]][javadoc.io]
[![CircleCI][CircleCI Badge]][CircleCI]
[![Codecov][Codecov Badge]][Codecov]
[![License][License Badge]][License]

[Maven Central Badge]: https://maven-badges.herokuapp.com/maven-central/net.rakugakibox.spring.boot/orika-spring-boot-starter/badge.svg
[Maven Central]: https://maven-badges.herokuapp.com/maven-central/net.rakugakibox.spring.boot/orika-spring-boot-starter
[javadoc.io Badge]: https://www.javadoc.io/badge/net.rakugakibox.spring.boot/orika-spring-boot-starter.svg
[javadoc.io]: https://www.javadoc.io/doc/net.rakugakibox.spring.boot/orika-spring-boot-starter
[CircleCI Badge]: https://circleci.com/gh/akihyro/orika-spring-boot-starter.svg?style=shield
[CircleCI]: https://circleci.com/gh/akihyro/orika-spring-boot-starter
[Codecov Badge]: https://codecov.io/gh/akihyro/orika-spring-boot-starter/branch/master/graph/badge.svg
[Codecov]: https://codecov.io/gh/akihyro/orika-spring-boot-starter
[License Badge]: https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg
[License]: LICENSE.txt

[Spring Boot] Starter for [Orika (Java Bean mapping framework)].  

[Spring Boot]: https://spring.io/projects/spring-boot
[Orika (Java Bean mapping framework)]: http://orika-mapper.github.io/orika-docs/

Note: This page is for Spring Boot 2. If you use Spring Boot 1, please refer to [v1.7.x branch].  

[v1.7.x branch]: https://github.com/akihyro/orika-spring-boot-starter/tree/v1.7.x

## Features

* Manages the `MapperFacade` (Orika's mapper interface) in the application context
  and makes it injectable into your code.
* Provides interface to customize the `MapperFactory`.
* Provides interface to customize the `MapperFactoryBuilder`.

## Supported versions

"orika-spring-boot-starter" supports the following versions.  
Other versions might also work, but we have not tested it.  

* Java 8, 9, 10, 11
* Spring Boot 2.1.3
* Orika 1.5.4

## Usage

### Adding the dependency

"orika-spring-boot-starter" is published on Maven Central Repository.  
If you are using Maven, add the following dependency.  

```xml
<dependency>
    <groupId>net.rakugakibox.spring.boot</groupId>
    <artifactId>orika-spring-boot-starter</artifactId>
    <version>1.9.0</version>
</dependency>
```

### Injecting the `MapperFacade`

The `MapperFacade` (Orika's mapper interface) is managed by the application context.  
Inject the `MapperFacade` into your code.  

For example:  

```java
@Autowired
private MapperFacade orikaMapperFacade;
```

### Mapping your beans

Map your beans using the `MapperFacade`.  

For example:  

```java
PersonSource source = new PersonSource();
source.setFirstName("John");
source.setLastName("Smith");
source.setAge(23);
PersonDestination destination = orikaMapperFacade.map(source, PersonDestination.class);
```

## Customizing

### Customizing the `MapperFactory`

If you need to customize the `MapperFactory`,
create an instance of `OrikaMapperFactoryConfigurer` within the application context.  
`OrikaMapperFactoryConfigurer` components are auto-detected
and the `configure(MapperFactory)` method is called.  

For example:  

```java
@Component
public class PersonMapping implements OrikaMapperFactoryConfigurer {
    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(PersonSource.class, PersonDestination.class)
                .field("firstName", "givenName")
                .field("lastName", "sirName")
                .byDefault()
                .register();
    }
}
```

See also the Orika official documents:  

* [Declarative Mapping Configuration]
* [Advanced Mapping Configurations]

[Declarative Mapping Configuration]: http://orika-mapper.github.io/orika-docs/mappings-via-classmapbuilder.html
[Advanced Mapping Configurations]: http://orika-mapper.github.io/orika-docs/advanced-mappings.html

### Customizing the `MapperFactoryBuilder`

If you need to customize the `MapperFactoryBuilder`,
create an instance of `OrikaMapperFactoryBuilderConfigurer` within the application context.  
`OrikaMapperFactoryBuilderConfigurer` components are auto-detected
and the `configure(MapperFactoryBuilder)` method is called.  

For example:  

```java
@Component
public class CustomOrikaMapperFactoryBuilderConfigurer implements OrikaMapperFactoryBuilderConfigurer {
    @Override
    public void configure(MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder) {
        // Your customization code.
    }
}
```

See also the Orika official documents:  

* [MapperFactory Configuration]

[MapperFactory Configuration]: http://orika-mapper.github.io/orika-docs/mapper-factory.html

## Configuration properties

"orika-spring-boot-starter" provides the following configuration properties.  
These can be configure by your "application.yml" / "application.properties".  

```yml
orika:
  # Whether to enable auto-configuration.
  # Defaults to true.
  enabled: true
  # Whether to use built-in converters (MapperFactoryBuilder#useBuiltinConverters(boolean)).
  # Follows Orika's behavior by default.
  useBuiltinConverters: true
  # Whether to use auto-mapping (MapperFactoryBuilder#useAutoMapping(boolean)).
  # Follows Orika's behavior by default.
  useAutoMapping: true
  # Whether to map null values (MapperFactoryBuilder#mapNulls(boolean)).
  # Follows Orika's behavior by default.
  mapNulls: true
  # Whether to dump the current state of the mapping infrastructure objects
  # upon occurrence of an exception while mapping (MapperFactoryBuilder#dumpStateOnException(boolean)).
  # Follows Orika's behavior by default.
  dumpStateOnException: false
  # Whether the class-map should be considered 'abstract' (MapperFactoryBuilder#favorExtension(boolean)).
  # Follows Orika's behavior by default.
  favorExtension: false
  # Whether full field context should be captured (MapperFactoryBuilder#captureFieldContext(boolean)).
  # Follows Orika's behavior by default.
  captureFieldContext: false
```

## Release notes

Please refer to the "[Releases]" page.  

[Releases]: https://github.com/akihyro/orika-spring-boot-starter/releases

## Contributing

Bug reports and pull requests are welcome :)  

## Building and testing

To build and test, you can run:  

```sh
$ cd orika-spring-boot-starter
$ ./mvnw clean install
```

## License

Licensed under the [Apache License, Version 2.0].  

[Apache License, Version 2.0]: LICENSE.txt
