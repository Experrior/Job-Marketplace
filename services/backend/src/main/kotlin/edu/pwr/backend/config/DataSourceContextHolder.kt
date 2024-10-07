package edu.pwr.backend.config

object DataSourceContextHolder {
    private val threadLocal = ThreadLocal<String>()
    private val datSourceLookupKeys: MutableList<String> = ArrayList()

    fun switchDataSource(ds: String) {
        threadLocal.set(ds)
    }

    fun getDataSource(): String {
        return threadLocal.get() ?: throw IllegalStateException("No data source set.")
    }


    fun clearDataSource() {
        threadLocal.remove()
    }

    fun containDataSource(ds: String): Boolean {
        return datSourceLookupKeys.contains(ds)
    }


    fun addDataSource(ds: String) {
        if (!containDataSource(ds)) {
            datSourceLookupKeys.add(ds)
        }
    }
}