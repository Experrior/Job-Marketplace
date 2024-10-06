//package edu.pwr.backend.config;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Order(-1)
//@Component
//public class DataSourceAspect {
//    @Before("@annotation(tds)")
//    public void before(JoinPoint joinPoint, TargetDataSource tds) throws Throwable {
//        String ds =tds.name();
//        if (DataSourceContextHolder.containDataSource(ds)) {
//            log.info("Use dataSource: {} -> {}", tds.name(), joinPoint.getSignature());
//            DataSourceContextHolder.switchDataSource(tds.name());
//        } else {
//            log.error("datasource [{}] doesn't exist, using default > {}", tds.name(), joinPoint.getSignature());
//        }
//
//    }
//
//
//    @After("@annotation(tds)")
//    public void restoreDataSource(JoinPoint point, TargetDataSource tds) {
//        log.info("Revert datasource {} -> {}", tds.name(), point.getSignature());
//        DataSourceContextHolder.clearDataSource();
//
//    }
//
//}
