//package edu.pwr.backend.config
//
//import lombok.Getter
//import java.util.concurrent.atomic.AtomicInteger
//
//@Getter
//object ReadOnlyContext {
//    private val READ_ONLY_LEVEL: ThreadLocal<AtomicInteger> = ThreadLocal.withInitial {
//        AtomicInteger(
//            0
//        )
//    }
//
//    val isReadOnly: Boolean
//        //default constructor
//        get() = READ_ONLY_LEVEL.get()
//            .get() > 0
//
//    fun enter() {
//        READ_ONLY_LEVEL.get()
//            .incrementAndGet()
//    }
//
//    fun exit() {
//        READ_ONLY_LEVEL.get()
//            .decrementAndGet()
//    }
//}