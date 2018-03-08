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
import mobi.uchicago.finalproject.model.Item;
import mobi.uchicago.finalproject.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    
    @GetMapping("/items")
    public List<Item> getAllitems() {
        return itemRepository.findAll();
    }
    
    @PostMapping("/item")
    public Item createitem(@Valid @RequestBody Item item) {
        return itemRepository.save(item);
    }
    
    @GetMapping("/item/{id}")
    public Item getitemById(@PathVariable(value = "id") Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));
    }
    
    @PutMapping("/items/{id}")
    public Item updateitem(@PathVariable(value = "id") Integer itemId,
                                            @Valid @RequestBody Item itemDetails) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));

        item.setDescription(itemDetails.getDescription());

        Item updateditem = itemRepository.save(item);
        return updateditem;
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteitem(@PathVariable(value = "id") Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("item", "id", itemId));

        itemRepository.delete(item);

        return ResponseEntity.ok().build();
    }
}
