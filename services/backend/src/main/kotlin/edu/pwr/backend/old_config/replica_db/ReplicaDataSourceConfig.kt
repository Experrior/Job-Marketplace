//package edu.pwr.backend.old_config.replica_db
//
//import edu.pwr.backend.old_config.DataSourceConfiguration
//import edu.pwr.backend.old_config.DataSourceType
//import edu.pwr.backend.old_config.DataSourceType.READ_ONLY
//import edu.pwr.backend.old_config.DatabaseConnectionProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.jdbc.datasource.DriverManagerDataSource
//import javax.sql.DataSource
//
//@Configuration
//open class ReplicaDataSourceConfiguration : DataSourceConfiguration {
//    override fun dataSourceType(): DataSourceType {
//        return READ_ONLY
//    }
//
//    @Bean
//    @ReplicaDataSource
//    open fun replicaDataSource(@ReplicaProperties properties: DatabaseConnectionProperties): DataSource {
//        val dataSource = DriverManagerDataSource()
//
//        dataSource.url = properties.getUrl()
//        dataSource.username = properties.getUsername()
//        dataSource.password = properties.getPassword()
//
//        return definePoolDataSourceConnection(dataSource)
//    }
//}