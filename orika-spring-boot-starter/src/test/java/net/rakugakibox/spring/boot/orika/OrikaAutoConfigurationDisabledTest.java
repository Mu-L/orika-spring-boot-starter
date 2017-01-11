package net.rakugakibox.spring.boot.orika;

import java.util.Optional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The {@link OrikaAutoConfiguration}'s test cases when disabled.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("orika.enabled=false")
public class OrikaAutoConfigurationDisabledTest {

    /**
     * The auto-configuration properties for Orika.
     */
    @Autowired
    protected Optional<OrikaProperties> orikaProperties;

    /**
     * The {@link DefaultMapperFactory.MapperFactoryBuilder}.
     */
    @Autowired
    protected Optional<DefaultMapperFactory.MapperFactoryBuilder<?, ?>> mapperFactoryBuilder;

    /**
     * The {@link MapperFactory}.
     */
    @Autowired
    protected Optional<MapperFactory> mapperFactory;

    /**
     * The {@link MapperFacade}.
     */
    @Autowired
    protected Optional<MapperFacade> mapperFacade;

    /**
     * Tests the {@link OrikaAutoConfiguration}'s configuration properties.
     */
    @Test
    public void orikaProperties_shouldNotBeCreated() {
        assertThat(orikaProperties).isNotPresent();
    }

    /**
     * Tests the {@link OrikaAutoConfiguration#orikaMapperFactoryBuilder()}.
     */
    @Test
    public void orikaMapperFactoryBuilder_shouldNotBeCreated() {
        assertThat(mapperFactoryBuilder).isNotPresent();
    }

    /**
     * Tests the {@link OrikaAutoConfiguration#orikaMapperFactory(DefaultMapperFactory.MapperFactoryBuilder)}.
     */
    @Test
    public void orikaMapperFactory_shouldNotBeCreated() {
        assertThat(mapperFactory).isNotPresent();
    }

    /**
     * Tests the {@link OrikaAutoConfiguration#orikaMapperFacade(MapperFactory)}.
     */
    @Test
    public void orikaMapperFacade_shouldNotBeCreated() {
        assertThat(mapperFacade).isNotPresent();
    }

    /**
     * The context configuration.
     */
    @Configuration
    @EnableAutoConfiguration
    public static class ContextConfiguration {
    }

}
