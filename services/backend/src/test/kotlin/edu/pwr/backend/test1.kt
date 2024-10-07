package edu.pwr.backend

import edu.pwr.backend.services.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class DataSourceAspectTest {

    @Autowired
    private lateinit var jobService: JobService

    @Test
    fun testDataSourceAspect() {
        jobService.getAllJobs() // Invoke the method to trigger the aspect
        // Add assertions or checks to verify the expected behavior
    }
}
