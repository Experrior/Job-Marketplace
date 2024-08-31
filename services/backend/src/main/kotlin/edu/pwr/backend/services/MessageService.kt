package edu.pwr.backend.services;


import edu.pwr.backend.dto.MessageDTO
import edu.pwr.backend.repositories.MessageRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class MessageService(
    val messageRepository: MessageRepository
) {
    fun get(messageId: Int): MessageDTO {
        return messageRepository.getReferenceById(messageId).toDTO();
    }

    @Transactional
    open fun update(messageDTO: MessageDTO): MessageDTO {
        val toSave = messageDTO.toEntity()
        return messageRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(messageId: Int): Boolean {
        try {
            val message = messageRepository.getReferenceById(messageId);
            messageRepository.delete(message);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
