//package edu.pwr.backend.old_config
//
//import io.opentelemetry.api.trace.Span
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
//import org.springframework.transaction.support.TransactionSynchronizationManager
//
//class TransactionRoutingDataSource : AbstractRoutingDataSource() {
//    override fun determineCurrentLookupKey(): Any {
//        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
//            LOGGER.info("Routed to: {}", DataSourceType.READ_ONLY)
//            enrichSpan(DataSourceType.READ_ONLY)
//            return DataSourceType.READ_ONLY
//        }
//
//        LOGGER.info("Routed to: {}", DataSourceType.READ_WRITE)
//        enrichSpan(DataSourceType.READ_WRITE)
//        return DataSourceType.READ_WRITE
//    }
//
//    private fun enrichSpan(dataSourceType: DataSourceType) {
//        val currentSpan: Span? = Span.current()
//        currentSpan?.setAttribute("db.type", dataSourceType.name)
//        currentSpan?.setAttribute("db.poll", dataSourceType.poolName())
//    }
//
//    companion object {
//        private val LOGGER: Logger = LoggerFactory.getLogger(TransactionRoutingDataSource::class.java)
//    }
//}