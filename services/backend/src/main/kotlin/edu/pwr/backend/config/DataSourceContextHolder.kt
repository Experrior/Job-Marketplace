package edu.pwr.backend.config

object DataSourceContextHolder {
    private val threadLoal = ThreadLocal<String>()
    private val datSourceLookupKeys: MutableList<String> = ArrayList()

    fun switchDataSource(ds: String) {
        threadLoal.set(ds)
    }

    fun getDataSource(): String {
        return threadLoal.get()
    }

    fun clearDataSource() {
        threadLoal.remove()
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