# orika-spring-boot-starter

[![maven central badge]][maven central]
[![javadoc badge]][javadoc]
[![release badge]][release]
[![build badge]][build]
[![codecov badge]][codecov]
[![license badge]][license]

[maven central]: https://maven-badges.herokuapp.com/maven-central/dev.akkinoc.spring.boot/orika-spring-boot-starter
[maven central badge]: https://maven-badges.herokuapp.com/maven-central/dev.akkinoc.spring.boot/orika-spring-boot-starter/badge.svg
[javadoc]: https://javadoc.io/doc/dev.akkinoc.spring.boot/orika-spring-boot-starter
[javadoc badge]: https://javadoc.io/badge2/dev.akkinoc.spring.boot/orika-spring-boot-starter/javadoc.svg
[release]: https://github.com/akkinoc/orika-spring-boot-starter/releases
[release badge]: https://img.shields.io/github/v/release/akkinoc/orika-spring-boot-starter?color=brightgreen&sort=semver
[build]: https://github.com/akkinoc/orika-spring-boot-starter/actions/workflows/build.yml
[build badge]: https://github.com/akkinoc/orika-spring-boot-starter/actions/workflows/build.yml/badge.svg
[codecov]: https://codecov.io/gh/akkinoc/orika-spring-boot-starter
[codecov badge]: https://codecov.io/gh/akkinoc/orika-spring-boot-starter/branch/main/graph/badge.svg
[license]: LICENSE.txt
[license badge]: https://img.shields.io/github/license/akkinoc/orika-spring-boot-starter?color=blue

[Spring Boot] Starter for [Orika].

[Spring Boot]: https://spring.io/projects/spring-boot
[Orika]: https://orika-mapper.github.io/orika-docs

## Features

* Manages MapperFacade in the application context and makes it injectable into your code.
* Provides an interface to configure MapperFactory.
* Provides an interface to configure MapperFactoryBuilder.
* Provides configuration properties to configure MapperFactoryBuilder.

## Dependencies

Depends on:

* Java 8, 11 or 15
* Kotlin 1.5
* Spring Boot 2.5
* Orika 1.5

Other versions may also work, but have not been tested.

## Usage

### Adding the dependency

The artifact is published on [Maven Central Repository][maven central].
If you are using Maven, add the following dependency.

```xml
<dependency>
    <groupId>dev.akkinoc.spring.boot</groupId>
    <artifactId>orika-spring-boot-starter</artifactId>
    <version>${orika-spring-boot-starter.version}</version>
</dependency>
```

### Injecting the MapperFacade

The MapperFacade is managed in the application context.
Inject the MapperFacade into your code.

For example in Java:

```java
import ma.glasnost.orika.MapperFacade;
```

```java
@Autowired
private MapperFacade orikaMapperFacade;
```

### Mapping your beans

Map your beans using the MapperFacade.

For example in Java:

```java
// Maps from PersonSource to PersonDestination
PersonSource src = new PersonSource("John", "Smith", 23);
System.out.println(src);   // => "PersonSource(firstName=John, lastName=Smith, age=23)"
PersonDestination dest = orikaMapperFacade.map(src, PersonDestination.class);
System.out.println(dest);  // => "PersonDestination(givenName=John, sirName=Smith, age=23)"
```

## MapperFactory Configuration

If you need to configure the MapperFactory,
create an instance of OrikaMapperFactoryConfigurer in the application context.
The OrikaMapperFactoryConfigurer components are auto-detected and the "configure" method is called.

For example in Java:

```java
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;

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

See also the Orika User Guide:

* [Declarative Mapping Configuration](https://orika-mapper.github.io/orika-docs/mappings-via-classmapbuilder.html)
* [Advanced Mapping Configurations](https://orika-mapper.github.io/orika-docs/advanced-mappings.html)

## MapperFactoryBuilder Configuration

If you need to configure the MapperFactoryBuilder,
create an instance of OrikaMapperFactoryBuilderConfigurer in the application context.
The OrikaMapperFactoryBuilderConfigurer components are auto-detected and the "configure" method is called.

For example in Java:

```java
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryBuilderConfigurer;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;

@Component
public class OrikaConfiguration implements OrikaMapperFactoryBuilderConfigurer {
    @Override
    public void configure(MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder) {
        // Your configuration codes.
    }
}
```

See also the Orika User Guide:

* [MapperFactory Configuration](https://orika-mapper.github.io/orika-docs/mapper-factory.html)

## Configuration Properties

Provides the following configuration properties.
These can be configured by your "application.yml" / "application.properties".

```yaml
# The configuration properties for Orika.
orika:
  # Whether to enable auto-configuration.
  # Defaults to true.
  enabled: true
  # Whether to use built-in converters.
  # See also MapperFactoryBuilder.useBuiltinConverters.
  # By default, follows Orika's behavior.
  use-builtin-converters: true
  # Whether to use auto-mapping.
  # See also MapperFactoryBuilder.useAutoMapping.
  # By default, follows Orika's behavior.
  use-auto-mapping: true
  # Whether to map nulls.
  # See also MapperFactoryBuilder.mapNulls.
  # By default, follows Orika's behavior.
  map-nulls: true
  # Whether to dump the current state of the mapping infrastructure objects
  # upon occurrence of an exception while mapping.
  # See also MapperFactoryBuilder.dumpStateOnException.
  # By default, follows Orika's behavior.
  dump-state-on-exception: false
  # Whether to favor extension by default in registered class-maps.
  # See also MapperFactoryBuilder.favorExtension.
  # By default, follows Orika's behavior.
  favor-extension: false
  # Whether full field context should be captured.
  # See also MapperFactoryBuilder.captureFieldContext.
  # By default, follows Orika's behavior.
  capture-field-context: false
```

## API Reference

Please refer to the [Javadoc][javadoc].

## Release Notes

Please refer to the [Releases][release] page.

## License

Licensed under the [Apache License, Version 2.0][license].
