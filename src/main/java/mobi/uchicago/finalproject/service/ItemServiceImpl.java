package mobi.uchicago.finalproject.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mobi.uchicago.finalproject.exception.FileEmptyException;
import mobi.uchicago.finalproject.exception.FileUploadException;
import mobi.uchicago.finalproject.exception.ResourceNotFoundException;
import mobi.uchicago.finalproject.exception.StorageFileNotFoundException;
import mobi.uchicago.finalproject.model.Item;
import mobi.uchicago.finalproject.model.User;
import mobi.uchicago.finalproject.repository.ItemRepository;
import mobi.uchicago.finalproject.repository.UserRepository;

@Service
public class ItemServiceImpl implements ItemService {
    private static String UPLOADED_FOLDER = "/home/ubuntu/final-project/upload/";
//    private static String UPLOADED_FOLDER = "/home/andrew/upload/";

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    public Item createItem(Item item, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        item.setUser(user);

        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Integer id, Item itemDetails) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("item", "id", id));

        item.setDescription(itemDetails.getDescription());

        Item updateditem = itemRepository.save(item);
        return updateditem;
    }

    @Override
    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("item", "id", id));

        itemRepository.delete(item);
    }

    @Override
    public Item uploadItemImage(Integer id, MultipartFile file) {
        Item item = null;

        if (file.isEmpty()) {
            throw new FileEmptyException("The file uploaded is empty");
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            // Path path = Paths.get(UPLOADED_FOLDER + itemId + "/" +
            // file.getOriginalFilename());
            Path parentPath = Paths.get(UPLOADED_FOLDER + id + "/");
            if (!Files.exists(parentPath)) {
                Files.createDirectories(parentPath);
            }

            Path path = Paths.get(UPLOADED_FOLDER + id + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

            item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
            item.setImgUrl(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("Something goes wrong during upload");
        }

        return item;
    }
    
    @Override
    public Resource getItemImage(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
        return loadAsResource(item.getImgUrl());
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = Paths.get(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + fileName);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }


}
