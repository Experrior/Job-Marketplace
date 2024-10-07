package edu.pwr.backend


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy


@SpringBootApplication()
@EnableAspectJAutoProxy
open class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
