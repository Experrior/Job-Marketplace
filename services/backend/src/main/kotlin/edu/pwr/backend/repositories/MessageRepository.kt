package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.entities.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface MessageRepository : JpaRepository<Message, Int>, JpaSpecificationExecutor<Message> {
    fun findByChatId(chat: Chat, pageable: Pageable): Page<Message>
}