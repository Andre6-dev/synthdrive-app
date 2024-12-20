package io.devandre.synthdrive.shared.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        // Register datetime, uuid and bigdecimal scalars
        return runtimeWiring ->
                runtimeWiring.scalar(ExtendedScalars.DateTime)
                        .scalar(ExtendedScalars.DateTime)
                        .scalar(ExtendedScalars.UUID)
                        .scalar(ExtendedScalars.GraphQLBigDecimal);
    }
}
