package edu.pwr.backend.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy


@Configuration
@EnableAspectJAutoProxy
open class DataSourceConfig {

    @PostConstruct
    fun init() {
        // Register available data sources
        DataSourceContextHolder.addDataSource("")
        DataSourceContextHolder.addDataSource("replica1")
        println("[DEBUG] startup after")
    }
}
