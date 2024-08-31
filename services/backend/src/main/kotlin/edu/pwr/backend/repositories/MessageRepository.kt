package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Message
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Int> {


}