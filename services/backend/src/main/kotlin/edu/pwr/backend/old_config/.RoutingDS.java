//package edu.pwr.backend.config;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//public class RoutingDS extends AbstractRoutingDataSource {
//
//    public RoutingDS(DataSource writer, DataSource reader) {
//        Map<Object, Object> dataSources = new HashMap<>();
//        dataSources.put("writer", writer);
//        dataSources.put("reader", reader);
//
//        setTargetDataSources(dataSources);
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return ReadOnlyContext.INSTANCE.isReadOnly() ? "reader" : "writer";
//    }
//}
