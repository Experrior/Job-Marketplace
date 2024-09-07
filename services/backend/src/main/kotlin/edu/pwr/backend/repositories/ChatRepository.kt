package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface ChatRepository : JpaRepository<Chat, Int>, JpaSpecificationExecutor<Chat> {

}