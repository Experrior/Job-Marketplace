package edu.pwr.backend.services;


import edu.pwr.backend.dto.ChatCreationDTO
import edu.pwr.backend.dto.ChatDTO
import edu.pwr.backend.entities.Application
import edu.pwr.backend.entities.Chat
import edu.pwr.backend.repositories.ChatRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class ChatService(
    private val chatRepository: ChatRepository
) {
    fun get(chatId: Int): ChatDTO {
        return chatRepository.getReferenceById(chatId).toDTO();
    }

    @Transactional
    open fun update(chatCreationDTO: ChatCreationDTO): ChatDTO {
        val chat = Chat(
            name = chatCreationDTO.name,
            members = chatCreationDTO.members,
            createdBy = chatCreationDTO.createdBy
        )

        return chatRepository.save(chat).toDTO()

    }
    @Transactional
    open fun delete(chatId: Int): Boolean {
        try {
            val chat = chatRepository.getReferenceById(chatId);
            chatRepository.delete(chat);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
