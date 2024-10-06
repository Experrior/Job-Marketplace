//package edu.pwr.backend.old_config
//
//import com.zaxxer.hikari.HikariConfig
//import com.zaxxer.hikari.HikariDataSource
//import javax.sql.DataSource
//
//interface DataSourceConfiguration {
//    fun dataSourceType(): DataSourceType
//
//    fun definePoolDataSourceConnection(dataSource: DataSource): HikariDataSource {
//        return HikariDataSource(hikariConfig(dataSource))
//    }
//
//    private fun hikariConfig(dataSource: DataSource): HikariConfig {
//        val hikariConfig = HikariConfig()
//        val dataSourceType: DataSourceType = dataSourceType()
//
//        hikariConfig.poolName = dataSourceType.poolName()
//        hikariConfig.maximumPoolSize = dataSourceType.maximumPoolSize()
//        hikariConfig.minimumIdle = dataSourceType.minimumIdle()
//        hikariConfig.connectionTimeout = dataSourceType.connectionTimeout()
//        hikariConfig.maxLifetime = dataSourceType.maxLifetime()
//        hikariConfig.idleTimeout = dataSourceType.idleTimeout()
//        hikariConfig.dataSource = dataSource
//        hikariConfig.isAutoCommit = false
//
//        return hikariConfig
//    }
//}