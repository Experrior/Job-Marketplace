package edu.pwr.backend.config

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar
import org.springframework.core.env.Environment
import org.springframework.core.type.AnnotationMetadata
import javax.sql.DataSource


@Slf4j
class DataSourceRegister : EnvironmentAware, ImportBeanDefinitionRegistrar {
    private var defaultDataSource: DataSource? = null
    private val dataSources: MutableMap<String, DataSource?> = HashMap()

    override fun setEnvironment(environment: Environment) {
        //init main dataSource
        this.defaultDataSource = buildDataSource(environment, "")
        dataSources["master"] = defaultDataSource
        DataSourceContextHolder.addDataSource("master")
        //init all replicas
        val replicas = environment.getProperty("spring.datasource.replicas")
        val replicaDs = replicas!!.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (ds in replicaDs) {
            val replica = buildDataSource(environment, "." + ds)
            dataSources[ds] = replica
        }
    }

    private fun buildDataSource(environment: Environment, prefix: String): DataSource? {
        try {
            val dataSourceType = Class.forName(DATASOURCE_POOL_TYPE) as Class<out DataSource>

            val driverClassName = environment.getProperty("spring.datasource$prefix.driver-class-name")
            val url = environment.getProperty("spring.datasource$prefix.url")
            val username = environment.getProperty("spring.datasource$prefix.username")
            val password = environment.getProperty("spring.datasource$prefix.password")
            val factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                .username(username).password(password)
            return factory.build()
        } catch (e: ClassNotFoundException) {
            throw e
//            DataSourceRegister.log.error(e.message)
        }
        return null
    }

    override fun registerBeanDefinitions(importingClassMetadata: AnnotationMetadata, registry: BeanDefinitionRegistry) {
        //create DynamicDataSource
        val beanDefinition = GenericBeanDefinition()
        beanDefinition.beanClass = DataSource::class.java
        beanDefinition.isSynthetic = true
        val mpv = beanDefinition.propertyValues
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource)
        mpv.addPropertyValue("targetDataSources", dataSources)
        registry.registerBeanDefinition("dataSource", beanDefinition)
        println("[DEBUG] registerBeanDefinitions")
    }

    companion object {
        private const val DATASOURCE_POOL_TYPE = "org.apache.tomcat.jdbc.pool.DataSource"
    }
}