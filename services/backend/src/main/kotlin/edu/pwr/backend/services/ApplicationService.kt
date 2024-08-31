package edu.pwr.backend.services;


import edu.pwr.backend.dto.ApplicationCreationDTO
import edu.pwr.backend.dto.ApplicationDTO
import edu.pwr.backend.entities.Application
import edu.pwr.backend.repositories.ApplicationRepository
import edu.pwr.backend.repositories.JobRepository
import edu.pwr.backend.repositories.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class ApplicationService(
    private val applicationRepository: ApplicationRepository,
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository
) {
    fun get(applicationId: Int): Application {
        return applicationRepository.getReferenceById(applicationId)
    }

    @Transactional
    open fun update(applicationCreationDTO: ApplicationCreationDTO): ApplicationDTO {
        val user = applicationCreationDTO.userId.let { userId ->
            userRepository.findById(userId)
                .orElseThrow { EntityNotFoundException("User with ID $userId not found") }
        }

        val job = applicationCreationDTO.jobId.let { jobId ->
            jobRepository.findById(jobId)
                .orElseThrow { EntityNotFoundException("Job with ID $jobId not found") }
        }

        val application = Application(
            userId = user,
            jobId = job,
        )

        return applicationRepository.save(application).toDTO()
    }
    @Transactional
    open fun delete(applicationId: Int): Boolean {
        try {
            val application = applicationRepository.getReferenceById(applicationId);
            applicationRepository.delete(application);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
