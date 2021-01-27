package io.licence.webapp.features.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path ="/id/{idImage}")
    public Image save(@PathVariable("idImage") String idImage) {
        return imageService.create(idImage);
    }

    @PostMapping(path ="/query/{query}")
    public List<Image> saveAll(@PathVariable("query") String query) {
        return imageService.createAll(query);
    }

    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Image> getAll(){
        return imageService.getAll();
    }

}
