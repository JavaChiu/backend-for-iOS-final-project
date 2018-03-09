package mobi.uchicago.finalproject.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mobi.uchicago.finalproject.model.Item;

public interface ItemService {
    List<Item> getAllItems();
    
    Item getItemById(Integer id);
    
    Item createItem(Item Item, Integer userId);
 
    Item updateItem(Integer id, Item ItemDetails);
 
    void deleteItem(Integer id);
    
    Item uploadItemImage(Integer id, MultipartFile file);
}
