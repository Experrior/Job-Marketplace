package edu.pwr.backend.config

import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Slf4j
@Aspect
@Order(-1)
@Component
class DataSourceAspect {
    @Before("@annotation(tds)")
    @Throws(Throwable::class)
    fun before(joinPoint: JoinPoint, tds: TargetDataSource) {
        val ds = tds.name
        if (DataSourceContextHolder.containDataSource(ds)) {
//            DataSourceAspect.log.info("Use dataSource: {} -> {}", tds.name, joinPoint.signature)
            DataSourceContextHolder.switchDataSource(tds.name)
        } else {
//            DataSourceAspect.log.error(
//                "datasource [{}] doesn't exist, using default > {}",
//                tds.name,
//                joinPoint.signature
//            )
        }
    }


    @After("@annotation(tds)")
    fun restoreDataSource(point: JoinPoint, tds: TargetDataSource) {
//        DataSourceAspect.log.info("Revert datasource {} -> {}", tds.name, point.signature)
        DataSourceContextHolder.clearDataSource()
    }
}