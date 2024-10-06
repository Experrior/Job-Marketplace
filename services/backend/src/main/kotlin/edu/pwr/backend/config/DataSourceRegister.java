//package edu.pwr.backend.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
//import org.springframework.core.env.Environment;
//import org.springframework.core.type.AnnotationMetadata;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class DataSourceRegister implements EnvironmentAware, ImportBeanDefinitionRegistrar {
//
//    private static final String DATASOURCE_POOL_TYPE = "org.apache.tomcat.jdbc.pool.DataSource";
//    private DataSource defaultDataSource;
//    private Map<String, DataSource> dataSources = new HashMap<>();
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        //init main dataSource
//        this.defaultDataSource = buildDataSource(environment,"");
//        dataSources.put("master", this.defaultDataSource);
//        DataSourceContextHolder.addDataSource("master");
//        //init all replicas
//        String replicas = environment.getProperty("spring.datasource.replicas");
//        String[] replicaDs = replicas.split("");
//        for (String ds : replicaDs) {
//            DataSource replica = buildDataSource(environment, ""+ds);
//            dataSources.put(ds, replica);
//        }
//    }
//
//    private DataSource buildDataSource(Environment environment, String prefix) {
//
//        try {
//            Class<? extends DataSource > dataSourceType = (Class <? extends DataSource>) Class.forName(DATASOURCE_POOL_TYPE);
//
//            String driverClassName = environment.getProperty("spring.datasource" + prefix + ".driver-class-name");
//            String url = environment.getProperty("spring.datasource" + prefix + ".url");
//            String username = environment.getProperty("spring.datasource" + prefix + ".username");
//            String password = environment.getProperty("spring.datasource" + prefix + ".password");
//            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
//                    .username(username).password(password);
//            return factory.build();
//        } catch (ClassNotFoundException e) {
//            log.error(e.getMessage());
//        }
//        return null;
//    }
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        //create DynamicDataSource
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(DataSource.class);
//        beanDefinition.setSynthetic(true);
//        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
//        mpv.addPropertyValue("targetDataSources", dataSources);
//        registry.registerBeanDefinition("dataSource", beanDefinition);
//        log.info("Registered DataSource");
//
//    }
//
//}
