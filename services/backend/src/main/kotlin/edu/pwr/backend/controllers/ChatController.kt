package edu.pwr.backend.controllers


import edu.pwr.backend.dto.ChatCreationDTO
import edu.pwr.backend.dto.ChatDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.ChatService
import edu.pwr.backend.services.CompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class ChatController (val chatService: ChatService) {

    @GetMapping("/chat/{chatId}")
    fun get( @PathVariable chatId: Int) : ResponseEntity<ChatDTO> {
        return ResponseEntity.ok(chatService.get(chatId))
    }

    @PostMapping("/chat/update")
    fun update( @RequestBody chatCreationDTO: ChatCreationDTO) : ResponseEntity<ChatDTO> {
        return ResponseEntity.ok(chatService.update(chatCreationDTO))
    }

    @DeleteMapping("/chat/{chatId}")
    fun delete( @PathVariable chatId: Int) : ResponseEntity<Unit> {
        return if (chatService.delete(chatId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}