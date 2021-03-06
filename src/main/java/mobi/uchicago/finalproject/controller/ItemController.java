package mobi.uchicago.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mobi.uchicago.finalproject.model.Item;
import mobi.uchicago.finalproject.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllitems() {
        return itemService.getAllItems();
    }

    @PostMapping("/item/{userId}")
    public Item createitem(@PathVariable(value = "userId") Integer userId, @Valid @RequestBody Item item) {
        return itemService.createItem(item, userId);
    }

    @GetMapping("/item/{id}")
    public Item getitemById(@PathVariable(value = "id") Integer id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/items/{id}")
    public Item updateitem(@PathVariable(value = "id") Integer id, @Valid @RequestBody Item itemDetails) {

        return itemService.updateItem(id, itemDetails);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteitem(@PathVariable(value = "id") Integer id) {
        itemService.deleteItem(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/item/{id}/itemImage")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable(value = "id") Integer id) throws IOException {

        Resource file = itemService.getItemImage(id);
        MediaType type = null;
        if(file.getFilename().endsWith("jpg")||file.getFilename().endsWith("jpeg")) {
            type = MediaType.IMAGE_JPEG;
        } else if(file.getFilename().endsWith("png")) {
            type = MediaType.IMAGE_PNG;
        } else {
            type = MediaType.APPLICATION_JSON;
        }

        return ResponseEntity.ok()
                .contentLength(file.contentLength())
                .contentType(type)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/item/{id}/itemImage")
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file,
            @PathVariable(value = "id") Integer id) {

        Item updatedItem = itemService.uploadItemImage(id, file);
        itemService.updateItem(id, updatedItem);

        return ResponseEntity.ok().build();
    }
}
