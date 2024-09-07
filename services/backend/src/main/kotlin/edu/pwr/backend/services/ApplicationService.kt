package edu.pwr.backend.services

import edu.pwr.backend.entities.Application
import edu.pwr.backend.repositories.ApplicationRepository
import edu.pwr.backend.repositories.JobRepository
import edu.pwr.backend.repositories.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class ApplicationService(
    private val applicationRepository: ApplicationRepository,
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository
) {

    fun getApplicationById(applicationId: Int): Application? {
        return applicationRepository.findById(applicationId).orElse(null)
    }

    fun getAllApplications(limit: Int = 10, offset: Int = 0): List<Application> {
        return applicationRepository.findAll(PageRequest.of(offset, limit)).content
    }

    fun createApplication(
        userId: Int,
        jobId: Int,
    ): Application? {
        val user = userRepository.findById(userId).orElse(null) ?: return null
        val job = jobRepository.findById(jobId).orElse(null) ?: return null

        val newApplication = Application(
            userId = user,
            job = job,
            applicationDate = Timestamp.from(Instant.now())
        )
        return applicationRepository.save(newApplication)
    }

    fun updateApplication(
        applicationId: Int,
        status: String? = null
    ): Application? {
        val existingApplication = applicationRepository.findById(applicationId).orElse(null) ?: return null
        status?.let { existingApplication.status = it }
        return applicationRepository.save(existingApplication)
    }

    fun deleteApplication(applicationId: Int): Boolean {
        val application = applicationRepository.findById(applicationId).orElse(null) ?: return false
        applicationRepository.delete(application)
        return true
    }
}
