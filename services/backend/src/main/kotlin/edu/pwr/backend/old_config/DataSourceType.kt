//package edu.pwr.backend.old_config
//
//enum class DataSourceType(private val poolName: String) {
//    READ_ONLY("Replica-DB") {
//        override fun minimumIdle(): Int {
//            return Runtime.getRuntime().availableProcessors()
//        }
//
//        override fun maximumPoolSize(): Int {
//            return Runtime.getRuntime().availableProcessors() * 4
//        }
//
//        override fun connectionTimeout(): Long {
//            return 250
//        }
//
//        override fun idleTimeout(): Long {
//            return 600000
//        }
//
//        override fun maxLifetime(): Long {
//            return 800000
//        }
//    },
//    READ_WRITE("Master-DB") {
//        override fun minimumIdle(): Int {
//            return Runtime.getRuntime().availableProcessors()
//        }
//
//        override fun maximumPoolSize(): Int {
//            return Runtime.getRuntime().availableProcessors() * 2
//        }
//
//        override fun connectionTimeout(): Long {
//            return 250
//        }
//
//        override fun idleTimeout(): Long {
//            return 600000
//        }
//
//        override fun maxLifetime(): Long {
//            return 800000
//        }
//    };
//
//    fun poolName(): String {
//        return this.poolName
//    }
//
//    abstract fun minimumIdle(): Int
//
//    abstract fun maximumPoolSize(): Int
//
//    abstract fun connectionTimeout(): Long
//
//    abstract fun idleTimeout(): Long
//
//    abstract fun maxLifetime(): Long
//}