package edu.pwr.backend.config
import edu.pwr.backend.graphql.TimestampScalar
import graphql.GraphQL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
open class GraphQLConfig {

    @Bean
    open fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder ->
            wiringBuilder.scalar(TimestampScalar)
        }
    }
}
