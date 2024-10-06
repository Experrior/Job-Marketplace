//package edu.pwr.backend.old_config.master_db
//
//import edu.pwr.backend.old_config.DataSourceConfiguration
//import edu.pwr.backend.old_config.DataSourceType
//import edu.pwr.backend.old_config.DatabaseConnectionProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.jdbc.datasource.DriverManagerDataSource
//import javax.sql.DataSource
//
//@Configuration
//open class MasterDataSourceConfiguration : DataSourceConfiguration {
//    override fun dataSourceType(): DataSourceType {
//        return DataSourceType.READ_WRITE
//    }
//
//    @Bean
//    @MasterDataSource
//    open fun masterDataSource(@MasterProperties properties: DatabaseConnectionProperties): DataSource {
//        val dataSource = DriverManagerDataSource()
//
//        dataSource.url = properties.getUrl()
//        dataSource.username = properties.getUsername()
//        dataSource.password = properties.getPassword()
//
//        return definePoolDataSourceConnection(dataSource)
//    }
//}