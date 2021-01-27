package io.licence.imagesearchwrapper.unsplash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/images")
public class ImageController {


    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/{query}", produces = MediaType.APPLICATION_JSON_VALUE, params = {"query"})
    public Results getAllImageByQuery(@RequestParam(value = "query") String query) {
        return imageService.getImageInfo(query);
    }

    @GetMapping("/{imageId}")
    public Image getImageById(@PathVariable("imageId") String imageId) {
       return imageService.getImageById(imageId);

    }

    @GetMapping("save/id/{imageId}")
    public String saveImages (@PathVariable("imageId") String imageId) throws IOException {
        return imageService.saveImages(imageId);
    }

    @GetMapping("save/query/{query}")
    public List<String> saveAllImages (@PathVariable("query") String query) throws IOException {
        return imageService.saveAllImages(query);
    }

}
