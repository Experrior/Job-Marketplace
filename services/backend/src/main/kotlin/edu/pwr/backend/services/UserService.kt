package edu.pwr.backend.services;


import edu.pwr.backend.dto.UserDTO
import edu.pwr.backend.repositories.UserRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class UserService(
    val userRepository: UserRepository
) {
    fun get(userId: Int): UserDTO {
        return userRepository.getReferenceById(userId).toDTO();
    }

    @Transactional
    open fun update(userDTO: UserDTO): UserDTO {
        val toSave = userDTO.toEntity()
        return userRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(userId: Int): Boolean {
        try {
            val user = userRepository.getReferenceById(userId);
            userRepository.delete(user);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
