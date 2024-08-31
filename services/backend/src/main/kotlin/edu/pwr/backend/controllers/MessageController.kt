package edu.pwr.backend.controllers


import edu.pwr.backend.dto.MessageDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.MessageService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class MessageController (val messageService: MessageService) {

    @GetMapping("/message/{messageId}")
    fun get( @PathVariable messageId: Int) : ResponseEntity<MessageDTO> {
        return ResponseEntity.ok(messageService.get(messageId))
    }

    @PostMapping("/message/update")
    fun update( @RequestBody messageDTO: MessageDTO) : ResponseEntity<MessageDTO> {
        return ResponseEntity.ok(messageService.update(messageDTO))
    }

    @DeleteMapping("/message/{messageId}")
    fun delete( @PathVariable messageId: Int) : ResponseEntity<Unit> {
        return if (messageService.delete(messageId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}