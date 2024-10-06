//package edu.pwr.backend.old_config
//
//import edu.pwr.backend.old_config.master_db.MasterDataSource
//import edu.pwr.backend.old_config.replica_db.ReplicaDataSource
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import javax.sql.DataSource
//
//@Configuration
//open class DataSourceRoutingConfiguration {
//    @Bean
//    @Primary
//    open fun routingDataSource(
//        @MasterDataSource masterDataSource: DataSource,
//        @ReplicaDataSource replicaDataSource: DataSource
//    ): TransactionRoutingDataSource {
//        val routingDataSource = TransactionRoutingDataSource()
//
//        val dataSourceMap: MutableMap<Any, Any> = HashMap()
//        dataSourceMap[DataSourceType.READ_WRITE] = masterDataSource
//        dataSourceMap[DataSourceType.READ_ONLY] = replicaDataSource
//
//        routingDataSource.setTargetDataSources(dataSourceMap)
//        routingDataSource.setDefaultTargetDataSource(masterDataSource)
//
//        return routingDataSource
//    }
//}