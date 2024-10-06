package edu.pwr.backend.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class DynamicDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        return DataSourceContextHolder.getDataSource()
    }
}