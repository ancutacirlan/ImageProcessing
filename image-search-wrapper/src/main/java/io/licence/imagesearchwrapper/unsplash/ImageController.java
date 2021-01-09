package io.licence.imagesearchwrapper.unsplash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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

    @GetMapping("save/{imageId}")
    public String saveImages (@PathVariable("imageId") String imageId) throws IOException {
        return imageService.saveImages(imageId);
    }


}
