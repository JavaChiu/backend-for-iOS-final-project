package mobi.uchicago.finalproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobi.uchicago.finalproject.exception.ResourceNotFoundException;
import mobi.uchicago.finalproject.model.Message;
import mobi.uchicago.finalproject.repository.MessageRepository;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;
    
    @GetMapping("/messages")
    public List<Message> getAllmessages() {
        return messageRepository.findAll();
    }
    
    @PostMapping("/message")
    public Message createmessage(@Valid @RequestBody Message message) {
        return messageRepository.save(message);
    }
    
    @GetMapping("/message/{id}")
    public Message getmessageById(@PathVariable(value = "id") Integer messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("message", "id", messageId));
    }
    
    @PutMapping("/messages/{id}")
    public Message updatemessage(@PathVariable(value = "id") Integer messageId,
                                            @Valid @RequestBody Message messageDetails) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("message", "id", messageId));

        message.setMessage(message.getMessage());

        Message updatedmessage = messageRepository.save(message);
        return updatedmessage;
    }
    
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deletemessage(@PathVariable(value = "id") Integer messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("message", "id", messageId));

        messageRepository.delete(message);

        return ResponseEntity.ok().build();
    }
}
