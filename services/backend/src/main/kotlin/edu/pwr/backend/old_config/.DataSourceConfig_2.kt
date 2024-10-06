//package edu.pwr.backend.config
//
//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
//import org.springframework.beans.factory.annotation.Value
//
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import javax.sql.DataSource
//
//@Configuration
//open class DataSourceConfig {
//
//    @Value("\${spring.datasource.username}")
//    private val username: String? = null
//    @Value("\${spring.datasource.username}")
//    private val password: String? = null
//
//    open fun dataSource(readOnly: Boolean, isAutoCommit: Boolean): DataSource {
//        val config = HikariConfig()
//
//        if (readOnly) {
//            config.jdbcUrl = "jdbc:postgresql://localhost:5434/JobMarketDB"
//            config.username = username
//            config.password = password
//            config.isReadOnly = true
//            config.isAutoCommit = isAutoCommit
//        } else {
//            config.jdbcUrl = "jdbc:postgresql://localhost:5432/JobMarketDB"
//            config.username = username
//            config.password = password
//            config.isReadOnly = false
//            config.isAutoCommit = isAutoCommit
//        }
//        return HikariDataSource(config)
//    }
//
//    @Bean
//    open fun routingDataSource(): DataSource {
//        return RoutingDS(
//            dataSource(false, false),
//            dataSource(true, true)
//        )
//    }
//}