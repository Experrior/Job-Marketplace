package edu.pwr.backend.config

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS
)
@Retention(
    AnnotationRetention.RUNTIME
)
@MustBeDocumented
annotation class TargetDataSource(val name: String = "")