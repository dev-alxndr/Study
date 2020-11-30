package io.alxndr.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "io.alxndr.core")
@ComponentScan(basePackages ="io.alxndr.core")
//@EnableJpaRepositories(basePackages = "net.grovesoft.site")
public class ComponentConfig {
}
