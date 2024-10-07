package edu.pwr.backend.config

import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Slf4j
@Aspect
@Order(-1) // Ensure this runs before transaction aspects
@Component
class DataSourceAspect {

    @Before("@annotation(edu.pwr.backend.config.TargetDataSource)")
    fun before(joinPoint: JoinPoint, tds: TargetDataSource) {
        println("[DEBUG] before method: ${joinPoint.signature}")
//        val ds = tds.name
//        if (DataSourceContextHolder.containDataSource(ds)) {
//            println("[DEBUG] Switching to dataSource: ${ds} -> ${joinPoint.signature}")
//            DataSourceContextHolder.switchDataSource(ds)
//        } else {
//            println("[DEBUG] DataSource [${ds}] doesn't exist, using default > ${joinPoint.signature}")
//        }
    }

    @After("@annotation(tds)")
    fun after(joinPoint: JoinPoint, tds: TargetDataSource) {
        println("[DEBUG] after method: ${joinPoint.signature}")
        DataSourceContextHolder.clearDataSource()
    }

    @AfterThrowing("@annotation(tds)")
    fun afterThrowing(joinPoint: JoinPoint, tds: TargetDataSource, ex: Throwable) {
        println("[DEBUG] Exception in method: ${joinPoint.signature}, message: ${ex.message}")
    }
}

































//package edu.pwr.backend.config
//
//import lombok.extern.slf4j.Slf4j
//import org.aspectj.lang.JoinPoint
//import org.aspectj.lang.annotation.After
//import org.aspectj.lang.annotation.AfterThrowing
//import org.aspectj.lang.annotation.Aspect
//import org.aspectj.lang.annotation.Before
//import org.springframework.context.annotation.EnableAspectJAutoProxy
//import org.springframework.core.annotation.Order
//import org.springframework.stereotype.Component
//
//@Slf4j
//@Aspect
//@Order(-1) // Set to ensure this runs before transaction aspects
//@Component
//@EnableAspectJAutoProxy
//class DataSourceAspect {
//
//    @AfterThrowing("@annotation(tds)")
//    fun at(joinPoint: JoinPoint, tds: TargetDataSource) {
//        println("[DEBUG] at")
//
//    }
//
//    @Before("@annotation(TargetDataSource)")
//    @Throws(Throwable::class)
//    fun before(joinPoint: JoinPoint, tds: TargetDataSource) {
//        println("[DEBUG] before")
////        val ds = tds.name
////        if (DataSourceContextHolder.containDataSource(ds)) {
////            println("[DEBUG] Switching to dataSource: ${ds} -> ${joinPoint.signature}")
////            DataSourceContextHolder.switchDataSource(ds)
////        } else {
////            println("[DEBUG]  DataSource [ ${ds}] doesn't exist, using default > ${joinPoint.signature}")
////            // Optionally, throw an exception or switch to a default data source
////        }
//    }
//
//    @After("@annotation(TargetDataSource)")
//    fun after(joinPoint: JoinPoint, tds: TargetDataSource) {
//        println("[DEBUG] after")
////        println("[DEBUG] Restoring default data source after ${joinPoint.signature}", )
////        DataSourceContextHolder.clearDataSource()
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package edu.pwr.backend.config
//
////
////import lombok.extern.slf4j.Slf4j
////import org.aspectj.lang.JoinPoint
////import org.aspectj.lang.annotation.After
////import org.aspectj.lang.annotation.Aspect
////import org.aspectj.lang.annotation.Before
////import org.springframework.core.annotation.Order
////import org.springframework.stereotype.Component
////
////
////@Slf4j
////@Aspect
////@Order(-1)
////@Component
////class DataSourceAspect {
////    @Before("@annotation(tds)")
////    @Throws(Throwable::class)
////    fun before(joinPoint: JoinPoint, tds: TargetDataSource) {
////        val ds = tds.name
////        println("[DEBUG] asda s")
////        if (DataSourceContextHolder.containDataSource(ds)) {
////            println("[DEBUG] asda s")
//////            DataSourceAspect.log.info("Use dataSource: {} -> {}", tds.name, joinPoint.signature)
////            DataSourceContextHolder.switchDataSource(tds.name)
////        } else {
////            println("[DEBUG] ISSUE")
//////            DataSourceAspect.log.error(
//////                "datasource [{}] doesn't exist, using default > {}",
//////                tds.name,
//////                joinPoint.signature
//////            )
////        }
////    }
////
////
////    @After("@annotation(tds)")
////    fun restoreDataSource(point: JoinPoint, tds: TargetDataSource) {
////        println("[DEBUG] Revert datasource {} -> {}")
//////        DataSourceAspect.log.info("Revert datasource {} -> {}", tds.name, point.signature)
////        DataSourceContextHolder.clearDataSource()
////    }
////}